package org.callumhoughton18.webelfchat;

public class WebElfUtils {
    // 6 = HappyBif
    // 1 = ShockedBif
    // 2 = confusedBif
    // 3 = SadBif
    // 4 = SmilingBif
    // 5 = LaughingBif
    // 7 = Joyfulbif
    public static String EmotionShorthandToLonghand(String emotion){
        switch (emotion){
            case "@1": return "shocked";
            case "@2": return "confused";
            case "@3": return "sad";
            case "@4": return "smiling";
            case "@5": return "laughing";
            case "@7": return "joyful";
            default: return "happy";
        }
    }
}
