package ru.cft.focusstart;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
enum PropertyManager {
    PRODUCERS_COUNT("producer.count"),
    PRODUCE_TIME("producer.produceTime"),
    CONSUMER_COUNT("consumer.count"),
    CONSUME_TIME("consumer.consumeTime"),
    STORAGE_SIZE("storage.size");

    private static final String PROPERTIES_FILE_NAME = "config.properties";
    @Getter
    private final Integer value;

    PropertyManager(String propertyName) {
        value = getPropertyValue(propertyName);
    }

    private Integer getPropertyValue(String propertyName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return Integer.valueOf(properties.getProperty(propertyName));
        } catch (IOException e) {
            log.error("Не удалось найти файл конфигураций: " + PROPERTIES_FILE_NAME, e);
        }
        return null;
    }
}
