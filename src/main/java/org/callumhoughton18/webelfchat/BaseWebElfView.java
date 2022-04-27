package org.callumhoughton18.webelfchat;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.callumhoughton18.webelfchat.WebElf;
import org.callumhoughton18.webelfchat.WebElfResponse;
import org.callumhoughton18.webelfchat.WebElfUtils;
import org.callumhoughton18.webelfchat.components.AddableMessageList;
import org.callumhoughton18.webelfchat.components.AudioPlayer;
import org.springframework.beans.factory.annotation.Value;

@CssImport(value = "./styles/fonts.css")
@CssImport(value = "./styles/elf-view.css")
@CssImport(value="./styles/text-field-style.css", themeFor="vaadin-text-field" )
public class BaseWebElfView extends VerticalLayout {
    // Need to init bot when the server has loaded, as currently the schedule and bot text
    // isn't loaded and available before the MainView init method is called.
    @Value("${bif_text_url}")
    private String bifTextPath;

    @Value("${elf_schedule_url}")
    private String elfSchedulePath;

    @Value("${base_elf_emotion_images_url}")
    private String baseElfEmotionImagesPath;

    @Value("${refresh_sound_url}")
    private String refreshSoundUrl;

    AddableMessageList responses;
    Button addButton;
    TextField userResponseField;
    Image bifEmotion;
    Image snowFlake1;
    Image snowFlake2;
    AudioPlayer player;
    WebElf webElf;
    private String webElfName;

    public BaseWebElfView(String webElfName) {
        this.webElfName = webElfName;

        setSizeFull();
        addClassName("elf-view");
        responses = new AddableMessageList();
        responses.addClassName("chat");

        player = new AudioPlayer();
        userResponseField = new TextField();
        addButton = new Button("Send!");
        bifEmotion = new Image();
        snowFlake1 = new Image();
        snowFlake1.addClassName("snowflake");
        snowFlake2 = new Image();
        snowFlake2.addClassName("snowflake");
        H1 heading = new H1(String.format("%s on the Modern Web", webElfName));
        HorizontalLayout header = new HorizontalLayout(snowFlake1, heading, snowFlake2);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        heading.addClassName("heading");
        bifEmotion.addClassName("elfEmotion");

        addButton.addClassName("submitButton");
        addButton.addClickShortcut(Key.ENTER);
        HorizontalLayout inputFields = new HorizontalLayout(userResponseField, addButton);
        inputFields.setAlignItems(FlexComponent.Alignment.CENTER);
        Scroller messageScroller = new Scroller(responses);
        messageScroller.setSizeFull();
        add(
                header,
                player,
                bifEmotion,
                messageScroller,
                inputFields
        );
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
        expand(inputFields);
        inputFields.setWidth("100%");
        inputFields.expand();
        userResponseField.setWidthFull();
    }


    protected void init(WebElf chosenWebElf) {

//        URL bifTextPathFormatted = this.getClass().getClassLoader().getResource(bifTextPath);
//        URL bifScheduleFormatted = this.getClass().getClassLoader().getResource(elfSchedulePath);
//        URL emotionsPathFormatted = this.getClass().getClassLoader().getResource(baseElfEmotionImagesPath);
        webElf = chosenWebElf;
        webElf.initializeBot();

        player.setSource(refreshSoundUrl);
        bifEmotion.setAlt(String.format("%ss Emotion Image", webElfName));
        bifEmotion.setSrc(String.format("files/happy_%s.jpg", webElfName));
        snowFlake1.setSrc("files/snowflake.gif");
        snowFlake2.setSrc("files/snowflake.gif");

        addButton.addClickListener(click -> {
            String userResponse = userResponseField.getValue();
            WebElfResponse bifResponse = webElf.submitMessage(userResponse);
            if (bifResponse.getEmotion() != null) {
                String emotionUrl = WebElfUtils.EmotionShorthandToLonghand(bifResponse.getEmotion());
                String fileUrl = String.format("files/%s_%s.jpg", emotionUrl, webElfName);
                bifEmotion.setSrc(fileUrl);
                player.play();
            }
            responses.addMessage("You", userResponse);
            responses.addMessage(webElfName, bifResponse.getResponse(), "lastMessage");
            userResponseField.setValue("");
        });

        responses.addMessage(webElfName, "Hello? Who's there? (Type your messages in the blue space below " +
                "and press Enter, or click the Send! button, to send them to me.)", "lastMessage");

        userResponseField.focus();
    }
}
