package com.app.newship.cli.model;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class NewsWhipPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("NewsWhip" + "$ ",
                AttributedStyle.DEFAULT.background(AttributedStyle.GREEN));
    }
}
