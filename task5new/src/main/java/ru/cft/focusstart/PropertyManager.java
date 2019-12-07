package ru.cft.focusstart;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertyManager {

    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);

    private static final String PROPERTIES_FILE_NAME = "config.properties";

    @Getter
    private final Properties properties = new Properties();

    PropertyManager() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Не удалось найти файл конфигураций: " + PROPERTIES_FILE_NAME, e);
        }
    }

    Integer getProperty(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }
}
