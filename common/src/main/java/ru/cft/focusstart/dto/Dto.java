package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MessageDto.class, name = "Message"),
        @JsonSubTypes.Type(value = LoginRequestDto.class, name = "LoginRequest"),
        @JsonSubTypes.Type(value = LoginResponseDto.class, name = "LoginResponse"),
        @JsonSubTypes.Type(value = PingDto.class, name = "Ping")
})
public interface Dto {

    void getDtoAction(ConnectionListener connectionListener, Connection socketConnection);
}
