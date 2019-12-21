package ru.cft.focusstart.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.cft.focusstart.Status;

@Getter
@RequiredArgsConstructor
public
class Dto {
    private final String login;
    private final Status status;
    private final String message;

    @Override
    public String toString() {
        return "login: " + login + ", status: " + status + ", message: " + message;
    }
}
