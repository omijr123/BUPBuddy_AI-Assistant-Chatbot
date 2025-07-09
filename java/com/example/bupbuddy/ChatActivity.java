package com.example.bupbuddy;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bupbuddy.models.ActionCategory;
import com.example.bupbuddy.models.ActionItem;
import com.example.bupbuddy.models.ChatMessage;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText inputMessage;
    private ImageButton btnSend, btnVoice;
    private ScrollView scrollViewQuickActions;
    private LinearLayout quickActionsContainer;
    private ChatAdapter chatAdapter;
    private final List<ChatMessage> messageList = new ArrayList<>();
    private GeminiClient geminiClient;
    private Executor mainExecutor;

    private boolean isDarkMode = false;
    private String currentLanguage = "en";

    private final ActivityResultLauncher<Intent> voiceResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    ArrayList<String> voiceResults = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (voiceResults != null && !voiceResults.isEmpty()) {
                        inputMessage.setText(voiceResults.get(0));
                        sendMessage();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPreferences();
        applyTheme();
        setContentView(R.layout.activity_chat);

        geminiClient = new GeminiClient(getString(R.string.gemini_api_key));
        mainExecutor = Executors.newSingleThreadExecutor();

        setupUI();
        addInitialBotMessage();
        populateQuickActions();
    }

    private void setupUI() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_view_chat);
        inputMessage = findViewById(R.id.input_message);
        btnSend = findViewById(R.id.btn_send);
        btnVoice = findViewById(R.id.btn_voice);
        scrollViewQuickActions = findViewById(R.id.scroll_view_quick_actions);
        quickActionsContainer = findViewById(R.id.quick_actions_container);

        chatAdapter = new ChatAdapter(messageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chatAdapter);

        btnSend.setOnClickListener(v -> sendMessage());
        btnVoice.setOnClickListener(v -> startVoiceInput());

        inputMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSend.setEnabled(s.length() > 0);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void populateQuickActions() {
        quickActionsContainer.removeAllViews();
        List<ActionCategory> categories = QuickActionData.getQuickActions(currentLanguage);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (ActionCategory category : categories) {
            TextView categoryTitle = (TextView) inflater.inflate(R.layout.quick_action_category, quickActionsContainer, false);
            categoryTitle.setText(category.getTitle());
            quickActionsContainer.addView(categoryTitle);

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setColumnCount(3);
            for (ActionItem item : category.getItems()) {
                View actionItemView = inflater.inflate(R.layout.quick_action_item, gridLayout, false);
                TextView icon = actionItemView.findViewById(R.id.action_icon);
                TextView label = actionItemView.findViewById(R.id.action_label);
                icon.setText(item.getIcon());
                label.setText(item.getLabel());

                actionItemView.setOnClickListener(v -> sendQuickMessage(item.getMessage()));

                GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                        GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f)
                );
                params.width = 0;
                actionItemView.setLayoutParams(params);
                gridLayout.addView(actionItemView);
            }
            quickActionsContainer.addView(gridLayout);
        }
    }


    private void sendMessage() {
        String messageText = inputMessage.getText().toString().trim();
        if (messageText.isEmpty()) return;

        switchToConversationView();
        addMessage(new ChatMessage(messageText, true));
        inputMessage.setText("");
        hideKeyboard();
        getBotResponse(messageText);
    }

    private void sendQuickMessage(String message) {
        switchToConversationView();
        addMessage(new ChatMessage(message, true));
        getBotResponse(message);
    }

    private void getBotResponse(String userMessage) {
        addTypingIndicator();

        String keywordResponse = KeywordResponseHelper.findKeywordResponse(this, userMessage, currentLanguage);
        if (keywordResponse != null) {
            removeTypingIndicator();
            addMessage(new ChatMessage(keywordResponse, false));
            return;
        }

        ListenableFuture<GenerateContentResponse> responseFuture = geminiClient.getResponse(userMessage);
        Futures.addCallback(responseFuture, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                runOnUiThread(() -> {
                    removeTypingIndicator();
                    String botResponseText = result.getText();
                    if (botResponseText != null) {
                        addMessage(new ChatMessage(botResponseText, false));
                    } else {
                        addMessage(new ChatMessage(getString(R.string.api_error_generic), false));
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                runOnUiThread(() -> {
                    removeTypingIndicator();
                    addMessage(new ChatMessage(getString(R.string.api_error_connection), false));
                });
            }
        }, mainExecutor);
    }

    private void switchToConversationView() {
        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
            scrollViewQuickActions.setVisibility(View.GONE);
        }
    }

    private void addMessage(ChatMessage chatMessage) {
        messageList.add(chatMessage);
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void addTypingIndicator() {
        messageList.add(new ChatMessage("typing", false));
        chatAdapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private void removeTypingIndicator() {
        if (!messageList.isEmpty() && messageList.get(messageList.size() - 1).isTyping()) {
            messageList.remove(messageList.size() - 1);
            chatAdapter.notifyItemRemoved(messageList.size());
        }
    }

    private void addInitialBotMessage() {
        String welcomeMessage = currentLanguage.equals("en") ?
                getString(R.string.bot_welcome_en) :
                getString(R.string.bot_welcome_bn);
        addMessage(new ChatMessage(welcomeMessage, false));
    }

    private void clearChat() {
        messageList.clear();
        geminiClient = new GeminiClient(getString(R.string.gemini_api_key));
        chatAdapter.notifyDataSetChanged();
        addInitialBotMessage();
        scrollViewQuickActions.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, currentLanguage.equals("bn") ? "bn-BD" : "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speak_now));
        try {
            voiceResultLauncher.launch(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Speech recognition not supported on this device.", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        MenuItem darkModeItem = menu.findItem(R.id.action_toggle_dark_mode);
        darkModeItem.setIcon(isDarkMode ? R.drawable.ic_sun : R.drawable.ic_moon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_toggle_dark_mode) {
            toggleDarkMode();
            return true;
        } else if (id == R.id.action_toggle_language) {
            toggleLanguage();
            return true;
        } else if (id == R.id.action_clear_chat) {
            clearChat();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences prefs = getSharedPreferences("BupBuddyPrefs", MODE_PRIVATE);
        isDarkMode = prefs.getBoolean("dark_mode", false);
        currentLanguage = prefs.getString("language", "en");
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = getSharedPreferences("BupBuddyPrefs", MODE_PRIVATE).edit();
        editor.putBoolean("dark_mode", isDarkMode);
        editor.putString("language", currentLanguage);
        editor.apply();
    }


    private void applyTheme() {
        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    private void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        savePreferences();
        recreate();
    }

    private void toggleLanguage() {
        currentLanguage = currentLanguage.equals("en") ? "bn" : "en";
        savePreferences();
        recreate();
    }
}