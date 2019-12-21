package ru.cft.focusstart;

import ru.cft.focusstart.view.MainView;

public class Main {
    public static void main(String[] args) {
        chatMonitor messageMonitor = new chatMonitor();
        ServerCommunicationService socketService = new ServerCommunicationService(messageMonitor);
        new MainView(messageMonitor, socketService);
    }
}
