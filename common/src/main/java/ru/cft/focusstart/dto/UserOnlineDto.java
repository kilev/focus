package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

import java.util.List;

@Value
public class UserOnlineDto implements Dto {

    private final List<String> userOnline;

    public UserOnlineDto(@JsonProperty("userInOnline") List<String> userOnline) {
        this.userOnline = userOnline;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {

    }
}
