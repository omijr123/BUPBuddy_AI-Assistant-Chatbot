package com.example.bupbuddy.models;

import java.util.List;

public class ActionCategory {
    private final String title;
    private final List<ActionItem> items;

    public ActionCategory(String title, List<ActionItem> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() { return title; }
    public List<ActionItem> getItems() { return items; }
}