package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

@Value
public class ClientMessageDto implements Dto {

    private final String author;
    private final String message;

    public ClientMessageDto(@JsonProperty("author") String author,
                            @JsonProperty("message") String message) {
        this.author = author;
        this.message = message;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {
        connectionListener.onClientMessage(this, connection);
    }
}
