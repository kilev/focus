package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

import java.util.Date;

@Value
public class MessageDto implements Dto {

    private final String login;
    private final String message;
    private final long messageId;
    private final Date messageTime;

    public MessageDto(@JsonProperty("login") String login,
                      @JsonProperty("message") String message,
                      @JsonProperty("messageId") long messageId,
                      @JsonProperty("messageTime") Date messageTime) {
        this.login = login;
        this.message = message;
        this.messageId = messageId;
        this.messageTime = messageTime;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onMessage(this, connection);
    }
}
