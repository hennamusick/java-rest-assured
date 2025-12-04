# REST Assured API Testing Framework with Page Object Model

A comprehensive REST API automation testing framework using REST Assured with Page Object Model (POM) design pattern.

## 🚀 Features

- **Page Object Model (POM)**: Clean separation of test logic and API service layer
- **REST Assured**: Powerful REST API testing library
- **TestNG**: Test execution and management
- **Allure Reporting**: Beautiful test execution reports
- **Lombok**: Reduced boilerplate code with annotations
- **Jackson**: JSON serialization/deserialization
- **Logback**: Comprehensive logging
- **AssertJ**: Fluent assertions

## 📁 Project Structure

```
java-rest-assured/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/api/automation/
│   │           ├── config/
│   │           │   └── ConfigManager.java
│   │           ├── models/
│   │           │   ├── User.java
│   │           │   └── Post.java
│   │           ├── services/           # POM Layer
│   │           │   ├── BaseService.java
│   │           │   ├── UserService.java
│   │           │   └── PostService.java
│   │           └── utils/
│   │               ├── RestClient.java
│   │               └── JsonUtils.java
│   └── test/
│       ├── java/
│       │   └── com/api/automation/
│       │       └── tests/
│       │           ├── BaseTest.java
│       │           ├── UserTests.java
│       │           └── PostTests.java
│       └── resources/
│           ├── config.properties
│           └── logback.xml
├── pom.xml
├── testng.xml
└── README.md
```

## 🏗️ Architecture

### Page Object Model (POM) Pattern

This framework implements POM pattern for API testing:

1. **Service Layer** (`services/`): Contains service classes representing different API endpoints
   - `BaseService.java`: Abstract base class with common HTTP methods
   - `UserService.java`: User API endpoints
   - `PostService.java`: Post API endpoints

2. **Model Layer** (`models/`): POJOs representing API request/response bodies
   - `User.java`: User entity
   - `Post.java`: Post entity

3. **Test Layer** (`tests/`): Test classes that use service layer
   - `BaseTest.java`: Base test setup
   - `UserTests.java`: User API tests
   - `PostTests.java`: Post API tests

4. **Configuration Layer** (`config/` & `utils/`):
   - `ConfigManager.java`: Manages application configuration
   - `RestClient.java`: REST Assured specifications
   - `JsonUtils.java`: JSON utilities

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
UserService userService = new UserService();
Response response = userService.getUserById(1);
User user = userService.getUserByIdAsObject(1);
```

### Test Layer:
```java
@Test
public void testGetUserById() {
    Response response = userService.getUserById(1);
    response.then()
            .statusCode(200)
            .body("id", equalTo(1));
}
```

## 🧪 Sample Test Scenarios

The framework includes tests for:

- ✅ GET all users/posts
- ✅ GET user/post by ID
- ✅ POST (create) new user/post
- ✅ PUT (update) user/post
- ✅ PATCH (partial update) user/post
- ✅ DELETE user/post
- ✅ Object deserialization

## 🔍 Key Components

### BaseService
Abstract class providing common HTTP methods (GET, POST, PUT, PATCH, DELETE) that all service classes inherit.

### Service Classes (POM)
- Encapsulate API endpoints
- Provide reusable methods for API calls
- Handle request/response logic
- Support method chaining

### Model Classes
- Use Lombok annotations (@Data, @Builder)
- Represent API entities
- Enable type-safe operations

## 📄 License

This project is licensed under the MIT License.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.