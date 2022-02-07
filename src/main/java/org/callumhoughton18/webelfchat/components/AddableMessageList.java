package org.callumhoughton18.webelfchat.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;

public class AddableMessageList extends Div {

    public void addMessage(String from, String text, String className) {
        Div line = new Div(new Text(from + ": " + text));
        if (className != null) line.addClassName(className);
        line.getElement().callJsFunction("scrollIntoView");
        add(line);
    }

    public void addMessage(String from, String text) {
        addMessage(from, text, null);
    }
}
