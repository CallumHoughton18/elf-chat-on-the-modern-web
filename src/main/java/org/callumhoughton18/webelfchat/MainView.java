package org.callumhoughton18.webelfchat;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.callumhoughton18.webelfchat.components.AddableMessageList;
import org.callumhoughton18.webelfchat.components.AudioPlayer;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

@Route("")
@CssImport(value = "./styles/fonts.css")
@CssImport(value = "./styles/elf-view.css")
@CssImport(value="./styles/text-field-style.css", themeFor="vaadin-text-field" )
public class MainView extends VerticalLayout {

    // Need to init bot when the server has loaded, as currently the schedule and bot text
    // isn't loaded and available before the MainView init method is called.
    @Value("${bif_text_url}")
    private String bifTextUrl;

    @Value("${elf_schedule_url}")
    private String elfScheduleUrl;

    @Value("${base_elf_emotion_images_url}")
    private String baseElfEmotionImagesUrl;

    @Value("${refresh_sound_url}")
    private String refreshSoundUrl;

    AddableMessageList responses;
    Button addButton;
    TextField userResponseField;
    Image bifEmotion;
    AudioPlayer player;
    WebElf bif;

    public MainView() {
        setSizeFull();
        addClassName("elf-view");
        responses = new AddableMessageList();
        responses.addClassName("chat");

        player = new AudioPlayer();
        userResponseField = new TextField();
        addButton = new Button("Add");
        bifEmotion = new Image();
        H1 heading = new H1("Bif on the Modern Web");
        heading.addClassName("heading");
        bifEmotion.setHeight("300px");

        addButton.addClassName("submitButton");
        addButton.addClickShortcut(Key.ENTER);
        HorizontalLayout inputFields = new HorizontalLayout(userResponseField, addButton);
        Scroller messageScroller = new Scroller(responses);
        messageScroller.setSizeFull();
        add(
                heading,
                player,
                bifEmotion,
                messageScroller,
                inputFields
        );
    }

    @PostConstruct
    public void init() {
        bif = new WebElf(bifTextUrl, elfScheduleUrl);
        bif.initializeBot();

        player.setSource(refreshSoundUrl);
        bifEmotion.setAlt("Bifs Emotion Image");
        bifEmotion.setSrc(String.format("files/happy_bif.jpg", baseElfEmotionImagesUrl));

        addButton.addClickListener(click -> {
            String userResponse = userResponseField.getValue();
            WebElfResponse bifResponse = bif.submitMessage(userResponse);
            if (bifResponse.getEmotion() != null) {
                String emotionUrl = WebElfUtils.EmotionShorthandToLonghand(bifResponse.getEmotion());
                String fileUrl = String.format("files/%s_bif.jpg", emotionUrl);
                bifEmotion.setSrc(fileUrl);
                player.play();
            }
            responses.addMessage("You", userResponse);
            responses.addMessage("Bif", bifResponse.getResponse(), "lastMessage");
            userResponseField.setValue("");
        });
    }
}