package ru.cft.focusstart;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(new Property());
        server.start();
    }
}
