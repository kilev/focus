package ru.cft.focusstart;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public enum Property {
    DEFAULT_HOST("server.host"),
    DEFAULT_PORT("server.port"),
    DEFAULT_LOGIN("login.default");

    private static final String PROPERTIES_FILE_NAME = "client.properties";
    @Getter
    private final String value;

    Property(String propertyKey) {
        value = getPropertyValue(propertyKey);
    }

    private String getPropertyValue(String propertyKey) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(propertyKey);
        } catch (IOException e) {
            log.error("Не удалось найти файл конфигураций: " + PROPERTIES_FILE_NAME, e);
        }
        return null;
    }
}
