package ru.cft.focusstart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.cft.focusstart.Connection;
import ru.cft.focusstart.ConnectionListener;

import java.util.List;

@Value
public class UserOnlineListDto implements Dto {

    private final List<String> userInOnline;

    public UserOnlineListDto(@JsonProperty("userInOnline") List<String> userInOnline) {
        this.userInOnline = userInOnline;
    }

    @Override
    public void getDtoAction(ConnectionListener connectionListener, Connection connection) {

    }
}
