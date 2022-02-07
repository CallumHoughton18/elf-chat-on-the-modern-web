package org.callumhoughton18.webelfchat.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

@Tag("audio")
public class AudioPlayer extends Component {
    public AudioPlayer() {
        getElement().setAttribute("controls", false);
    }

    public void setSource(String path) {
        getElement().setProperty("src", path);
    }

    public void play() {
        getElement().callJsFunction("pause");
        getElement().setProperty("currentTime", 0);
        getElement().callJsFunction("play");
    }
}
