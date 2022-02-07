package org.callumhoughton18.webelfchat;

public class WebElfResponse {
    private String emotion;
    private final String response;
    public WebElfResponse(String response) {
        this.response = response;
    }

    public WebElfResponse(String response, String emotion) {
        this.emotion = emotion;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public String getEmotion() {
        return emotion;
    }
}
