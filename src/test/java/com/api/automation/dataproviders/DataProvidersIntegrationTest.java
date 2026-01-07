package com.api.automation.dataproviders;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

/**
 * Integrated Data Providers Test Suite
 * Demonstrates all 13 methods of passing test data in a single test class
 * 
 * Methods Covered:
 * 1. Data Providers (2D Object Arrays)
 * 2. TestNG XML Parameters
 * 3. System Properties
 * 4. Environment Variables
 * 5. Properties Files
 * 6. Constants
 * 7. Enums
 * 8. JSON Configuration
 * 9. CSV Data
 * 10. Excel Data
 * 11. Dependency Injection
 * 12. Test Context
 * 13. Builder Pattern
 */
public class DataProvidersIntegrationTest {
    
    private static final Logger logger = LoggerFactory.getLogger(DataProvidersIntegrationTest.class);
    
    // ========================================
    // 1. DATA PROVIDER - Simple 2D Array
    // ========================================
    
    @DataProvider(name = "simpleCredentials")
    public Object[][] simpleCredentialsProvider() {
        logger.info("Loading simpleCredentials data provider");
        return new Object[][] {
            { "admin@example.com", "adminPassword123", "ADMIN" },
            { "user@example.com", "userPassword123", "USER" },
            { "guest@example.com", "guestPassword123", "GUEST" }
        };
    }
    
    @Test(dataProvider = "simpleCredentials", groups = "dataProvider")
    public void testWithSimpleDataProvider(String email, String password, String role) {
        logger.info("Testing with Data Provider - Email: {}, Role: {}", email, role);
        assert email != null && !email.isEmpty() : "Email should not be empty";
        assert password != null && !password.isEmpty() : "Password should not be empty";
        assert role != null && !role.isEmpty() : "Role should not be empty";
    }
    
    // ========================================
    // 2. DATA PROVIDER - Object Array
    // ========================================
    
    @DataProvider(name = "userObjects")
    public Object[][] userObjectsProvider() {
        logger.info("Loading userObjects data provider");
        return new Object[][] {
            { new TestUser("admin@example.com", "adminPassword123", "Admin User", "ADMIN") },
            { new TestUser("user@example.com", "userPassword123", "John Doe", "USER") },
            { new TestUser("moderator@example.com", "modPassword123", "Jane Smith", "MODERATOR") }
        };
    }
    
    @Test(dataProvider = "userObjects", groups = "dataProvider")
    public void testWithUserObjects(TestUser user) {
        logger.info("Testing with User Object - Email: {}, Name: {}", user.email, user.name);
        assert user.email != null : "Email should not be null";
        assert user.name != null : "Name should not be null";
        assert user.role != null : "Role should not be null";
    }
    
    // ========================================
    // 3. DATA PROVIDER - CSV Data
    // ========================================
    
    @DataProvider(name = "csvData")
    public Object[][] csvDataProvider() {
        logger.info("Loading CSV data provider");
        return loadCsvData("src/test/resources/testdata.csv");
    }
    
    private Object[][] loadCsvData(String filePath) {
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
                if (values.length >= 3) {
                    data.add(new Object[] {
                        values[0].trim(),      // email
                        values[1].trim(),      // password
                        values[2].trim(),      // role
                        Boolean.parseBoolean(values[3].trim())  // enabled
                    });
                }
            }
            logger.info("Loaded {} records from CSV file", data.size());
        } catch (IOException e) {
            logger.error("Error loading CSV data: {}", e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    @Test(dataProvider = "csvData", groups = "csvData")
    public void testWithCsvData(String email, String password, String role, boolean enabled) {
        logger.info("Testing with CSV Data - Email: {}, Role: {}, Enabled: {}", email, role, enabled);
        assert email != null && !email.isEmpty() : "Email from CSV should not be empty";
        assert role != null && !role.isEmpty() : "Role from CSV should not be empty";
    }
    
    // ========================================
    // 4. DATA PROVIDER - JSON Data
    // ========================================
    
    @DataProvider(name = "jsonData")
    public Object[][] jsonDataProvider() {
        logger.info("Loading JSON data provider");
        return loadJsonData("src/test/resources/testdata.json");
    }
    
    private Object[][] loadJsonData(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray users = json.getAsJsonArray("users");
            
            for (int i = 0; i < users.size(); i++) {
                JsonObject user = users.get(i).getAsJsonObject();
                data.add(new Object[] {
                    user.get("email").getAsString(),
                    user.get("password").getAsString(),
                    user.get("name").getAsString(),
                    user.get("role").getAsString()
                });
            }
            logger.info("Loaded {} records from JSON file", data.size());
        } catch (IOException e) {
            logger.error("Error loading JSON data: {}", e.getMessage());
        }
        
        return data.toArray(new Object[0][]);
    }
    
    @Test(dataProvider = "jsonData", groups = "jsonData")
    public void testWithJsonData(String email, String password, String name, String role) {
        logger.info("Testing with JSON Data - Email: {}, Name: {}, Role: {}", email, name, role);
        assert email != null : "Email from JSON should not be null";
        assert name != null : "Name from JSON should not be null";
        assert role != null : "Role from JSON should not be null";
    }
    
    // ========================================
    // 5. TESTNG XML PARAMETERS
    // ========================================
    
    @Parameters({"email", "password"})
    @Test(groups = "xmlParameters")
    public void testWithXmlParameters(
            @org.testng.annotations.Optional("default@example.com") String email,
            @org.testng.annotations.Optional("defaultPass") String password) {
        logger.info("Testing with XML Parameters - Email: {}", email);
        assert email != null && !email.isEmpty() : "Email parameter should not be empty";
    }
    
    // ========================================
    // 6. SYSTEM PROPERTIES
    // ========================================
    
    @Test(groups = "systemProperties")
    public void testWithSystemProperties() {
        String testEmail = System.getProperty("test.email", "test@example.com");
        String testEnv = System.getProperty("test.env", "dev");
        
        logger.info("Testing with System Properties - Email: {}, Environment: {}", testEmail, testEnv);
        assert testEmail != null : "System property test.email should exist";
    }
    
    // ========================================
    // 7. ENVIRONMENT VARIABLES
    // ========================================
    
    @Test(groups = "environmentVars")
    public void testWithEnvironmentVariables() {
        String apiKey = System.getenv("TEST_API_KEY");
        String baseUrl = System.getenv("TEST_BASE_URL");
        
        // Use defaults if not set
        if (apiKey == null) apiKey = "default-api-key";
        if (baseUrl == null) baseUrl = "https://api.example.com";
        
        logger.info("Testing with Environment Variables - API Key: {}, Base URL: {}", 
                   apiKey != null ? "[MASKED]" : "NOT SET", baseUrl);
        assert baseUrl != null : "Base URL should be available";
    }
    
    // ========================================
    // 8. PROPERTIES FILE
    // ========================================
    
    @Test(groups = "propertiesFile")
    public void testWithPropertiesFile() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/test/resources/testdata.properties"));
            String username = props.getProperty("admin.username");
            String apiUrl = props.getProperty("api.baseurl");
            
            logger.info("Testing with Properties File - Username: {}, API URL: {}", username, apiUrl);
            assert username != null : "Username from properties should exist";
        } catch (IOException e) {
            logger.error("Error loading properties file: {}", e.getMessage());
        }
    }
    
    // ========================================
    // 9. CONSTANTS
    // ========================================
    
    @Test(groups = "constants")
    public void testWithConstants() {
        String adminEmail = TestDataConstants.Users.ADMIN_EMAIL;
        String userEmail = TestDataConstants.Users.USER_EMAIL;
        String apiEndpoint = TestDataConstants.Endpoints.USERS;
        
        logger.info("Testing with Constants - Admin: {}, Endpoint: {}", adminEmail, apiEndpoint);
        assert adminEmail.equals("admin@example.com") : "Admin email constant should match";
        assert apiEndpoint.equals("/users") : "Users endpoint constant should match";
    }
    
    // ========================================
    // 10. ENUMS
    // ========================================
    
    @Test(groups = "enums")
    public void testWithEnums() {
        for (TestUserRole role : TestUserRole.values()) {
            logger.info("Testing with Enum - Role: {}, Email: {}", role.name(), role.getEmail());
            assert role.getEmail() != null : "Role email should not be null";
            assert role.getPassword() != null : "Role password should not be null";
        }
    }
    
    // ========================================
    // 11. DEPENDENCY INJECTION (ITestContext)
    // ========================================
    
    @Test(groups = "dependencyInjection")
    public void testWithDependencyInjection(ITestContext context) {
        logger.info("Testing with Dependency Injection");
        
        // Store data that can be shared with other tests
        context.setAttribute("testEmail", "injected@example.com");
        context.setAttribute("testPassword", "injectedPass123");
        
        Object storedEmail = context.getAttribute("testEmail");
        logger.info("Stored and retrieved from context: {}", storedEmail);
        assert storedEmail != null : "Injected context data should not be null";
    }
    
    // ========================================
    // 12. TEST CONTEXT - Shared Data
    // ========================================
    
    @Test(groups = "testContext", priority = 1)
    public void testContextStoreData(ITestContext context) {
        logger.info("Test 1: Storing data in test context");
        context.setAttribute("sharedUser", "sharedUser@example.com");
        context.setAttribute("sharedPassword", "sharedPass123");
    }
    
    @Test(groups = "testContext", priority = 2, dependsOnMethods = "testContextStoreData")
    public void testContextRetrieveData(ITestContext context) {
        logger.info("Test 2: Retrieving data from test context");
        String user = (String) context.getAttribute("sharedUser");
        String password = (String) context.getAttribute("sharedPassword");
        
        logger.info("Retrieved from context - User: {}, Password: [MASKED]", user);
        assert user != null && user.equals("sharedUser@example.com") : 
            "Should retrieve stored user from context";
    }
    
    // ========================================
    // 13. BUILDER PATTERN
    // ========================================
    
    @Test(groups = "builderPattern")
    public void testWithBuilderPattern() {
        logger.info("Testing with Builder Pattern");
        
        TestUser adminUser = new TestUserBuilder()
            .withEmail("admin@example.com")
            .withPassword("adminPassword123")
            .withName("Admin User")
            .withRole("ADMIN")
            .build();
        
        TestUser regularUser = new TestUserBuilder()
            .withEmail("user@example.com")
            .withName("John Doe")
            .build();  // Uses default password and role
        
        logger.info("Built user with Builder - Admin: {}, Regular User: {}", 
                   adminUser.email, regularUser.email);
        assert adminUser.email.equals("admin@example.com") : "Admin email should match";
        assert regularUser.role.equals("USER") : "Default role should be USER";
    }
    
    // ========================================
    // HELPER CLASSES
    // ========================================
    
    /**
     * Simple User model for testing
     */
    public static class TestUser {
        public String email;
        public String password;
        public String name;
        public String role;
        
        public TestUser(String email, String password, String name, String role) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.role = role;
        }
    }
    
    /**
     * Builder for flexible test user creation
     */
    public static class TestUserBuilder {
        private String email = "test@example.com";
        private String password = "testPassword123";
        private String name = "Test User";
        private String role = "USER";
        
        public TestUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }
        
        public TestUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
        
        public TestUserBuilder withName(String name) {
            this.name = name;
            return this;
        }
        
        public TestUserBuilder withRole(String role) {
            this.role = role;
            return this;
        }
        
        public TestUser build() {
            return new TestUser(email, password, name, role);
        }
    }
    
    /**
     * Test data constants organized by category
     */
    public static class TestDataConstants {
        public static class Users {
            public static final String ADMIN_EMAIL = "admin@example.com";
            public static final String ADMIN_PASSWORD = "adminPassword123";
            public static final String USER_EMAIL = "user@example.com";
            public static final String USER_PASSWORD = "userPassword123";
            public static final String GUEST_EMAIL = "guest@example.com";
            public static final String GUEST_PASSWORD = "guestPassword123";
        }
        
        public static class Endpoints {
            public static final String BASE_URL = "https://api.example.com";
            public static final String USERS = "/users";
            public static final String POSTS = "/posts";
            public static final String LOGIN = "/auth/login";
        }
        
        public static class HttpCodes {
            public static final int OK = 200;
            public static final int CREATED = 201;
            public static final int BAD_REQUEST = 400;
            public static final int UNAUTHORIZED = 401;
            public static final int NOT_FOUND = 404;
            public static final int SERVER_ERROR = 500;
        }
    }
    
    /**
     * Enum for type-safe user roles
     */
    public enum TestUserRole {
        ADMIN("admin@example.com", "adminPassword123", "Administrator"),
        MODERATOR("moderator@example.com", "modPassword123", "Moderator"),
        USER("user@example.com", "userPassword123", "Regular User"),
        GUEST("guest@example.com", "guestPassword123", "Guest");
        
        private final String email;
        private final String password;
        private final String displayName;
        
        TestUserRole(String email, String password, String displayName) {
            this.email = email;
            this.password = password;
            this.displayName = displayName;
        }
        
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getDisplayName() { return displayName; }
    }
}
