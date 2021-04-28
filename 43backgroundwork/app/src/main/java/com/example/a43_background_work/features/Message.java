package com.example.a43_background_work.features;

public class Message {

    public String text;
    public boolean isError;

    public Message(String text, boolean isError) {
        this.text = text;
        this.isError = isError;
    }
}
