# Data Passing Methods in TestNG Testing Framework

## Overview
This guide covers 13 different ways to pass values and data in TestNG testing frameworks for Java REST Assured API testing.

---

## 1. DATA PROVIDERS (Most Popular)

### What is a Data Provider?
A method that returns data to be used in a parameterized test. Each iteration runs the test with different data.

### Syntax
```java
@DataProvider
public Object[][] dataProviderName() {
    return new Object[][] {
        { "data1", "data2" },
        { "data3", "data4" }
    };
}

@Test(dataProvider = "dataProviderName")
public void testMethod(String param1, String param2) {
    // Test code
}
```

### Types of Data Providers

#### 1.1 Simple 2D Array
```java
@DataProvider
public Object[][] loginDataProvider() {
    return new Object[][] {
        { "user1@example.com", "password123" },
        { "user2@example.com", "password456" },
        { "user3@example.com", "password789" }
    };
}

@Test(dataProvider = "loginDataProvider")
public void testLogin(String username, String password) {
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);
}
```

#### 1.2 Object Array
```java
@DataProvider
public Object[][] userDataProvider() {
    return new Object[][] {
        { new User("John", "john@example.com", 25) },
        { new User("Jane", "jane@example.com", 30) },
        { new User("Bob", "bob@example.com", 28) }
    };
}

@Test(dataProvider = "userDataProvider")
public void testUserCreation(User user) {
    System.out.println("User: " + user.getName());
}
```

#### 1.3 Named Data Provider
```java
@DataProvider(name = "loginScenarios")
public Object[][] loginScenarios() {
    return new Object[][] {
        { "valid@example.com", "password123", true },
        { "invalid@example.com", "wrongpass", false },
        { "", "password123", false }
    };
}

@Test(dataProvider = "loginScenarios")
public void testLoginScenarios(String email, String password, boolean shouldSucceed) {
    // Test
}
```

#### 1.4 Dynamic Data Provider
```java
@DataProvider
public Object[][] dynamicDataProvider() {
    List<Object[]> data = new ArrayList<>();
    
    // Load from database
    List<User> users = getUsersFromDatabase();
    for (User user : users) {
        data.add(new Object[] { user });
    }
    
    return data.toArray(new Object[0][]);
}
```

### Advantages
✅ Easy to implement  
✅ Supports multiple data iterations  
✅ Can load data dynamically  
✅ Clean and readable test code  
✅ Works with any data type  

### Disadvantages
❌ Not suitable for very large datasets  
❌ Data is hardcoded or loaded at runtime  

---

## 2. TESTNG.XML PARAMETERS

### What are Parameters?
Test configuration values passed from testng.xml file to test methods.

### Syntax
```xml
<!-- testng.xml -->
<test name="Login Test">
    <parameter name="username" value="john@example.com"/>
    <parameter name="password" value="password123"/>
    <classes>
        <class name="com.api.automation.tests.LoginTest"/>
    </classes>
</test>
```

```java
@Parameters({"username", "password"})
@Test
public void testLogin(String username, String password) {
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);
}
```

### Examples

#### 2.1 Simple Parameters
```xml
<test name="API Test">
    <parameter name="baseUrl" value="https://api.example.com"/>
    <parameter name="apiKey" value="your-key-here"/>
    <parameter name="timeout" value="30"/>
    <classes>
        <class name="com.api.automation.ApiTest"/>
    </classes>
</test>
```

#### 2.2 Different Environments
```xml
<test name="Dev Environment">
    <parameter name="env" value="dev"/>
    <parameter name="baseUrl" value="http://localhost:8080"/>
</test>

<test name="Prod Environment">
    <parameter name="env" value="prod"/>
    <parameter name="baseUrl" value="https://api.example.com"/>
</test>
```

#### 2.3 Test Method
```java
@Parameters({"env", "baseUrl"})
@Test
public void testApi(String env, String baseUrl) {
    System.out.println("Environment: " + env);
    System.out.println("Base URL: " + baseUrl);
}
```

### Advantages
✅ Easy environment switching  
✅ Centralized configuration  
✅ No code changes needed  
✅ Good for different environments  

### Disadvantages
❌ Limited to string values  
❌ Not ideal for large datasets  
❌ Verbose XML format  

---

## 3. SYSTEM PROPERTIES

### What are System Properties?
JVM properties passed via command line or system configuration.

### Syntax
```bash
java -Dusername=john -Dpassword=pass123 TestRunner
```

```java
String username = System.getProperty("username", "default");
```

### Examples

#### 3.1 Command Line Usage
```bash
java -Dusername=john@example.com \
     -Dpassword=password123 \
     -Denv=staging \
     -Dapi.timeout=30 \
     -cp . com.example.TestRunner
```

#### 3.2 Maven Usage
```bash
mvn test -Dusername=john@example.com -Dpassword=pass123 -Denv=staging
```

#### 3.3 Get System Properties
```java
public class SystemPropertiesUtil {
    
    public static String getUsername() {
        return System.getProperty("username", "default@example.com");
    }
    
    public static String getPassword() {
        return System.getProperty("password", "defaultPass");
    }
    
    public static String getEnvironment() {
        return System.getProperty("env", "dev");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(System.getProperty("api.timeout", "30"));
    }
}

@Test
public void testWithSystemProperties() {
    String username = SystemPropertiesUtil.getUsername();
    String password = SystemPropertiesUtil.getPassword();
    // Test code
}
```

### Advantages
✅ Very flexible  
✅ Runtime configuration  
✅ Works across entire JVM  
✅ Perfect for CI/CD pipelines  

### Disadvantages
❌ Less organized than parameters  
❌ Harder to track what properties are used  
❌ Easy to have naming conflicts  

---

## 4. ENVIRONMENT VARIABLES

### What are Environment Variables?
System-level variables available to the JVM.

### Syntax
```bash
export API_KEY=your-api-key
export BASE_URL=https://api.example.com
java TestRunner
```

```java
String apiKey = System.getenv("API_KEY");
```

### Examples

#### 4.1 Set Environment Variables
```bash
# Linux/Mac
export TEST_USERNAME=john@example.com
export TEST_PASSWORD=password123
export API_KEY=abc123xyz
export BASE_URL=https://api.example.com

# Windows
set TEST_USERNAME=john@example.com
set TEST_PASSWORD=password123
set API_KEY=abc123xyz
set BASE_URL=https://api.example.com
```

#### 4.2 Use Environment Variables
```java
public class EnvironmentConfig {
    
    public static String getApiKey() {
        return System.getenv("API_KEY");
    }
    
    public static String getBaseUrl() {
        return System.getenv("BASE_URL");
    }
    
    public static String getUsername() {
        return System.getenv("TEST_USERNAME");
    }
    
    public static String getPassword() {
        return System.getenv("TEST_PASSWORD");
    }
}

@Test
public void testWithEnvironment() {
    String apiKey = EnvironmentConfig.getApiKey();
    String baseUrl = EnvironmentConfig.getBaseUrl();
    // Test code
}
```

### Advantages
✅ Secure for sensitive data  
✅ System-wide configuration  
✅ Great for secrets management  
✅ CI/CD friendly  

### Disadvantages
❌ Can be hard to debug  
❌ Environment-dependent  
❌ Not stored in code repository  

---

## 5. PROPERTIES FILES

### What are Properties Files?
External .properties files containing key-value pairs.

### Syntax
```properties
# testdata.properties
username=john@example.com
password=password123
environment=staging
```

```java
Properties props = new Properties();
props.load(new FileInputStream("testdata.properties"));
String username = props.getProperty("username");
```

### Examples

#### 5.1 Properties File Content
```properties
# User Credentials
admin.username=admin@example.com
admin.password=adminPassword123

regular.username=user@example.com
regular.password=userPassword123

# API Configuration
api.baseurl=https://api.example.com
api.timeout=30
api.retries=3

# Environment
environment=staging
debug.enabled=true

# Database
db.host=localhost
db.port=5432
db.name=test_db
```

#### 5.2 Load Properties
```java
public class ConfigReader {
    
    private static Properties properties;
    
    static {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(
                "src/test/resources/testdata.properties"
            );
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key, "0"));
    }
    
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key, "false"));
    }
}

@Test
public void testWithProperties() {
    String username = ConfigReader.getProperty("admin.username");
    String baseUrl = ConfigReader.getProperty("api.baseurl");
    int timeout = ConfigReader.getIntProperty("api.timeout");
}
```

### Advantages
✅ Easy to maintain  
✅ Supports multiple environments  
✅ Can be versioned  
✅ Human-readable format  

### Disadvantages
❌ Flat structure (hard for complex data)  
❌ No validation  
❌ Requires careful parsing  

---

## 6. CONSTANTS AND STATIC CLASSES

### What are Constants?
Hard-coded values organized in Java classes.

### Syntax
```java
public class TestData {
    public static final String ADMIN_EMAIL = "admin@example.com";
    public static final String ADMIN_PASSWORD = "adminPassword123";
}

String email = TestData.ADMIN_EMAIL;
```

### Examples

#### 6.1 Organized Constants
```java
public class TestDataConstants {
    
    // User Credentials
    public static final class Users {
        public static final String ADMIN_EMAIL = "admin@example.com";
        public static final String ADMIN_PASSWORD = "adminPassword123";
        
        public static final String USER_EMAIL = "user@example.com";
        public static final String USER_PASSWORD = "userPassword123";
        
        public static final String GUEST_EMAIL = "guest@example.com";
        public static final String GUEST_PASSWORD = "guestPassword123";
    }
    
    // API Endpoints
    public static final class Endpoints {
        public static final String BASE_URL = "https://api.example.com";
        public static final String LOGIN = "/auth/login";
        public static final String USERS = "/users";
        public static final String POSTS = "/posts";
    }
    
    // HTTP Codes
    public static final class HttpCodes {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int NOT_FOUND = 404;
        public static final int SERVER_ERROR = 500;
    }
}
```

#### 6.2 Usage
```java
@Test
public void testUserEndpoint() {
    String endpoint = TestDataConstants.Endpoints.BASE_URL + 
                     TestDataConstants.Endpoints.USERS;
    int expectedStatus = TestDataConstants.HttpCodes.OK;
    
    System.out.println("Endpoint: " + endpoint);
    System.out.println("Expected Status: " + expectedStatus);
}
```

### Advantages
✅ Type-safe  
✅ IDE autocomplete  
✅ Compile-time checking  
✅ Easy refactoring  

### Disadvantages
❌ Hard-coded values  
❌ Requires code change for different values  
❌ Not ideal for environment-specific data  

---

## 7. ENUMS FOR TYPE-SAFE DATA

### What are Enums?
Type-safe enumeration of predefined values.

### Syntax
```java
public enum UserRole {
    ADMIN("admin@example.com", "adminPass"),
    USER("user@example.com", "userPass");
    
    private final String email;
    private final String password;
    
    UserRole(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
```

### Examples

#### 7.1 User Roles Enum
```java
public enum UserRole {
    ADMIN("admin@example.com", "adminPassword123", "Administrator"),
    MODERATOR("mod@example.com", "modPassword123", "Moderator"),
    USER("user@example.com", "userPassword123", "Regular User"),
    GUEST("guest@example.com", "guestPassword123", "Guest");
    
    private final String email;
    private final String password;
    private final String displayName;
    
    UserRole(String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
    }
    
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDisplayName() { return displayName; }
}

@Test(dataProvider = "userRoles")
public void testWithUserRoles(UserRole role) {
    String email = role.getEmail();
    String password = role.getPassword();
    // Test code
}

@DataProvider
public Object[][] userRoles() {
    return new Object[][] {
        { UserRole.ADMIN },
        { UserRole.USER },
        { UserRole.GUEST }
    };
}
```

#### 7.2 HTTP Method Enum
```java
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");
    
    private final String method;
    
    HttpMethod(String method) {
        this.method = method;
    }
    
    public String getMethod() { return method; }
}

@Test
public void testHttpMethods() {
    for (HttpMethod method : HttpMethod.values()) {
        System.out.println(method.getMethod());
    }
}
```

#### 7.3 Environment Enum
```java
public enum TestEnvironment {
    DEV("http://localhost:8080", true),
    STAGING("https://staging-api.example.com", false),
    PRODUCTION("https://api.example.com", false);
    
    private final String baseUrl;
    private final boolean debugEnabled;
    
    TestEnvironment(String baseUrl, boolean debugEnabled) {
        this.baseUrl = baseUrl;
        this.debugEnabled = debugEnabled;
    }
    
    public String getBaseUrl() { return baseUrl; }
    public boolean isDebugEnabled() { return debugEnabled; }
}
```

### Advantages
✅ Type-safe  
✅ Compile-time checking  
✅ IDE support  
✅ Easy to iterate  

### Disadvantages
❌ Can't be changed at runtime  
❌ Requires recompilation for changes  

---

## 8. JSON CONFIGURATION FILES

### What is JSON Configuration?
Test data and configuration stored in JSON format.

### Example Structure
```json
{
  "users": [
    {
      "username": "john@example.com",
      "password": "password123",
      "role": "admin"
    },
    {
      "username": "jane@example.com",
      "password": "password456",
      "role": "user"
    }
  ],
  "testConfig": {
    "timeout": 30,
    "retries": 3,
    "environment": "staging"
  }
}
```

### Load JSON Data
```java
public class JsonConfigLoader {
    
    public static Object[][] loadUsersFromJson() {
        try {
            FileInputStream fis = new FileInputStream(
                "src/test/resources/testdata.json"
            );
            JsonObject json = JsonParser
                .parseReader(new InputStreamReader(fis))
                .getAsJsonObject();
            
            List<Object[]> data = new ArrayList<>();
            JsonArray users = json.getAsJsonArray("users");
            
            for (JsonElement element : users) {
                JsonObject user = element.getAsJsonObject();
                data.add(new Object[] {
                    user.get("username").getAsString(),
                    user.get("password").getAsString(),
                    user.get("role").getAsString()
                });
            }
            
            return data.toArray(new Object[0][]);
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[][] {};
        }
    }
}
```

### Advantages
✅ Hierarchical data structure  
✅ Complex data support  
✅ Human readable  
✅ Good for APIs  

### Disadvantages
❌ Requires JSON library  
❌ More verbose than properties  

---

## 9. CSV FILE DATA

### What is CSV Data?
Comma-separated values for tabular test data.

### Example CSV File
```csv
email,password,role,enabled
john@example.com,password123,admin,true
jane@example.com,password456,user,true
bob@example.com,password789,guest,false
```

### Load CSV Data
```java
public class CsvDataLoader {
    
    public static Object[][] loadDataFromCsv(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(filePath))) {
            
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header
                }
                
                String[] values = line.split(",");
                data.add(new Object[] {
                    values[0].trim(),  // email
                    values[1].trim(),  // password
                    values[2].trim(),  // role
                    Boolean.parseBoolean(values[3].trim())  // enabled
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data.toArray(new Object[0][]);
    }
}

@Test(dataProvider = "csvData")
public void testWithCsvData(String email, String password, 
                            String role, boolean enabled) {
    System.out.println("Email: " + email);
    System.out.println("Role: " + role);
}

@DataProvider
public Object[][] csvData() {
    return CsvDataLoader.loadDataFromCsv(
        "src/test/resources/testdata.csv"
    );
}
```

### Advantages
✅ Easy to manage large datasets  
✅ Can edit with Excel  
✅ Compact format  
✅ Good for tabular data  

### Disadvantages
❌ Limited data types  
❌ No nesting support  
❌ Parsing required  

---

## 10. EXCEL FILE DATA

### What is Excel Data?
Test data stored in Excel spreadsheets using Apache POI.

### Setup POI Dependency
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.0.0</version>
</dependency>
```

### Load Excel Data
```java
public class ExcelDataLoader {
    
    public static Object[][] loadDataFromExcel(String filePath) {
        List<Object[]> data = new ArrayList<>();
        
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            // Skip header row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                data.add(new Object[] {
                    row.getCell(0).getStringCellValue(),   // email
                    row.getCell(1).getStringCellValue(),   // password
                    row.getCell(2).getStringCellValue(),   // role
                    (int) row.getCell(3).getNumericCellValue()  // age
                });
            }
            
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data.toArray(new Object[0][]);
    }
}

@Test(dataProvider = "excelData")
public void testWithExcelData(String email, String password, 
                              String role, int age) {
    System.out.println("Email: " + email);
    System.out.println("Age: " + age);
}

@DataProvider
public Object[][] excelData() {
    return ExcelDataLoader.loadDataFromExcel(
        "src/test/resources/testdata.xlsx"
    );
}
```

### Advantages
✅ Professional format  
✅ Can use Excel formulas  
✅ Support multiple sheets  
✅ Complex data structure  

### Disadvantages
❌ Requires Apache POI  
❌ Slower to load  
❌ Version compatibility  

---

## 11. BUILDER PATTERN FOR TEST DATA

### What is Builder Pattern?
Design pattern for constructing complex objects step-by-step.

### Example
```java
public class UserBuilder {
    
    private String email = "test@example.com";
    private String password = "password123";
    private String name = "Test User";
    private int age = 25;
    private String role = "USER";
    
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public UserBuilder withAge(int age) {
        this.age = age;
        return this;
    }
    
    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }
    
    public User build() {
        return new User(this);
    }
}

@Test
public void testWithBuilder() {
    User admin = new UserBuilder()
        .withEmail("admin@example.com")
        .withPassword("adminPass")
        .withName("Admin User")
        .withAge(35)
        .withRole("ADMIN")
        .build();
    
    User regularUser = new UserBuilder()
        .withEmail("user@example.com")
        .withName("Regular User")
        .build();  // Uses defaults
    
    System.out.println("Admin: " + admin.getEmail());
    System.out.println("User: " + regularUser.getEmail());
}
```

### Advantages
✅ Readable test code  
✅ Flexible object creation  
✅ Default values support  
✅ Easy to understand  

### Disadvantages
❌ More boilerplate code  
❌ Slower for simple objects  

---

## 12. COMPARISON TABLE

| Method | Use Case | Complexity | Flexibility | Performance |
|--------|----------|-----------|-------------|-------------|
| Data Provider | Most tests | Low | High | Fast |
| testng.xml | Parameters | Low | Medium | Fast |
| System Properties | CLI/CI-CD | Low | High | Fast |
| Environment Variables | Secrets | Low | High | Fast |
| Properties Files | Configuration | Medium | Medium | Fast |
| Constants | Static data | Low | Low | Fast |
| Enums | Type-safe data | Medium | Low | Fast |
| JSON Files | Complex data | High | High | Medium |
| CSV Files | Tabular data | Medium | Medium | Medium |
| Excel Files | Large datasets | High | High | Slow |
| Builder Pattern | Object creation | High | High | Medium |
| Test Context | Shared data | Medium | Medium | Fast |

---

## 13. BEST PRACTICES

### Choose the Right Method
1. **Simple values** → Constants or testng.xml
2. **Environment-specific** → System properties or environment variables
3. **Large datasets** → CSV or Excel
4. **Complex structures** → JSON
5. **Multiple test cases** → Data Provider
6. **Sensitive data** → Environment variables
7. **Type safety** → Enums
8. **Complex objects** → Builder pattern

### Security Tips
- Never hardcode passwords
- Use environment variables for secrets
- Use masks in logs for sensitive data
- Keep test data in .gitignore
- Use different test data for different environments

### Maintenance
- Keep test data updated
- Document data providers
- Use meaningful names
- Organize data logically
- Version control your data files

### Performance
- Cache loaded data
- Avoid repeated file reads
- Use appropriate data structures
- Consider data size

---

## 14. PRACTICAL EXAMPLE: COMPLETE FLOW

```java
@Test(dataProvider = "loginCredentials")
public void testLoginFlow(String email, String password, 
                          String expectedRole) {
    // Arrange
    String baseUrl = SystemPropertiesUtil.getBaseUrl();
    int timeout = ConfigReader.getIntProperty("api.timeout");
    
    // Act
    RestAssured.baseURI = baseUrl;
    RestAssured.requestSpecification = new RequestSpecBuilder()
        .setConnectTimeout(timeout)
        .build();
    
    Response response = RestAssured
        .given()
            .contentType(ContentType.JSON)
            .body(new LoginRequest(email, password))
        .when()
            .post(Endpoints.LOGIN)
        .then()
            .statusCode(HttpCodes.OK)
            .extract()
            .response();
    
    // Assert
    String role = response.jsonPath().getString("role");
    Assert.assertEquals(role, expectedRole);
}

@DataProvider
public Object[][] loginCredentials() {
    return new Object[][] {
        { UserRole.ADMIN.getEmail(), UserRole.ADMIN.getPassword(), "ADMIN" },
        { UserRole.USER.getEmail(), UserRole.USER.getPassword(), "USER" },
        { UserRole.GUEST.getEmail(), UserRole.GUEST.getPassword(), "GUEST" }
    };
}
```

---

## 15. CONCLUSION

- **Data Provider** is most versatile for test automation
- Combine multiple methods for flexibility
- Choose based on your specific needs
- Keep test data organized and maintainable
- Document your data sources
- Follow security best practices

