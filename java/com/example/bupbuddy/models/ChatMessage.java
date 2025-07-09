// File: ChatMessage.java
package com.example.bupbuddy.models;

public class ChatMessage {
    private final String text;
    private final boolean isUser;
    private final boolean isTyping;

    public ChatMessage(String text, boolean isUser) {
        this.text = text;
        this.isUser = isUser;
        this.isTyping = "typing".equals(text);
    }

    public String getText() {
        return text;
    }

    public boolean isUser() {
        return isUser;
    }

    public boolean isTyping() {
        return isTyping;
    }
}