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
- **Soft Assertions**: Better test failure reporting with TestNG SoftAssert
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

3. **Test Layer** (`tests/`): Test classes that use service layer
   - `BaseTest.java`: Base test setup and teardown
   - `UserTests.java`: 8 comprehensive User API tests
   - `PostTests.java`: 9 comprehensive Post API tests
   - `ObjectTests.java`: 16 comprehensive Object API tests (including POST request tests)
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
    
    Response response = userService.getUserById(1);
    logger.info("Response received with status code: {}", response.getStatusCode());
    
    logger.info("Validating response body fields");
    response.then().log().status().log().body()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue());
    
    logger.info("Test completed successfully");
}

@Test
public void testCreateObject() {
    logger.info("Starting test: testCreateObject");
    
    Map<String, Object> data = new HashMap<>();
    data.put("year", 2023);
    data.put("price", 2499.99);
    
    ApiObject newObject = ApiObject.builder()
            .name("Apple MacBook Pro 14")
            .data(data)
            .build();
    
    logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
    
    Response response = objectService.createObject(newObject);
    logger.info("Response received with status code: {}", response.getStatusCode());
    
    softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 14");
    softAssert.assertAll();
    
    logger.info("Test completed successfully");
}
```

## 🧪 Test Coverage

The framework includes **33 comprehensive test cases** across 3 test suites:

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

### Object API Tests (16 tests)
- ✅ GET object by ID (Apple MacBook Pro 16)
- ✅ GET object as POJO
- ✅ Verify response structure
- ✅ Verify data types
- ✅ Verify response time
- ✅ Verify content type
- ✅ GET all objects
- ✅ POST create object with complete data
- ✅ POST create Apple MacBook Pro 16 (API doc example)
- ✅ POST validate response structure (id, createdAt)
- ✅ POST create with minimal data
- ✅ POST create multiple objects
- ✅ POST verify performance and content type
- ✅ PUT update object
- ✅ PATCH partial update object
- ✅ DELETE object

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