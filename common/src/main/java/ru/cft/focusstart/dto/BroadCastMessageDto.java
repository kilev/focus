package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

import java.util.Date;

@Value
public class BroadCastMessageDto implements Dto {

    private final String author;
    private final String message;
    private final Long id;
    private final Date time;

    public BroadCastMessageDto(@JsonProperty("author") String author,
                               @JsonProperty("message") String message,
                               @JsonProperty("id") Long messageId,
                               @JsonProperty("time") Date messageTime) {
        this.author = author;
        this.message = message;
        this.id = messageId;
        this.time = messageTime;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onBroadcastMessage(this, connection);
    }
}
