package com.example.bupbuddy;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.ai.client.generativeai.type.GenerationConfig;
import com.google.ai.client.generativeai.type.SafetySetting;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Collections;
import java.util.List;

public class GeminiClient {

    private final ChatFutures chat;

    public GeminiClient(String apiKey) {
        String modelName = "gemini-1.5-flash-latest";
        List<SafetySetting> safetySettings = Collections.emptyList();
        GenerationConfig generationConfig = new GenerationConfig.Builder().build();

        GenerativeModel gm = new GenerativeModel(
                modelName,
                apiKey,
                generationConfig,
                safetySettings
        );

        GenerativeModelFutures modelFutures = GenerativeModelFutures.from(gm);
        // Start a new chat. The chat object will maintain the history.
        this.chat = modelFutures.startChat(Collections.emptyList());
    }

    public ListenableFuture<GenerateContentResponse> getResponse(String userMessage) {
        // *** THE CORRECT WAY TO CREATE CONTENT ***
        // Use the Content.Builder and just add the text.
        Content userContent = new Content.Builder()
                .addText(userMessage)
                .build();

        return chat.sendMessage(userContent);
    }
}