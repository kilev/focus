package ru.cft.focusstart;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class ClientService {

    private Socket socket;
    private PrintWriter socketPrintWriter;

    public void sendMessage(String text) {
        socketPrintWriter.println(text);
    }

    public void connectToServer(String host, Integer port) {
        try {
            socket = new Socket(host, port);
            socketPrintWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            log.error("socket connection error", e);
        }
    }

    public void disconnectFromCurrentServer() {
        if (socket != null) {
            try {
                socketPrintWriter.close();
                socket.close();
            } catch (IOException e) {
                log.error("socket disconnection error", e);
            }
        }
    }

}
