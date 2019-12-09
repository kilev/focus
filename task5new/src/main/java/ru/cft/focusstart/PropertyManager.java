package ru.cft.focusstart;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
class PropertyManager {

    private static final String PROPERTIES_FILE_NAME = "config.properties";

    @Getter
    private final Properties properties = new Properties();

    PropertyManager() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Не удалось найти файл конфигураций: " + PROPERTIES_FILE_NAME, e);
        }
    }

    Integer getProperty(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }
}
