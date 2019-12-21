package ru.cft.focusstart;

import lombok.Getter;

import javax.swing.*;

public class chatMonitor {

    private static final String messageSeparator = System.lineSeparator();
    @Getter
    private final JTextArea messagesTextArea = new JTextArea();

    public void addMessage(String author, String message) {
        messagesTextArea.append("<" + author + "> " + message + messageSeparator);
    }

    public void clearMessages() {
        messagesTextArea.setText(null);
    }
}
