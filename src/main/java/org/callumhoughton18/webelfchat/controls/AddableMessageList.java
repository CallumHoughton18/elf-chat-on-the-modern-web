package org.callumhoughton18.webelfchat.controls;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

public class AddableMessageList extends Div {
    public void addMessage(String from, String text) {
        Div line = new Div(new Text(from + ": " + text));
        line.getElement().callJsFunction("scrollIntoView");
        add(line);
    }
}
