# REST Assured API Testing Framework with Page Object Model

A comprehensive REST API automation testing framework using REST Assured with Page Object Model (POM) design pattern. This framework demonstrates professional API testing with detailed logging, soft assertions, and comprehensive test coverage.

## 🚀 Features

- **Page Object Model (POM)**: Clean separation of test logic and API service layer
- **REST Assured 5.4.0**: Powerful REST API testing library with fluent assertions
- **TestNG 7.9.0**: Test execution and management framework
- **Allure Reporting 2.25.0**: Beautiful and comprehensive test execution reports
- **Lombok 1.18.30**: Reduced boilerplate code with annotations
- **Jackson 2.16.1**: JSON serialization/deserialization with formatted output
- **SLF4J + Logback**: Comprehensive logging with console and file output
- **Comprehensive Soft Assertions**: Non-blocking assertions across all test methods using TestNG SoftAssert for better test failure visibility and detailed validation reports
- **Request/Response Logging**: Detailed API call logging (method, URI, status, body)

## 📁 Project Structure

```
java-rest-assured/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/api/automation/
│   │           ├── config/
│   │           │   └── ConfigManager.java      # Configuration manager
│   │           ├── models/
│   │           │   ├── User.java               # User POJO with Lombok
│   │           │   ├── Post.java               # Post POJO with Lombok
│   │           │   └── ApiObject.java          # ApiObject POJO for restful-api.dev
│   │           ├── services/                   # POM Layer
│   │           │   ├── BaseService.java        # Base service with HTTP methods
│   │           │   ├── UserService.java        # User API endpoints
│   │           │   ├── PostService.java        # Post API endpoints
│   │           │   └── ObjectService.java      # Object API endpoints (restful-api.dev)
│   │           └── utils/
│   │               ├── RestClient.java         # REST Assured specifications
│   │               └── JsonUtils.java          # JSON utilities with formatting
│   └── test/
│       ├── java/
│       │   └── com/api/automation/
│       │       └── tests/
│       │           ├── BaseTest.java           # Base test setup
│       │           ├── UserTests.java          # 8 User API tests
│       │           ├── PostTests.java          # 9 Post API tests
│       │           ├── ObjectTests.java        # 16 Object API tests
│       │           └── utils/
│       │               └── TestUtils.java      # Test utility methods
│       └── resources/
│           ├── config.properties               # Application configuration
│           └── logback.xml                     # Logging configuration
├── pom.xml                                     # Maven dependencies
├── testng.xml                                  # TestNG suite configuration
└── README.md
```

## 🏗️ Architecture

### Page Object Model (POM) Pattern

This framework implements POM pattern for API testing:

1. **Service Layer** (`services/`): Contains service classes representing different API endpoints
   - `BaseService.java`: Abstract base class with common HTTP methods and request logging
   - `UserService.java`: User API endpoints (jsonplaceholder.typicode.com)
   - `PostService.java`: Post API endpoints (jsonplaceholder.typicode.com)
   - `ObjectService.java`: Object API endpoints (api.restful-api.dev)

2. **Model Layer** (`models/`): POJOs representing API request/response bodies
   - `User.java`: User entity with nested Address and Company classes
   - `Post.java`: Post entity
   - `ApiObject.java`: ApiObject entity with dynamic data map

3. **Test Layer** (`tests/`): Test classes with comprehensive soft assertions
   - `BaseTest.java`: Base test setup and teardown
   - `UserTests.java`: 8 comprehensive User API tests with soft assertions
   - `PostTests.java`: 9 comprehensive Post API tests with soft assertions
   - `ObjectDeleteTests.java`: DELETE endpoint tests with 6 test methods using soft assertions
   - `ObjectGetTests.java`: GET single object tests with 5 test methods using soft assertions
   - `ObjectGetAllTests.java`: GET all objects tests with 3 test methods using soft assertions
   - `ObjectGetByIdsTests.java`: GET by IDs query param tests with 4 test methods using soft assertions
   - `ObjectPostTests.java`: POST create tests with 2 test methods using soft assertions
   - `ObjectPutTests.java`: PUT update tests with 2 test methods using soft assertions
   - `ObjectPatchTests.java`: PATCH partial update tests with 1 test method using soft assertions
   - `TestUtils.java`: Test utility helper methods

4. **Configuration Layer** (`config/` & `utils/`):
   - `ConfigManager.java`: Singleton configuration manager
   - `RestClient.java`: REST Assured request/response specifications
   - `JsonUtils.java`: JSON serialization/deserialization with formatted output

## 🛠️ Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## 📦 Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd java-rest-assured
```

2. Install dependencies:
```bash
mvn clean install
```

## ▶️ Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test class:
```bash
mvn test -Dtest=UserTests
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## 📊 Generate Allure Reports

1. Run tests:
```bash
mvn clean test
```

2. Generate and open Allure report:
```bash
mvn allure:serve
```

## 🔧 Configuration

Edit `src/test/resources/config.properties` to configure:

```properties
base.uri=https://jsonplaceholder.typicode.com
timeout=30
environment=dev
```

## 📝 Example Usage

### Service Layer (POM):
```java
// Initialize service
UserService userService = new UserService();

// Get response
Response response = userService.getUserById(1);

// Get as POJO
User user = userService.getUserByIdAsObject(1);

// Create new user
User newUser = User.builder()
    .name("John Doe")
    .email("john@example.com")
    .build();
Response createResponse = userService.createUser(newUser);
```

### Test Layer with Logging and Soft Assertions:
```java
@Test
public void testGetUserById() {
    logger.info("Starting test: testGetUserById");
    logger.info("Fetching user with ID: 1");
    
    SoftAssert softAssert = new SoftAssert();
    Response response = userService.getUserById(1);
    logger.info("Response received with status code: {}", response.getStatusCode());
    
    logger.info("Validating response body fields");
    softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    softAssert.assertNotNull(response.body(), "Response body should not be null");
    softAssert.assertEquals(response.jsonPath().getInt("id"), 1, "User ID should be 1");
    softAssert.assertNotNull(response.jsonPath().getString("name"), "User name should not be null");
    
    logger.info("Test completed - asserting all soft assertions");
    softAssert.assertAll();
}

@Test
public void testCreateObject() {
    logger.info("Starting test: testCreateObject");
    
    SoftAssert softAssert = new SoftAssert();
    Map<String, Object> data = new HashMap<>();
    data.put("year", 2023);
    data.put("price", 2499.99);
    
    ApiObject newObject = ApiObject.builder()
            .name("Apple MacBook Pro 14")
            .data(data)
            .build();
    
    logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
    long startTime = System.currentTimeMillis();
    
    Response response = objectService.createObject(newObject);
    long responseTime = System.currentTimeMillis() - startTime;
    logger.info("Response received with status code: {} in {}ms", response.getStatusCode(), responseTime);
    
    // Multiple soft assertions for comprehensive validation
    softAssert.assertEquals(response.getStatusCode(), 201, "Status code should be 201 (Created)");
    softAssert.assertNotNull(response.body(), "Response body should not be null");
    softAssert.assertNotNull(response.jsonPath().getString("id"), "Created object ID should not be null");
    softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 14", "Object name should match");
    softAssert.assertTrue(responseTime < 3000, "Response time should be less than 3000 ms");
    
    logger.info("Test completed - asserting all soft assertions");
    softAssert.assertAll();
}
```

**Key Benefits of Soft Assertions:**
- ✅ **Non-blocking**: Continues executing all assertions even when one fails
- ✅ **Comprehensive Reports**: Collects all failures in single test report
- ✅ **Better Debugging**: Shows all issues at once instead of stopping at first failure
- ✅ **Descriptive Messages**: Each assertion includes clear failure message for easy diagnosis

## 🧪 Test Coverage

The framework includes **48 comprehensive test cases** with comprehensive soft assertions across 9 test suites:

### User API Tests (8 tests)
- ✅ GET all users
- ✅ GET user by ID
- ✅ GET user as POJO object
- ✅ POST (create) new user
- ✅ PUT (update) user
- ✅ PATCH (partial update) user
- ✅ DELETE user
- ✅ GET all users as array

### Post API Tests (9 tests)
- ✅ GET all posts
- ✅ GET post by ID
- ✅ GET post as POJO object
- ✅ GET posts by user ID
- ✅ POST (create) new post
- ✅ PUT (update) post
- ✅ PATCH (partial update) post
- ✅ DELETE post
- ✅ GET all posts as array

### Object API Tests (23 tests distributed across specialized test classes)

#### Object DELETE Tests (6 tests)
- ✅ DELETE object - validate status code
- ✅ DELETE object - validate response message
- ✅ DELETE object - response contains ID
- ✅ DELETE object - validate content type
- ✅ DELETE object - validate response time
- ✅ DELETE object - validate response structure

#### Object GET Tests (5 tests)
- ✅ GET object by ID with soft assertions
- ✅ GET object - verify response structure
- ✅ GET object - verify data types
- ✅ GET object - verify response time
- ✅ GET object - verify content type

#### Object GET All Tests (3 tests)
- ✅ GET all objects - validate status code
- ✅ GET all objects - validate content type
- ✅ GET all objects - validate response time

#### Object GET by IDs Tests (4 tests)
- ✅ GET objects by multiple IDs with soft assertions
- ✅ GET single object by ID query parameter
- ✅ GET by IDs - validate content type
- ✅ GET by IDs - validate response time

#### Object POST Tests (2 tests)
- ✅ POST create object with comprehensive soft assertions
- ✅ POST create object - performance and content type validation

#### Object PUT Tests (2 tests)
- ✅ PUT update object with soft assertions
- ✅ PUT update object - performance and content type validation

#### Object PATCH Tests (1 test)
- ✅ PATCH partial update object - response time and content type validation

### Test Features
- 📝 **Step-by-step logging** with SLF4J for every test action
- 📋 **Soft assertions** for comprehensive validation
- 🔍 **Request logging** (HTTP method, URI)
- 📊 **Response logging** (status code, body)
- 📄 **Formatted JSON** request body logging
- ⏱️ **Response time validation**
- 🎯 **Data type validation**
- 🧩 **POJO deserialization** tests

## 🔍 Key Components

### BaseService
Abstract class providing common HTTP methods (GET, POST, PUT, PATCH, DELETE) with:
- Built-in request logging (`.log().method().log().uri()`)
- REST Assured specifications
- Base path support
- Reusable across all service classes

### Service Classes (POM)
- Encapsulate API endpoints
- Provide reusable methods for API calls
- Handle request/response logic
- Support method chaining
- Include request/response logging

### Model Classes
- Use Lombok annotations (@Data, @Builder, @NoArgsConstructor, @AllArgsConstructor)
- Represent API entities with clean code
- Enable type-safe operations
- Support nested objects (User.Address, User.Company)
- Dynamic data structures (ApiObject with Map<String, Object>)

### Logging
- **SLF4J + Logback**: Console and file logging
- **Pattern**: `%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n`
- **Request logs**: HTTP method, URI
- **Response logs**: Status code, body
- **JSON formatting**: Pretty-printed request bodies

### Test Utilities
- `TestUtils.java`: Helper methods for common test operations
- Response detail printing
- Sample data creation
- Status code validation
- Retry with exponential backoff

## 🌐 APIs Under Test

### JSONPlaceholder (https://jsonplaceholder.typicode.com)
- Fake REST API for testing and prototyping
- Used for User and Post endpoints
- No authentication required

### Restful API (https://api.restful-api.dev)
- Real REST API for testing
- Used for Object endpoints (Apple devices)
- Supports full CRUD operations
- Returns generated IDs and timestamps

## 📈 Dependencies

```xml
<dependencies>
    <!-- REST Assured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
    </dependency>
    
    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
    </dependency>
    
    <!-- Allure TestNG -->
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <version>2.25.0</version>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
    </dependency>
    
    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.16.1</version>
    </dependency>
    
    <!-- SLF4J + Logback -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.11</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.14</version>
    </dependency>
</dependencies>
```

## 📄 License

This project is licensed under the MIT License.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.