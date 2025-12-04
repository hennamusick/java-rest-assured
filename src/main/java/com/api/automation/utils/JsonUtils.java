package com.api.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.path.json.JsonPath;

/**
 * JSON utilities for parsing and manipulating JSON data
 */
public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON", e);
        }
    }

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }

    public static JsonPath parseJson(String json) {
        return new JsonPath(json);
    }

    public static String getValueFromJson(String json, String path) {
        JsonPath jsonPath = new JsonPath(json);
        return jsonPath.getString(path);
    }
}
