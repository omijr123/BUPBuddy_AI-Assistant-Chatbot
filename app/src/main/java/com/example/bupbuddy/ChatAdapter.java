package com.example.bupbuddy;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bupbuddy.models.ChatMessage;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_BOT = 2;
    private static final int VIEW_TYPE_TYPING = 3;

    private final List<ChatMessage> messageList;

    public ChatAdapter(List<ChatMessage> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = messageList.get(position);
        if (message.isTyping()) {
            return VIEW_TYPE_TYPING;
        } else if (message.isUser()) {
            return VIEW_TYPE_USER;
        } else {
            return VIEW_TYPE_BOT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_USER) {
            View view = inflater.inflate(R.layout.item_message_user, parent, false);
            return new UserMessageViewHolder(view);
        } else if (viewType == VIEW_TYPE_BOT) {
            View view = inflater.inflate(R.layout.item_message_bot, parent, false);
            return new BotMessageViewHolder(view);
        } else { // VIEW_TYPE_TYPING
            View view = inflater.inflate(R.layout.item_typing_indicator, parent, false);
            return new TypingIndicatorViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_USER) {
            ((UserMessageViewHolder) holder).bind(message);
        } else if (holder.getItemViewType() == VIEW_TYPE_BOT) {
            ((BotMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        UserMessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_user);
        }
        void bind(ChatMessage message) {
            messageText.setText(message.getText());
        }
    }

    // This is the single, correct version of the BotMessageViewHolder
    static class BotMessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        BotMessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.text_message_bot);
        }
        void bind(ChatMessage message) {
            // *** KEY CHANGE: Use the new HtmlImageGetter to load images from URLs ***
            Html.ImageGetter imageGetter = new HtmlImageGetter(messageText);

            // The fromHtml method with 3 arguments is deprecated, but it's the correct one for this task.
            // It will now correctly process <img> tags using our ImageGetter.
            messageText.setText(Html.fromHtml(message.getText(), imageGetter, null));

            // This line is crucial for making the <a> links clickable.
            messageText.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    static class TypingIndicatorViewHolder extends RecyclerView.ViewHolder {
        TypingIndicatorViewHolder(View itemView) {
            super(itemView);
        }
    }
}