package com.example.a43_background_work.data.remote.dog_images.models;

public class ImagesUrlsResponse {

    String status;
    String[] message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
