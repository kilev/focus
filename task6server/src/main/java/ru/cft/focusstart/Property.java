package ru.cft.focusstart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Getter
class Property {

    private int messageMonitoringThreadCount;
    private String serverLogin;
    private int serverPort;
    private long nonActivityConnectionLiveTime;
    private int connectionCollectCheckTime;
    @Getter(AccessLevel.PRIVATE)
    private static final String PROPERTIES_FILE_NAME = "server.properties";

    Property() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            messageMonitoringThreadCount = Integer.parseInt(properties.getProperty("messageMonitoringThreadCount"));
            serverLogin = properties.getProperty("server.login");
            serverPort = Integer.parseInt(properties.getProperty("server.port"));
            nonActivityConnectionLiveTime = Integer.parseInt(properties.getProperty("connection.nonActivityLiveTime"));
            connectionCollectCheckTime = Integer.parseInt(properties.getProperty("connectionCollector.collectCheckTime"));
        } catch (IOException e) {
            log.error("Не удалось найти файл конфигураций: {}", PROPERTIES_FILE_NAME, e);
        }
    }
}
