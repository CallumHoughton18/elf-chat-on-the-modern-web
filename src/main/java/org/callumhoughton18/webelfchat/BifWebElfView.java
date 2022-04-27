package org.callumhoughton18.webelfchat;

import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.URL;

@Route("bif")
public class BifWebElfView extends BaseWebElfView{
    @Value("${bif_text_url}")
    private String bifTextPath;

    @Value("${elf_schedule_url}")
    private String elfSchedulePath;

    @Value("${base_elf_emotion_images_url}")
    private String baseElfEmotionImagesPath;
    public BifWebElfView() {
        super("Bif");
    }

    @PostConstruct
    public void constructBotAndView() {
        URL bifTextPathFormatted = this.getClass().getClassLoader().getResource(bifTextPath);
        URL bifScheduleFormatted = this.getClass().getClassLoader().getResource(elfSchedulePath);

        WebElf bonnieWebElf = new WebElf(bifTextPathFormatted, bifScheduleFormatted);
        init(bonnieWebElf);
    }
}
