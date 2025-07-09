package com.example.bupbuddy.models;

public class ActionItem {
    private final String icon;
    private final String label;
    private final String message;

    public ActionItem(String icon, String label, String message) {
        this.icon = icon;
        this.label = label;
        this.message = message;
    }

    public String getIcon() { return icon; }
    public String getLabel() { return label; }
    public String getMessage() { return message; }
}