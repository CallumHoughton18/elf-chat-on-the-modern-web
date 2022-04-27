package org.callumhoughton18.webelfchat;

import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.URL;

@Route("bonnie")
public class BonnieWebElfView extends BaseWebElfView {
    @Value("${bonnie_text_url}")
    private String bonnieTextPath;

    @Value("${elf_schedule_url}")
    private String elfSchedulePath;

    @Value("${base_elf_emotion_images_url}")
    private String baseElfEmotionImagesPath;
    public BonnieWebElfView() {
        super("Bonnie");
    }

    @PostConstruct
    public void constructBotAndView() {
        URL bonnieTextPathFormatted = this.getClass().getClassLoader().getResource(bonnieTextPath);
        URL bifScheduleFormatted = this.getClass().getClassLoader().getResource(elfSchedulePath);

        WebElf bonnieWebElf = new WebElf(bonnieTextPathFormatted, bifScheduleFormatted);
        init(bonnieWebElf);
    }
}
