package com.example.a43_background_work.data.remote.models;

import com.google.gson.JsonElement;

public class BreedsResponse {

    String status;
    JsonElement message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JsonElement getMessage() {
        return message;
    }

    public void setMessage(JsonElement message) {
        this.message = message;
    }
}
