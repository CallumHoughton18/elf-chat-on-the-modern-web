package org.callumhoughton18.webelfchat;

import org.robitron.elfchat.*;

import java.net.URL;

public class WebElf {
    private Grammar grammar;
    private QuestSet questSet;
    private Fred baseChatBot;
    private Context context;
    private URL scheduleUrl;
    private URL questionsUrl;
    private String currentMind;

    public WebElf(URL questSetUrl, URL botScheduleUrl){
        scheduleUrl = botScheduleUrl;
        questionsUrl = questSetUrl;
    }

    public void initializeBot() {
        grammar = new Grammar();
        questSet = new QuestSet();
        baseChatBot = new Fred(grammar, true, questionsUrl);
        context = new Context(baseChatBot);
        baseChatBot.setVerbose(false);
        questSet.loadFile(scheduleUrl);
    }

    public WebElfResponse submitMessage(String message) {

        // First, try mapping user response to what FRED expects based on previous messages MIND
        if (currentMind != null) {
            String mindBasedMapResponse = tryAndMatchMessageToExpectedMindResponse(message);
            if (mindBasedMapResponse != null) message = mindBasedMapResponse;
        }

        Phrase phrase = new Phrase(message, Grammar.breaks);
        grammar.removeCants(phrase);
        grammar.removeFluff(phrase);
        phrase.stashExpand();

        String botResponse = baseChatBot.formReply(phrase, context, true);
        // Try to match user response to dictionary of preset responses via MIND and SetQuest
        // If MIND response is null, call chatbot with raw input?
        // Parse the bots MIND response
        if (botResponse.startsWith("?")) {
            currentMind = botResponse.substring(0, 6);
            currentMind = currentMind.substring(1).trim();
            botResponse = botResponse.substring(6);
        } else {
            currentMind = null;
        }
        return parseEmotion(botResponse);
    }

    private String tryAndMatchMessageToExpectedMindResponse(String input) {
        Quest quest = questSet.findQuest(currentMind);
        for(int j = 0; j < quest.width; j++)
        {
            String s1 = (String)quest.query.elementAt(j);
            if(input.toLowerCase().contains(s1))
            {
                if (!s1.startsWith("!")) {
                    return s1;
                }
            }
        }
        return input;
    }

//    private WebElfResponse parseResponse(String response) {
//        String responseText = response;
//
//        Integer emotionNum = null;
//        if (response.startsWith("@")){
//            emotionNum = Integer.parseInt(response.substring(0, 2));
//            responseText = response.substring(2);
//        }
//        return new WebElfResponse(responseText, emotionNum);
//    }

    // 6 = HappyBif
    // 1 = ShockedBif
    // 2 = confusedBif
    // 3 = SadBif
    // 4 = SmilingBif
    // 5 = LaughingBif
    // 7 = Joyfulbif
    private WebElfResponse parseEmotion(String message) {
        if (!message.startsWith("@")) return new WebElfResponse(message);

        String emotionText = message.substring(0, 2);
        String formattedMessage = message.substring(2);
        return new WebElfResponse(formattedMessage, emotionText);
    }
}
