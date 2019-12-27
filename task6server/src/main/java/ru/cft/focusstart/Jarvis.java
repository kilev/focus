package ru.cft.focusstart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class Jarvis {

    private static final String AFTER_JARVIS_START_TEXT = "Джарвис готов слушать ваши команды.";
    private static final String SERVER_START_COMMAND = "start";
    private static final String SERVER_STOP_COMMAND = "stop";
    private static final String UNKNOWN_COMMAND_TEXT = "Джарвис не совсем понимает, что вы хотите сказать";

    private final Server server;

    void startListen() {
        log.info(AFTER_JARVIS_START_TEXT);
        Scanner in = new Scanner(System.in);
        while (true) {
            switch (in.next()) {
                case SERVER_START_COMMAND:
                    server.start();
                    break;
                case SERVER_STOP_COMMAND:
                    server.stop();
                    break;
                default:
                    log.info(UNKNOWN_COMMAND_TEXT);
                    break;

            }
        }
    }
}
