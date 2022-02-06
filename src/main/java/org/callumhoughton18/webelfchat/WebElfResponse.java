package org.callumhoughton18.webelfchat;

//enum Emotion {
//    SHOCKED(1),
//    CONFUSED(2),
//    SAD(3),
//    SMILING(4),
//    LAUGHING(5),
//    HAPPY(6),
//    JOYFUL(7);
//
//    private final int value;
//
//    Emotion(int value) {
//        this.value = value;
//    }
//
//    public int getValue() {
//        return value;
//    }
//}
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
