package org.callumhoughton18.webelfchat;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.callumhoughton18.webelfchat.controls.AddableMessageList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Route("")
@CssImport("./styles/shared-styles.css")
@Component
public class MainView extends VerticalLayout {

    // Need to init bot when the server has loaded, as currently the schedule and bot text
    // isn't loaded and available before the MainView init method is called.
    @Value("${bif_text_url}")
    private String bifTextUrl;

    @Value("${elf_schedule_url}")
    private String elfScheduleUrl;

    @Value("${base_elf_emotion_images_url}")
    private String baseElfEmotionImagesUrl;

    AddableMessageList responses;
    Button addButton;
    TextField userResponseField;
    Image bifEmotion;
    WebElf bif;

    public MainView() {
        setSizeFull();
        responses = new AddableMessageList();

        userResponseField = new TextField();
        addButton = new Button("Add");
        bifEmotion = new Image();
        bifEmotion.setHeight("300px");

        addButton.addClickShortcut(Key.ENTER);
        HorizontalLayout inputFields = new HorizontalLayout(userResponseField, addButton);
        Scroller messageScroller = new Scroller(responses);
        messageScroller.setSizeFull();
        add(
                new H1("Bif on the Modern Web"),
                bifEmotion,
                messageScroller,
                inputFields
        );
    }

    @PostConstruct
    public void init() {
        bif = new WebElf(bifTextUrl, elfScheduleUrl);
        bif.initializeBot();

        bifEmotion.setAlt("Bifs Emotion Image");
        bifEmotion.setSrc(String.format("files/happy_bif.jpg", baseElfEmotionImagesUrl));

        addButton.addClickListener(click -> {
            String userResponse = userResponseField.getValue();
            WebElfResponse bifResponse = bif.submitMessage(userResponse);
            if (bifResponse.getEmotion() != null) {
                String emotionUrl = WebElfUtils.EmotionShorthandToLonghand(bifResponse.getEmotion());
                String fileUrl = String.format("files/%s_bif.jpg", emotionUrl);
                bifEmotion.setSrc(fileUrl);
            }
            responses.addMessage("You", userResponse);
            responses.addMessage("Bif", bifResponse.getResponse());
            userResponseField.setValue("");
        });
    }
}