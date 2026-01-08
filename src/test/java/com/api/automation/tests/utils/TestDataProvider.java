package com.api.automation.tests.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Centralized Test Data Provider for all API tests
 * 
 * Provides various data passing methods:
 * - Data Providers from different sources
 * - CSV file loading
 * - JSON configuration loading
 * - Properties file reading
 * - Constants for test data
 */
public class TestDataProvider {
    
    // ============================================================
    // USER TEST DATA PROVIDERS
    // ============================================================
    
    /**
     * Data provider for user IDs to test
     */
    public static Object[][] getUserIds() {
        return new Object[][] {
            { 1 },
            { 2 },
            { 3 },
            { 5 },
            { 10 }
        };
    }
    
    /**
     * Data provider for post IDs to test
     */
    public static Object[][] getPostIds() {
        return new Object[][] {
            { 1 },
            { 2 },
            { 5 },
            { 10 },
            { 50 }
        };
    }
    
    /**
     * Data provider for pagination parameters
     */
    public static Object[][] getPaginationParams() {
        return new Object[][] {
            { 1, 10 },      // page, pageSize
            { 2, 10 },
            { 1, 5 },
            { 3, 20 }
        };
    }
    
    /**
     * Data provider for various user counts to validate
     */
    public static Object[][] getUserCounts() {
        return new Object[][] {
            { 10 },
            { 5 },
            { 1 }
        };
    }
    
    // ============================================================
    // CSV DATA PROVIDERS
    // ============================================================
    
    /**
     * Load test IDs from CSV file
     */
    public static Object[][] getTestIdsFromCsv() {
        return loadTestIdsFromCsv("src/test/resources/testdata.csv");
    }
    
    private static Object[][] loadTestIdsFromCsv(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] values = line.split(",");
                if (values.length >= 1) {
                    // Extract email as a pseudo-identifier
                    data.add(new Object[] { values[0].trim() });
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading CSV data: " + e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    // ============================================================
    // JSON CONFIGURATION PROVIDERS
    // ============================================================
    
    /**
     * Load test configuration from JSON file
     */
    public static JsonObject getTestConfigFromJson() {
        try (FileReader reader = new FileReader("src/test/resources/testdata.json")) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            return json.getAsJsonObject("testConfig");
        } catch (IOException e) {
            System.err.println("Error loading JSON configuration: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get API endpoints from JSON configuration
     */
    public static String getApiEndpointFromJson(String endpoint) {
        try (FileReader reader = new FileReader("src/test/resources/testdata.json")) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonObject endpoints = json.getAsJsonObject("endpoints");
            return endpoints.get(endpoint).getAsString();
        } catch (IOException e) {
            System.err.println("Error loading API endpoint: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Get test users from JSON file
     */
    public static JsonArray getUsersFromJson() {
        try (FileReader reader = new FileReader("src/test/resources/testdata.json")) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            return json.getAsJsonArray("users");
        } catch (IOException e) {
            System.err.println("Error loading users from JSON: " + e.getMessage());
            return null;
        }
    }
    
    // ============================================================
    // PROPERTIES FILE PROVIDERS
    // ============================================================
    
    /**
     * Get property value from testdata.properties
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }
    
    /**
     * Get property value with default
     */
    public static String getProperty(String key, String defaultValue) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/testdata.properties"));
            return props.getProperty(key, defaultValue);
        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
            return defaultValue;
        }
    }
    
    /**
     * Get integer property value
     */
    public static int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing integer property: " + e.getMessage());
            }
        }
        return defaultValue;
    }
    
    /**
     * Get boolean property value
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
    
    // ============================================================
    // CONSTANTS AND CONFIGURATION
    // ============================================================
    
    /**
     * Test data constants organized by category
     */
    public static class TestConstants {
        
        public static class API {
            public static final String BASE_URL = "https://jsonplaceholder.typicode.com";
            public static final String USERS_ENDPOINT = "/users";
            public static final String POSTS_ENDPOINT = "/posts";
            public static final String COMMENTS_ENDPOINT = "/comments";
            public static final int DEFAULT_TIMEOUT = 5000;
            public static final int RETRY_COUNT = 3;
        }
        
        public static class Validation {
            public static final int VALID_USER_ID = 1;
            public static final int INVALID_USER_ID = 9999;
            public static final int VALID_POST_ID = 1;
            public static final int INVALID_POST_ID = 9999;
            public static final int EXPECTED_USER_COUNT = 10;
            public static final int EXPECTED_POST_COUNT = 100;
        }
        
        public static class StatusCodes {
            public static final int OK = 200;
            public static final int CREATED = 201;
            public static final int BAD_REQUEST = 400;
            public static final int UNAUTHORIZED = 401;
            public static final int NOT_FOUND = 404;
            public static final int SERVER_ERROR = 500;
        }
        
        public static class Timeouts {
            public static final int SHORT = 2000;
            public static final int MEDIUM = 5000;
            public static final int LONG = 10000;
        }
    }
    
    // ============================================================
    // BUILDER PATTERN FOR TEST DATA
    // ============================================================
    
    /**
     * Builder for flexible test ID creation
     */
    public static class TestIdBuilder {
        private int userId = 1;
        private int postId = 1;
        private int commentId = 1;
        
        public TestIdBuilder withUserId(int userId) {
            this.userId = userId;
            return this;
        }
        
        public TestIdBuilder withPostId(int postId) {
            this.postId = postId;
            return this;
        }
        
        public TestIdBuilder withCommentId(int commentId) {
            this.commentId = commentId;
            return this;
        }
        
        public TestIds build() {
            return new TestIds(userId, postId, commentId);
        }
    }
    
    /**
     * Immutable test IDs container
     */
    public static class TestIds {
        public final int userId;
        public final int postId;
        public final int commentId;
        
        public TestIds(int userId, int postId, int commentId) {
            this.userId = userId;
            this.postId = postId;
            this.commentId = commentId;
        }
    }
    
    // ============================================================
    // ENUM FOR TEST SCENARIOS
    // ============================================================
    
    /**
     * Enum for different test scenarios
     */
    public enum TestScenario {
        HAPPY_PATH("Happy Path", true),
        EDGE_CASE("Edge Case", true),
        INVALID_DATA("Invalid Data", false),
        BOUNDARY("Boundary Test", true),
        PERFORMANCE("Performance Test", true);
        
        private final String description;
        private final boolean shouldPass;
        
        TestScenario(String description, boolean shouldPass) {
            this.description = description;
            this.shouldPass = shouldPass;
        }
        
        public String getDescription() {
            return description;
        }
        
        public boolean shouldPass() {
            return shouldPass;
        }
    }
}
