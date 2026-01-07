# 🎓 REST Assured API Testing Framework - Complete Beginner's Guide

[![Java CI/CD](https://github.com/hennamusick/java-rest-assured/actions/workflows/java-build.yml/badge.svg)](https://github.com/hennamusick/java-rest-assured/actions/workflows/java-build.yml)
[![Java Version](https://img.shields.io/badge/Java-21+-orange.svg)](https://www.oracle.com/java/technologies/downloads/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-green.svg)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.25.0-yellow.svg)](https://docs.qameta.io/allure/)
[![License](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)

A **comprehensive REST API automation testing framework** built with **REST Assured** and **Page Object Model (POM)** design pattern. Perfect for beginners learning API automation testing with Java!

This repository demonstrates **professional API testing** with detailed logging, soft assertions, POJO models, and comprehensive test coverage across real REST APIs.

---

## 📋 Table of Contents

- [What You'll Learn](#-what-youll-learn)
- [Features](#-features)
- [Project Structure](#-project-structure)
- [Architecture](#️-architecture)
- [Perfect For Beginners Because](#-perfect-for-beginners-because)
- [Framework Architecture - End-to-End Flow](#-framework-architecture---end-to-end-flow)
- [Prerequisites](#️-prerequisites)
- [Quick Start Installation](#-quick-start-installation)
- [Running Tests](#-running-tests)
- [Generate Beautiful Test Reports](#-generate-beautiful-test-reports)
- [Understanding the Framework - Beginner's Guide](#-understanding-the-framework---beginners-guide)
- [Configuration](#-configuration)
- [Code Examples - Step by Step](#-code-examples---step-by-step)
- [Comprehensive Classes & Methods Documentation](#-comprehensive-classes--methods-documentation)
- [Framework Architecture Explained](#️-framework-architecture-explained)
- [Test Coverage](#-test-coverage)
- [Key Components](#-key-components)
- [APIs Under Test](#-apis-under-test)
- [Dependencies](#-dependencies)
- [Learning Path for Beginners](#-learning-path-for-beginners)
- [Common Scenarios & Solutions](#-common-scenarios--solutions)
- [Troubleshooting](#-troubleshooting)
- [Additional Resources](#-additional-resources)
- [FAQ](#-faq)
- [Best Practices Implemented](#-best-practices-implemented)
- [Annotated Code Walkthroughs (Line-by-Line)](#-annotated-code-walkthroughs-line-by-line)
- [Data Providers & Parameterized Testing Guide](#-data-providers--parameterized-testing-guide)
- [License](#-license)
- [Contributing](#-contributing)
- [Questions or Issues](#-questions-or-issues)
- [Conclusion](#-conclusion)

---

## 📚 What You'll Learn

| Category | Skills |
|----------|--------|
| 🌐 **API Testing** | REST API fundamentals (GET, POST, PUT, PATCH, DELETE) |
| 🔧 **Framework** | REST Assured 5.4 with fluent assertions |
| 🏗️ **Design Pattern** | Page Object Model (POM) for API testing |
| 📦 **Data Handling** | POJO models with Lombok & Builder pattern |
| ✅ **Assertions** | Soft assertions for comprehensive validation |
| 🧪 **Test Framework** | TestNG suite management & parallel execution |
| 📋 **Build Tool** | Maven dependency & lifecycle management |
| 🎨 **Clean Code** | Lombok annotations for reduced boilerplate |
| 📝 **Logging** | SLF4J + Logback best practices |
| 📊 **Reporting** | Allure interactive test reports |
| 🔄 **JSON** | Serialization/deserialization with Jackson |
| 🚀 **CI/CD** | GitHub Actions automation pipeline |

## 🚀 Features

| Feature | Description | Version |
|---------|-------------|---------|
| 🏗️ **Page Object Model** | Clean separation of test logic and API service layer | - |
| 🔧 **REST Assured** | Powerful REST API testing with fluent assertions | 5.4.0 |
| 🧪 **TestNG** | Test execution and management framework | 7.9.0 |
| 📊 **Allure Reports** | Beautiful and comprehensive test execution reports | 2.25.0 |
| 🎨 **Lombok** | Reduced boilerplate code with annotations | 1.18.30 |
| 🔄 **Jackson** | JSON serialization/deserialization with formatting | 2.16.1 |
| 📝 **SLF4J + Logback** | Comprehensive logging with console and file output | 2.0.11 |
| ✅ **Soft Assertions** | Non-blocking assertions for better test failure visibility | Built-in |
| 📋 **Request/Response Logging** | Detailed API call logging (method, URI, status, body) | Built-in |
| 🚀 **CI/CD Ready** | GitHub Actions workflow with automated reporting | Built-in |

### ✨ Key Highlights

```
┌─────────────────────────────────────────────────────────────┐
│  ✅  48 Comprehensive Test Cases                            │
│  📦  3 REST APIs Under Test                                 │
│  🎯  100% Test Coverage for CRUD Operations                 │
│  📊  Automated Allure Reports                               │
│  🔄  GitHub Actions CI/CD Pipeline                          │
│  📝  Detailed Request/Response Logging                      │
│  🏗️  Professional POM Architecture                          │
│  ✨  Clean Code with Lombok                                 │
└─────────────────────────────────────────────────────────────┘
```

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
│   │               ├── JsonUtils.java          # JSON utilities with formatting
│   │               └── TestDataProvider.java   # Data providers for parameterization
│   └── test/
│       ├── java/
│       │   └── com/api/automation/
│       │       └── tests/
│       │           ├── BaseTest.java           # Base test setup
│       │           ├── UserTests.java          # 16 parameterized User tests
│       │           ├── PostTests.java          # 21 parameterized Post tests
│       │           ├── ObjectTests.java        # 16 Object API tests
│       │           ├── DataProvidersIntegrationTest.java # 27 data provider examples
│       │           └── utils/
│       │               └── TestUtils.java      # Test utility methods
│       └── resources/
│           ├── config.properties               # Application configuration
│           ├── testdata.csv                    # CSV test data (7 users)
│           ├── testdata.json                   # JSON test data (4 users + config)
│           ├── testdata.properties             # Properties test data
│           └── logback.xml                     # Logging configuration
├── pom.xml                                     # Maven dependencies
├── testng.xml                                  # TestNG suite configuration
└── README.md
```

### Data Providers Integration

The project structure now includes **centralized data provider utilities**:

```
Test Data Sources:
├── CSV Data: src/test/resources/testdata.csv
│   └── Contains: User email, password, roles, and metadata
├── JSON Data: src/test/resources/testdata.json  
│   └── Contains: User objects, API config, endpoints
└── Properties: src/test/resources/testdata.properties
    └── Contains: Credentials, URLs, timeouts

Data Provider Utility:
├── TestDataProvider.java (300+ lines)
│   ├── Core Methods: getUserIds(), getPostIds(), getPaginationParams()
│   ├── File Loaders: getTestIdsFromCsv(), getTestConfigFromJson()
│   ├── Helper Classes:
│   │   ├── TestConstants (API config, status codes)
│   │   ├── TestIdBuilder (fluent builder pattern)
│   │   └── TestScenario enum (5 test scenarios)
│   └── Property Loader: getProperty(key, default)
```

## 🏗️ Architecture

### Page Object Model (POM) Pattern with Data Providers

This framework implements **POM pattern integrated with TestNG Data Providers** for API testing:

1. **Service Layer** (`services/`): Contains service classes representing different API endpoints
   - `BaseService.java`: Abstract base class with common HTTP methods and request logging
   - `UserService.java`: User API endpoints (jsonplaceholder.typicode.com)
   - `PostService.java`: Post API endpoints (jsonplaceholder.typicode.com)
   - `ObjectService.java`: Object API endpoints (api.restful-api.dev)

2. **Model Layer** (`models/`): POJOs representing API request/response bodies
   - `User.java`: User entity with nested Address and Company classes
   - `Post.java`: Post entity
   - `ApiObject.java`: ApiObject entity with dynamic data map

3. **Test Layer** (`tests/`): Test classes with data providers and comprehensive soft assertions
   - `BaseTest.java`: Base test setup and teardown
   - `UserTests.java`: **16 parameterized User API tests** (8 methods × 2 data sets) with soft assertions
   - `PostTests.java`: **21 parameterized Post API tests** (8 methods × 2-3 data sets) with soft assertions
   - `DataProvidersIntegrationTest.java`: **27 comprehensive data provider examples**
   - `ObjectDeleteTests.java`: DELETE endpoint tests with 6 test methods
   - `ObjectGetTests.java`: GET single object tests with 5 test methods
   - `ObjectGetAllTests.java`: GET all objects tests with 3 test methods
   - `ObjectGetByIdsTests.java`: GET by IDs query param tests with 4 test methods
   - `ObjectPostTests.java`: POST create tests with 2 test methods
   - `ObjectPutTests.java`: PUT update tests with 2 test methods
   - `ObjectPatchTests.java`: PATCH partial update tests with 1 test method
   - `TestUtils.java`: Test utility helper methods

4. **Data Provider Layer** (NEW - `utils/`):
   - `TestDataProvider.java`: Centralized hub for all test data and parameterization
     - **7 Core Methods**: getUserIds(), getPostIds(), getPaginationParams(), etc.
     - **File Loaders**: CSV, JSON, Properties file readers
     - **TestConstants**: API config, status codes, validation values, timeouts
     - **TestIdBuilder**: Fluent builder for flexible test data
     - **TestScenario**: Enum for 5 test scenarios (HAPPY_PATH, EDGE_CASE, etc.)

5. **Configuration Layer** (`config/` & `utils/`):
   - `ConfigManager.java`: Singleton configuration manager
   - `RestClient.java`: REST Assured request/response specifications
   - `JsonUtils.java`: JSON serialization/deserialization with formatted output
   - **Data Source Files**:
     - `testdata.csv`: 7 user records with email, password, role, enabled status
     - `testdata.json`: 4 user objects + testConfig + API endpoints
     - `testdata.properties`: Admin credentials, URLs, timeouts

## 🎯 Perfect For Beginners Because...

- 📖 **Well-documented code** with comments explaining every step
- 🏗️ **Clean architecture** following industry best practices
- 🔍 **Real APIs** - Test against actual REST APIs (no mocks!)
- 📝 **Detailed logging** - See exactly what's happening in each test
- 🎨 **Beautiful reports** - Visual test execution results with Allure
- 🧩 **Modular design** - Easy to understand and extend
- ✨ **Modern Java** - Uses latest features (Java 17+, Lombok, Lambda)
- 🎯 **Data-Driven Testing** - Built-in data providers with 13+ parameterization methods
- 📊 **Multiple Data Sources** - CSV, JSON, Properties files with examples
- 🔄 **Parameterized Tests** - Run 37 test executions from 15 test methods
- 🛠️ **Utility Classes** - TestDataProvider, TestIdBuilder, TestConstants for easy test data management

---

## 🎨 Framework Architecture - End-to-End Flow

### High-Level Architecture Diagram with Data Providers

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                      DATA PROVIDER LAYER (NEW)                               │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  TestDataProvider.java (Centralized Hub)                            │    │
│  │  ┌──────────────────────────┐  ┌──────────────────────────┐        │    │
│  │  │ Data Sources             │  │ Core Methods             │        │    │
│  │  ├──────────────────────────┤  ├──────────────────────────┤        │    │
│  │  │ • testdata.csv           │  │ • getUserIds()           │        │    │
│  │  │ • testdata.json          │  │ • getPostIds()           │        │    │
│  │  │ • testdata.properties    │  │ • getPaginationParams()  │        │    │
│  │  └──────────────────────────┘  └──────────────────────────┘        │    │
│  │  ┌──────────────────────────────────────────────────────────────┐  │    │
│  │  │ Helper Classes: TestConstants, TestIdBuilder, TestScenario   │  │    │
│  │  └──────────────────────────────────────────────────────────────┘  │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Provides Test Data
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                    TEST EXECUTION LAYER (PARAMETERIZED)                      │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  TestNG Test Classes with @DataProvider (tests/)                   │    │
│  │  ┌──────────────────────────┐  ┌──────────────────────────┐       │    │
│  │  │ UserTests                │  │ PostTests                │       │    │
│  │  ├──────────────────────────┤  ├──────────────────────────┤       │    │
│  │  │ • 8 test methods         │  │ • 8 test methods         │       │    │
│  │  │ • 16 executions          │  │ • 21 executions          │       │    │
│  │  │   (@DataProvider)        │  │   (@DataProvider)        │       │    │
│  │  │ • provideUserIds()       │  │ • providePostIds()       │       │    │
│  │  │ • provideUserCounts()    │  │ • provideUserIds()       │       │    │
│  │  └──────────────────────────┘  └──────────────────────────┘       │    │
│  │                                                                     │    │
│  │  ┌──────────────────────────────────────────────────────────────┐ │    │
│  │  │ DataProvidersIntegrationTest (27 examples)                   │ │    │
│  │  │ • CSV data provider examples • JSON data provider examples   │ │    │
│  │  │ • Properties file examples   • Multiple parameterization     │ │    │
│  │  └──────────────────────────────────────────────────────────────┘ │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Uses Data to Execute Tests
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                       SERVICE LAYER (POM Pattern)                            │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  Service Classes (services/)                                        │    │
│  │  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐             │    │
│  │  │ UserService  │  │ PostService  │  │ObjectService │             │    │
│  │  │ - GET users  │  │ - GET posts  │  │ - CRUD ops   │             │    │
│  │  │ - POST user  │  │ - POST post  │  │ - GET by ID  │             │    │
│  │  │ - PUT user   │  │ - PUT post   │  │ - Query ops  │             │    │
│  │  │ - PATCH user │  │ - PATCH post │  │              │             │    │
│  │  │ - DELETE user│  │ - DELETE post│  │              │             │    │
│  │  └──────────────┘  └──────────────┘  └──────────────┘             │    │
│  │         │ extends           │ extends        │ extends             │    │
│  │         └───────────────────┴────────────────┘                     │    │
│  │                             │                                       │    │
│  │                    ┌────────▼────────┐                             │    │
│  │                    │  BaseService    │                             │    │
│  │                    │  - GET()        │                             │    │
│  │                    │  - POST()       │                             │    │
│  │                    │  - PUT()        │                             │    │
│  │                    │  - PATCH()      │                             │    │
│  │                    │  - DELETE()     │                             │    │
│  │                    │  - Logging      │                             │    │
│  │                    └─────────────────┘                             │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Uses
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                          MODEL LAYER (POJOs)                                 │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  POJO Classes (models/) - with Lombok @Data, @Builder              │    │
│  │  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐             │    │
│  │  │ User.java    │  │ Post.java    │  │ApiObject.java│             │    │
│  │  │ - id         │  │ - id         │  │ - id         │             │    │
│  │  │ - name       │  │ - userId     │  │ - name       │             │    │
│  │  │ - email      │  │ - title      │  │ - data       │             │    │
│  │  │ - address    │  │ - body       │  │   (Map)      │             │    │
│  │  │ - phone      │  │              │  │              │             │    │
│  │  └──────────────┘  └──────────────┘  └──────────────┘             │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Serialized/Deserialized by
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                       UTILITY & CONFIGURATION LAYER                          │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  ┌───────────────┐  ┌───────────────┐  ┌───────────────┐          │    │
│  │  │ RestClient    │  │ JsonUtils     │  │ConfigManager  │          │    │
│  │  │ - Request     │  │ - Serialize   │  │ - Properties  │          │    │
│  │  │   Spec        │  │ - Deserialize │  │ - Config      │          │    │
│  │  │ - Response    │  │ - Formatting  │  │   Loading     │          │    │
│  │  │   Spec        │  │               │  │               │          │    │
│  │  └───────────────┘  └───────────────┘  └───────────────┘          │    │
│  │                                                                     │    │
│  │  ┌───────────────┐  ┌───────────────┐  ┌───────────────┐          │    │
│  │  │ TestUtils     │  │AssertionHelper│  │ SLF4J Logger  │          │    │
│  │  │ - Helpers     │  │ - Soft Assert │  │ - Logback     │          │    │
│  │  │ - Retry Logic │  │ - Validation  │  │ - File Logs   │          │    │
│  │  └───────────────┘  └───────────────┘  └───────────────┘          │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Sends HTTP Requests
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                          REST ASSURED LIBRARY                                │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  HTTP Client (REST Assured 5.4.0)                                  │    │
│  │  - Request Building                                                 │    │
│  │  - Header Management                                                │    │
│  │  - Body Serialization (JSON)                                        │    │
│  │  - Response Parsing                                                 │    │
│  │  - Validation & Assertions                                          │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ HTTP Request (GET/POST/PUT/PATCH/DELETE)
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                            EXTERNAL REST APIs                                │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  ┌─────────────────────────────────┐  ┌──────────────────────────┐ │    │
│  │  │ JSONPlaceholder API              │  │ Restful-API.dev         │ │    │
│  │  │ https://jsonplaceholder.typicode │  │ https://api.restful-api │ │    │
│  │  │        .com                       │  │        .dev             │ │    │
│  │  │                                   │  │                         │ │    │
│  │  │ Endpoints:                        │  │ Endpoints:              │ │    │
│  │  │ - /users                          │  │ - /objects              │ │    │
│  │  │ - /posts                          │  │ - /objects/{id}         │ │    │
│  │  │ - /comments                       │  │ - /objects?id=1&id=2    │ │    │
│  │  └─────────────────────────────────┘  └──────────────────────────┘ │    │
│  └─────────────────────────────┬──────────────────────────────────────┘    │
└────────────────────────────────┼─────────────────────────────────────────────┘
                                 │ Returns HTTP Response (JSON)
                                 ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                        REPORTING & LOGGING OUTPUT                            │
│  ┌────────────────────────────────────────────────────────────────────┐    │
│  │  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐             │    │
│  │  │ Allure       │  │ TestNG HTML  │  │ Console Logs │             │    │
│  │  │ Reports      │  │ Reports      │  │ (SLF4J)      │             │    │
│  │  │ - Graphs     │  │ - Test       │  │ - Request    │             │    │
│  │  │ - Timeline   │  │   Results    │  │   Details    │             │    │
│  │  │ - Test Steps │  │ - Pass/Fail  │  │ - Response   │             │    │
│  │  │ - Attachments│  │   Summary    │  │   Info       │             │    │
│  │  └──────────────┘  └──────────────┘  └──────────────┘             │    │
│  └────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### End-to-End Test Execution Flow

```
┌──────────────────────────────────────────────────────────────────────────┐
│ STEP 1: Test Initialization                                              │
│ ──────────────────────────────────────────────────────────────────────   │
│                                                                           │
│  @BeforeClass                                                             │
│  ┌──────────────────────────────────────┐                                │
│  │ 1. Load Configuration                │                                │
│  │    - config.properties               │                                │
│  │    - logback.xml                     │                                │
│  │                                       │                                │
│  │ 2. Initialize Service Layer          │                                │
│  │    - Create Service Object           │                                │
│  │    - Setup REST Assured specs        │                                │
│  │                                       │                                │
│  │ 3. Initialize Logger                 │                                │
│  │    - SLF4J Logger instance           │                                │
│  └──────────────────────────────────────┘                                │
│                    ▼                                                      │
└───────────────────┼──────────────────────────────────────────────────────┘
                    │
┌───────────────────▼──────────────────────────────────────────────────────┐
│ STEP 2: Test Execution - Example: testCreateObject()                     │
│ ──────────────────────────────────────────────────────────────────────   │
│                                                                           │
│  @Test                                                                    │
│  ┌──────────────────────────────────────┐                                │
│  │ A. Prepare Test Data (POJO)         │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ ApiObject.builder()          │ │                                │
│  │    │   .name("MacBook Pro")       │ │                                │
│  │    │   .data(dataMap)             │ │                                │
│  │    │   .build()                   │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ B. Call Service Method               │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ objectService.createObject() │ │                                │
│  │    │   → BaseService.post()       │ │                                │
│  │    │     → RestClient specs       │ │                                │
│  │    │       → REST Assured         │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ C. HTTP Request Sent                 │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ POST /objects                │ │                                │
│  │    │ Headers:                     │ │                                │
│  │    │   Content-Type: JSON         │ │                                │
│  │    │ Body:                        │ │                                │
│  │    │   { "name": "MacBook",       │ │                                │
│  │    │     "data": {...} }          │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ D. API Processes Request             │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ api.restful-api.dev          │ │                                │
│  │    │ - Validates request          │ │                                │
│  │    │ - Creates resource           │ │                                │
│  │    │ - Generates ID               │ │                                │
│  │    │ - Returns response           │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ E. Response Received                 │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ Status: 200 OK               │ │                                │
│  │    │ Body:                        │ │                                │
│  │    │   { "id": "123",             │ │                                │
│  │    │     "name": "MacBook",       │ │                                │
│  │    │     "createdAt": "..." }     │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ F. Validate Response (Soft Asserts) │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ softAssert.assertEquals(     │ │                                │
│  │    │   status, 200)               │ │                                │
│  │    │ softAssert.assertNotNull(    │ │                                │
│  │    │   response.id)               │ │                                │
│  │    │ softAssert.assertEquals(     │ │                                │
│  │    │   name, "MacBook")           │ │                                │
│  │    │ softAssert.assertAll()       │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  │              ▼                       │                                │
│  │                                       │                                │
│  │ G. Logging                           │                                │
│  │    ┌──────────────────────────────┐ │                                │
│  │    │ logger.info("Test started")  │ │                                │
│  │    │ logger.info("Request sent")  │ │                                │
│  │    │ logger.info("Response: 200") │ │                                │
│  │    │ logger.info("Test passed")   │ │                                │
│  │    └──────────────────────────────┘ │                                │
│  └──────────────────────────────────────┘                                │
│                    ▼                                                      │
└───────────────────┼──────────────────────────────────────────────────────┘
                    │
┌───────────────────▼──────────────────────────────────────────────────────┐
│ STEP 3: Test Results & Reporting                                         │
│ ──────────────────────────────────────────────────────────────────────   │
│                                                                           │
│  @AfterMethod / @AfterClass                                               │
│  ┌──────────────────────────────────────┐                                │
│  │ 1. Collect Test Results              │                                │
│  │    - Test Name                       │                                │
│  │    - Pass/Fail Status                │                                │
│  │    - Execution Time                  │                                │
│  │    - Assertion Results               │                                │
│  │                                       │                                │
│  │ 2. Generate Reports                  │                                │
│  │    ┌─────────────────────────────┐  │                                │
│  │    │ Allure Report               │  │                                │
│  │    │ - Test steps with details   │  │                                │
│  │    │ - Request/Response data     │  │                                │
│  │    │ - Screenshots/Attachments   │  │                                │
│  │    │ - Timeline & Graphs         │  │                                │
│  │    └─────────────────────────────┘  │                                │
│  │                                       │                                │
│  │    ┌─────────────────────────────┐  │                                │
│  │    │ TestNG Report               │  │                                │
│  │    │ - Summary statistics        │  │                                │
│  │    │ - Passed/Failed tests       │  │                                │
│  │    │ - Suite execution results   │  │                                │
│  │    └─────────────────────────────┘  │                                │
│  │                                       │                                │
│  │    ┌─────────────────────────────┐  │                                │
│  │    │ Console/File Logs           │  │                                │
│  │    │ - Detailed execution logs   │  │                                │
│  │    │ - Request/Response details  │  │                                │
│  │    │ - Error stack traces        │  │                                │
│  │    └─────────────────────────────┘  │                                │
│  │                                       │                                │
│  │ 3. Cleanup                           │                                │
│  │    - Close connections               │                                │
│  │    - Release resources               │                                │
│  └──────────────────────────────────────┘                                │
└──────────────────────────────────────────────────────────────────────────┘
```

### Data Flow Diagram

```
TEST DATA                    EXECUTION                       VALIDATION
─────────                    ─────────                       ──────────

┌──────────┐                                              ┌──────────┐
│  POJO    │                                              │  Soft    │
│ Objects  │───┐                                      ┌───│ Asserts  │
└──────────┘   │                                      │   └──────────┘
               │                                      │
┌──────────┐   │   ┌──────────┐   ┌──────────┐   ┌──┴───┐
│ Builder  │───┼──▶│ Service  │──▶│   REST   │──▶│ JSON │
│ Pattern  │   │   │  Layer   │   │ Assured  │   │Parser│
└──────────┘   │   └──────────┘   └──────────┘   └──┬───┘
               │                         │            │
┌──────────┐   │                         ▼            │   ┌──────────┐
│Test Data │───┘                   ┌──────────┐      └──▶│Response  │
│ (Maps)   │                       │   API    │          │Validation│
└──────────┘                       │ Endpoint │          └──────────┘
                                   └──────────┘
                                        │
                                        ▼
                                   ┌──────────┐          ┌──────────┐
                                   │  HTTP    │          │ Allure   │
                                   │ Response │─────────▶│ Reports  │
                                   └──────────┘          └──────────┘
                                        │
                                        │
                                        ▼
                                   ┌──────────┐          ┌──────────┐
                                   │ Logging  │          │ TestNG   │
                                   │(SLF4J)   │─────────▶│ Reports  │
                                   └──────────┘          └──────────┘
```

### Component Interaction Example

**Scenario: Creating a new object via POST request**

```
[Test Class]                [Service]              [REST Assured]        [API]
     │                          │                         │                 │
     │ 1. Create POJO           │                         │                 │
     │    (Builder pattern)     │                         │                 │
     │                          │                         │                 │
     │ 2. Call service method   │                         │                 │
     ├─────────────────────────▶│                         │                 │
     │  createObject(pojo)      │                         │                 │
     │                          │                         │                 │
     │                          │ 3. Serialize POJO       │                 │
     │                          │    to JSON              │                 │
     │                          │                         │                 │
     │                          │ 4. Build HTTP request   │                 │
     │                          ├────────────────────────▶│                 │
     │                          │  POST /objects          │                 │
     │                          │  Body: JSON             │                 │
     │                          │                         │                 │
     │                          │                         │ 5. Send request │
     │                          │                         ├────────────────▶│
     │                          │                         │  POST /objects  │
     │                          │                         │                 │
     │                          │                         │                 │ 6. Process
     │                          │                         │                 │    & Save
     │                          │                         │                 │
     │                          │                         │ 7. Response     │
     │                          │                         │◀────────────────┤
     │                          │                         │  200 OK + JSON  │
     │                          │                         │                 │
     │                          │ 8. Parse response       │                 │
     │                          │◀────────────────────────┤                 │
     │                          │  Response object        │                 │
     │                          │                         │                 │
     │ 9. Return response       │                         │                 │
     │◀─────────────────────────┤                         │                 │
     │  Response object         │                         │                 │
     │                          │                         │                 │
     │ 10. Validate response    │                         │                 │
     │     - Status code        │                         │                 │
     │     - Response body      │                         │                 │
     │     - Response time      │                         │                 │
     │                          │                         │                 │
     │ 11. Log results          │                         │                 │
     │                          │                         │                 │
     │ 12. Report to TestNG     │                         │                 │
     │     & Allure             │                         │                 │
     │                          │                         │                 │
```

This architecture ensures:
- ✅ **Separation of Concerns** - Each layer has a specific responsibility
- ✅ **Reusability** - Services and utilities can be used across tests
- ✅ **Maintainability** - Changes to API structure only affect service layer
- ✅ **Testability** - Each component can be tested independently
- ✅ **Scalability** - Easy to add new tests, services, or APIs

---

## 🛠️ Prerequisites

### Required Software
- **Java JDK 21 (LTS)** - [Download Here](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6 or higher** - [Download Here](https://maven.apache.org/download.cgi)
- **IDE** (recommended):
  - IntelliJ IDEA Community Edition - [Download Here](https://www.jetbrains.com/idea/download/)
  - Eclipse IDE - [Download Here](https://www.eclipse.org/downloads/)
  - VS Code with Java Extension Pack - [Download Here](https://code.visualstudio.com/)

### Verify Installation
```bash
# ☕ Check Java version (should be 21+)
java -version

# 📦 Check Maven version (should be 3.6+)
mvn -version
```

**✅ Expected Output:**
```
java version "21.0.x"
Apache Maven 3.6.x or higher
```

---

## 📦 Quick Start Installation

### 🚀 4 Steps to Get Started

```
┌───────────────────────────────────────────────────────┐
│  Step 1️⃣  →  Clone Repository                        │
│  Step 2️⃣  →  Install Dependencies                    │
│  Step 3️⃣  →  Run Your First Test                     │
│  Step 4️⃣  →  View Beautiful Reports                  │
└───────────────────────────────────────────────────────┘
```

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/hennamusick/java-rest-assured.git
cd java-rest-assured
```

### 2️⃣ Install Dependencies
```bash
mvn clean install
```
> ⏱️ **Tip:** First run may take 2-3 minutes to download dependencies

### 3️⃣ Run Your First Test
```bash
mvn test -Dtest=ObjectGetTests#testGetObjectById
```
> ✅ **Success:** You should see `Tests run: 1, Failures: 0, Skipped: 0`

### 4️⃣ View Test Results
```bash
mvn allure:serve
```
> 🌐 **Result:** Opens interactive report in your browser automatically

🎉 **Congratulations!** You just ran your first API automation test!

---

## 🚀 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
# Run all User API tests
mvn test -Dtest=UserTests

# Run all Object DELETE tests
mvn test -Dtest=ObjectDeleteTests

# Run all Object POST tests
mvn test -Dtest=ObjectPostTests
```

### Run Single Test Method
```bash
# Run specific test method
mvn test -Dtest=UserTests#testGetUserById

# Run multiple specific tests
mvn test -Dtest=ObjectGetTests#testGetObjectById,testObjectResponseStructure
```

### Run Tests by Priority
```bash
# Run with TestNG XML configuration
mvn test -DsuiteXmlFile=test-suites/testng.xml
```

### Run Tests with Different Log Levels
```bash
# Run with debug logging
mvn test -Dlogback.configurationFile=src/test/resources/logback.xml
```

## 📊 Generate Beautiful Test Reports

### Allure Reports (Recommended)

**Step 1: Run tests**
```bash
mvn clean test
```

**Step 2: Generate and view report**
```bash
mvn allure:serve
```

This will automatically:
- Generate the Allure report
- Start a local web server
- Open the report in your default browser

### TestNG HTML Reports

After running tests, find the HTML report at:
```
target/surefire-reports/index.html
```

### Console Output

View detailed test execution logs in the console with:
- Test names and descriptions
- Request/Response details
- Assertion results
- Execution time

## ⚙️ CI/CD Pipeline (GitHub Actions)

- **Triggers:** Runs on both `push` and `pull_request` events.
- **Build:** Uses `actions/setup-java@v4` with Java 21 (LTS) and Maven cache.
- **Tests:** Executes `mvn -B -U -e -DskipITs=true test` with TestNG.
- **Reports:** Generates Allure via CLI (`allure generate`) and deploys to GitHub Pages on `push`.
- **PRs:** Pull requests run tests and artifacts. Report deployment to GitHub Pages is skipped, so PRs won’t include a live report link; download the `allure-results` artifact and run `allure serve` locally.

How to view CI results:
- Check workflow runs under GitHub “Actions”.
- Allure report (when enabled) publishes to GitHub Pages: `https://your-username.github.io/java-rest-assured/`.
- To enable Pages, set repository Settings → Pages → Branch: `gh-pages` (root).

### Auto-PR Workflow (auto-pr-develop-to-main.yml)

**Purpose:** Automatically create and maintain a pull request from `develop` to `main`.

**How it works:**
1. Triggers on every push to `develop` branch
2. Checks if `develop` branch is ahead of `main`
3. If ahead → Creates/updates PR automatically using `peter-evans/create-pull-request@v5`
4. If branches are in sync → No PR created (expected behavior)

#### GitHub Actions Auto-PR Approaches

Automated PR creation in GitHub Actions is generally achieved using:

1. **Third-party Actions** (Recommended)
   - `peter-evans/create-pull-request` - Widely used, well-documented solution
   - Handles edge cases and permissions automatically
   - Supports idempotent operations (won't create duplicates)

2. **GitHub CLI** (`gh pr create`)
   - Direct command-line approach
   - Requires explicit permission configuration
   - Note: `GITHUB_TOKEN` has security restrictions preventing PR creation via CLI

3. **GitHub API** (Advanced)
   - Via `actions/github-script` or custom scripts
   - Maximum flexibility but more complex
   - Requires manual duplicate prevention logic

#### Common Use Cases

- **Branch Synchronization**: Creating PR from `develop` to `main` (this project)
- **Dependency Updates**: Auto-PRs when Dependabot finds new versions
- **Release Management**: PR from release branch back to main after hotfix
- **Automated Content**: PRs when scripts update docs, data files, or generated code

#### Configuration

This project uses `peter-evans/create-pull-request@v5`:

```yaml
- uses: peter-evans/create-pull-request@v5
  with:
    token: ${{ secrets.GITHUB_TOKEN }}
    title: 'chore: merge develop into main'
    base: main
    branch: develop-to-main-pr
    delete-branch: false
    labels: automated
```

**Key Settings:**
- **base**: Target branch (`main`)
- **branch**: Tracking branch created for PR (`develop-to-main-pr`)
- **delete-branch**: Keep tracking branch after PR merge
- **labels**: Tag PR with `automated` label
- **Permissions Required**: `contents: write`, `pull-requests: write`

#### Usage

```bash
# Make and push changes to develop
git checkout develop
git commit -m "feat: add new feature"
git push origin develop

# Workflow automatically creates/updates PR
# No manual action needed!
```

#### Key Setup Steps

1. **Define Trigger**: Workflow runs on `push` to `develop` branch
2. **Check Differences**: Action compares `develop` vs `main`
3. **Create PR**: If branches differ, creates/updates PR automatically
4. **Manage Permissions**: Workflow has `contents: write` and `pull-requests: write`

#### Monitoring

- **Actions tab** → "Auto PR develop to main"
- Check workflow run status (✅ Success or ❌ Failed)
- View PR in **Pull Requests** tab
- PR automatically updates when pushing new commits to develop

#### Expected Behavior

**When PR is Created:**
```
Branch 'develop-to-main-pr' created
Pull request #X created: chore: merge develop into main
```

**When Branches Are Synced:**
```
Branch 'develop-to-main-pr' is not ahead of base 'main' and will not be created
```
*This is normal - no PR needed when branches are identical*

#### Debugging

Check workflow logs for these indicators:

**Successful PR Creation:**
```
Creating pull request for develop-to-main-pr:main
Pull request #X (chore: merge develop into main) created
```

**No Changes to Merge:**
```
Branch not ahead of base
No pull request will be created
```

**Permission Issues:**
```
Error: Resource not accessible by integration
Solution: Verify permissions in workflow YAML
```

---

## 📊 GitHub Actions Workflow Detailed Design

### 🏗️ Complete Workflow Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         GITHUB EVENTS (Triggers)                             │
│  ┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐          │
│  │  📤 Push Event   │  │  📥 Pull Request │  │  ⚙️  Manual      │          │
│  │  (main/develop)  │  │  (main/develop)  │  │  (workflow_dispatch)        │
│  └──────────────────┘  └──────────────────┘  └──────────────────┘          │
│         │                      │                        │                   │
│         └──────────────────────┴────────────────────────┘                   │
│                                │                                            │
│                        ┌───────▼────────┐                                  │
│                        │  Trigger Job   │                                  │
│                        └───────┬────────┘                                  │
└────────────────────────────────┼──────────────────────────────────────────────┘
                                 │
                  ┌──────────────▼──────────────────┐
                  │   🏗️ BUILD-AND-TEST JOB         │
                  │   (ubuntu-latest)               │
                  │   Environment: Java 21 + Maven  │
                  └──────────────┬──────────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
         ▼                       ▼                       ▼
    ┌─────────┐           ┌──────────┐          ┌────────────┐
    │ Step 1  │           │ Step 2   │          │  Step 3    │
    │📥 Clone │           │  ☕ Java │          │ 📦 Maven   │
    │Repo     │           │  Setup   │          │ Compile    │
    └────┬────┘           └────┬─────┘          └─────┬──────┘
         │                     │                      │
         └─────────────────────┼──────────────────────┘
                               │
                ┌──────────────▼──────────────┐
                │ Step 4: Run Tests (TestNG) │
                │ 🧪 mvn test                │
                │ 📊 Generate test-results  │
                │ 📄 Generate allure-results │
                └──────────────┬──────────────┘
                               │
         ┌─────────────────────┼─────────────────────┐
         │                     │                     │
         ▼                     ▼                     ▼
    ┌─────────┐          ┌──────────┐         ┌──────────┐
    │ Step 5  │          │ Step 6   │         │ Step 7   │
    │📋 Test  │          │📊 Allure │         │📈 Upload │
    │Summary  │          │Generate  │         │Artifacts │
    └────┬────┘          └────┬─────┘         └──────┬───┘
         │                    │                      │
         └────────────────────┼──────────────────────┘
                              │
              ┌───────────────▼────────────────┐
              │ Decision Point: Branch Check   │
              │  Is it a PUSH to main/develop? │
              └───────────────┬────────────────┘
                              │
              ┌───────────────┴───────────────┐
              │                               │
          YES │                          NO   │
              ▼                               ▼
    ┌──────────────────┐         ┌─────────────────────┐
    │ 📄 If PUSH:      │         │ 📄 If PR:           │
    │ • Deploy Allure  │         │ • Skip deployment   │
    │ • GitHub Pages   │         │ • Store artifacts   │
    │ • Update gh-pages│         │ (for download)      │
    └──────────────────┘         └─────────────────────┘
             │                              │
             └──────────────┬───────────────┘
                            │
                   ┌────────▼────────┐
                   │ ✅ Workflow     │
                   │    Complete     │
                   └─────────────────┘
```

### 🔄 Workflow Data Flow

```
┌──────────────────┐
│ GitHub Source    │
│ (main/develop)   │
└────────┬─────────┘
         │
         ▼
    ┌─────────────────┐
    │  Checkout Job   │
    │ Clones code     │
    └────────┬────────┘
             │
             ▼
    ┌──────────────────────────┐
    │  Java 21 + Maven Setup   │
    │  (with Maven cache)      │
    └────────┬─────────────────┘
             │
             ▼
    ┌────────────────────────────┐
    │  Compile Source Code       │
    │  src/main/java → classes/  │
    └────────┬───────────────────┘
             │
             ▼
    ┌──────────────────────────────┐
    │  Execute TestNG Tests        │
    │  48 test cases               │
    │  Parallel execution          │
    └────────┬─────────────────────┘
             │
        ┌────┴─────┐
        │           │
        ▼           ▼
┌──────────────┐  ┌─────────────────┐
│TestNG Report │  │Allure Results   │
│target/       │  │allure-results/  │
│surefire-     │  │                 │
│reports/      │  └────────┬────────┘
└──────────────┘           │
                           ▼
                ┌──────────────────────┐
                │ Generate Allure HTML │
                │ Graphs, Timeline,    │
                │ Test Steps           │
                └──────────┬───────────┘
                           │
                  ┌────────▼────────┐
                  │ Decision: PUSH? │
                  └────────┬────────┘
                           │
           ┌───────────────┴──────────────┐
           │                              │
          YES                            NO
           │                              │
           ▼                              ▼
    ┌────────────────┐          ┌──────────────────┐
    │Deploy to Pages │          │Store Artifact    │
    │gh-pages branch │          │(90 day retention)│
    │                │          │                  │
    │🌐 Live Report  │          │📥 Downloadable   │
    └────────────────┘          └──────────────────┘
```

### 📊 Performance Metrics & Job Details

| Step | Duration | Details |
|------|----------|---------|
| 📥 Checkout | 5-10s | Clone repo + fetch history |
| ☕ Java Setup | 15-30s | Download Java 21 + Maven cache |
| 🔨 Compile | 10-60s | Cache dependent (first slower) |
| 🧪 Tests | 60-90s | 48 tests, parallel execution |
| 📊 Reports | 15-20s | TestNG + Allure generation |
| 🚀 Deploy | 20-30s | Upload to gh-pages (push only) |
| **Total** | **⏱️ 2-5 min** | **Push: slower** / **PR: faster** |

### 🎯 Key Workflow Features

| Feature | Purpose | Benefit |
|---------|---------|---------|
| 📦 Maven Cache | Avoid re-downloading deps | 50-70% faster builds |
| 🔐 Scoped Permissions | Security best practice | Least privilege access |
| ✅ Continue on Error | Tests don't block pipeline | Reports always generated |
| 🌐 GitHub Pages | Live report hosting | Accessible anywhere |
| 📋 Dual Reports | TestNG + Allure | Comprehensive analysis |
| 🔄 Branch Filtering | Control execution | Only main/develop |
| ⚡ Java 21 LTS | Latest stable version | Modern features + security |
| 📄 Artifacts | Store results | Download & analyze locally |

---

## 📖 Understanding the Framework - Beginner's Guide

### What is API Testing?

**API (Application Programming Interface)** testing involves testing the communication between different software components. Unlike UI testing (clicking buttons), API testing:
- Tests the backend logic directly
- Is faster and more reliable
- Can be automated easily
- Catches bugs early in development

### What is REST?

**REST (Representational State Transfer)** is an architectural style for designing networked applications. REST APIs use HTTP methods:

| HTTP Method | Purpose | Example |
|------------|---------|---------|
| **GET** | Retrieve data | Get user by ID |
| **POST** | Create new data | Create new user |
| **PUT** | Update entire resource | Update user completely |
| **PATCH** | Update partial resource | Update user email only |
| **DELETE** | Remove data | Delete user |

### What is REST Assured?

**REST Assured** is a Java library that simplifies testing REST APIs. It provides:
- Easy-to-read syntax (fluent API)
- Built-in JSON/XML parsing
- Request/Response validation
- Authentication support
- Header management

### What is Page Object Model (POM)?

**POM** is a design pattern that:
- Separates test logic from API endpoints
- Makes code reusable and maintainable
- Reduces code duplication
- Makes tests easier to read

**Example:**
```java
// ❌ Without POM (messy)
given().get("/users/1").then().statusCode(200);

// ✅ With POM (clean)
Response response = userService.getUserById(1);
assertThat(response.getStatusCode()).isEqualTo(200);
```

### What are POJOs?

**POJO (Plain Old Java Object)** - Simple Java classes used to represent data:

```java
// POJO for User
@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
}

// Using POJO
User user = User.builder()
    .name("John")
    .email("john@example.com")
    .build();
```

**Benefits:**
- Type-safe (compile-time checking)
- Auto-completion in IDE
- Easy JSON conversion
- Clean, readable code

---

## 🔧 Configuration

### Project Configuration

Edit `src/test/resources/config.properties` to configure:

```properties
# Base URL for JSONPlaceholder API
base.uri=https://jsonplaceholder.typicode.com

# Request timeout in seconds
timeout=30

# Environment name
environment=dev
```

### Logging Configuration

Edit `src/test/resources/logback.xml` for logging settings:

```xml
<configuration>
    <!-- Console logging -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <!-- Set log level: TRACE, DEBUG, INFO, WARN, ERROR -->
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

### TestNG Configuration

Edit `testng.xml` to configure test suites:

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="API Test Suite">
    <test name="Object API Tests">
        <classes>
            <class name="com.api.automation.tests.endpoints.restfulapi.ObjectGetTests"/>
            <class name="com.api.automation.tests.endpoints.restfulapi.ObjectPostTests"/>
            <class name="com.api.automation.tests.endpoints.restfulapi.ObjectDeleteTests"/>
        </classes>
    </test>
</suite>
```

---

## 📝 Code Examples - Step by Step

### Example 1: Simple GET Request (Beginner)

```java
@Test
public void testGetUserById() {
    // Step 1: Initialize the service
    UserService userService = new UserService();
    
    // Step 2: Make GET request
    Response response = userService.getUserById(1);
    
    // Step 3: Validate response
    Assert.assertEquals(response.getStatusCode(), 200);
    Assert.assertNotNull(response.jsonPath().getString("name"));
    
    System.out.println("User name: " + response.jsonPath().getString("name"));
}
```

**What's happening:**
1. Created a service object to interact with User API
2. Called `getUserById(1)` which sends `GET /users/1`
3. Validated status code is 200 (Success)
4. Checked that name field exists
5. Printed the user name

### Example 2: POST Request with POJO (Intermediate)

```java
@Test
public void testCreateUser() {
    // Step 1: Create POJO object using Builder pattern
    User newUser = User.builder()
        .name("John Doe")
        .username("johndoe")
        .email("john@example.com")
        .build();
    
    // Step 2: Initialize service
    UserService userService = new UserService();
    
    // Step 3: Send POST request
    Response response = userService.createUser(newUser);
    
    // Step 4: Validate response
    Assert.assertEquals(response.getStatusCode(), 201); // 201 = Created
    Assert.assertNotNull(response.jsonPath().getString("id"));
    
    System.out.println("Created user ID: " + response.jsonPath().getString("id"));
}
```

**What's happening:**
1. Used Builder pattern to create User object (clean code!)
2. Sent POST request with User object (auto-converted to JSON)
3. Validated 201 status (resource created)
4. Verified server assigned an ID

### Example 3: Advanced with Soft Assertions (Advanced)

```java
@Test
public void testGetObjectWithSoftAssertions() {
    // Initialize
    SoftAssert softAssert = new SoftAssert();
    ObjectService objectService = new ObjectService();
    
    // Make request
    Response response = objectService.getObjectById("6");
    
    // Multiple assertions - ALL will execute even if one fails!
    softAssert.assertEquals(response.getStatusCode(), 200, "Status should be 200");
    softAssert.assertNotNull(response.jsonPath().getString("id"), "ID should exist");
    softAssert.assertNotNull(response.jsonPath().getString("name"), "Name should exist");
    softAssert.assertTrue(response.getTime() < 3000, "Response time should be < 3000ms");
    softAssert.assertTrue(
        response.getContentType().contains("application/json"), 
        "Content type should be JSON"
    );
    
    // Execute all assertions at once
    softAssert.assertAll();
}
```

**Benefits of Soft Assertions:**
- ✅ All assertions run (doesn't stop at first failure)
- ✅ See all validation issues at once
- ✅ Better for comprehensive testing
- ✅ Saves time debugging

### Example 4: Complete CRUD Operations

```java
public class CompleteCRUDExample {
    
    @Test(priority = 1)
    public void testCreate() {
        // CREATE - POST
        ApiObject newObject = ApiObject.builder()
            .name("Apple MacBook Pro 16")
            .data(Map.of("year", 2023, "price", 2499.99))
            .build();
        
        Response response = objectService.createObject(newObject);
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Save ID for next tests
        String objectId = response.jsonPath().getString("id");
        System.out.println("Created object with ID: " + objectId);
    }
    
    @Test(priority = 2)
    public void testRead() {
        // READ - GET
        Response response = objectService.getObjectById("6");
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Extract data
        String name = response.jsonPath().getString("name");
        System.out.println("Retrieved object: " + name);
    }
    
    @Test(priority = 3)
    public void testUpdate() {
        // UPDATE - PUT (full update)
        ApiObject updateObject = ApiObject.builder()
            .name("Apple MacBook Pro 16 (Updated)")
            .data(Map.of("year", 2024, "price", 2699.99))
            .build();
        
        Response response = objectService.updateObject("6", updateObject);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 4)
    public void testPartialUpdate() {
        // PARTIAL UPDATE - PATCH (partial update)
        ApiObject patchObject = ApiObject.builder()
            .name("MacBook Pro 16 - Special Edition")
            .build();
        
        Response response = objectService.patchObject("6", patchObject);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    
    @Test(priority = 5)
    public void testDelete() {
        // DELETE
        Response response = objectService.deleteObject("6");
        Assert.assertEquals(response.getStatusCode(), 200);
        
        String message = response.jsonPath().getString("message");
        Assert.assertTrue(message.contains("deleted"));
    }
}
```

### Example 5: Using POJO for Response Deserialization

```java
@Test
public void testUserAsPOJO() {
    // Get response as POJO object directly
    User user = userService.getUserByIdAsObject(1);
    
    // Now use object properties (type-safe!)
    Assert.assertNotNull(user.getName());
    Assert.assertNotNull(user.getEmail());
    
    // Access nested objects
    Assert.assertNotNull(user.getAddress());
    Assert.assertNotNull(user.getAddress().getCity());
    
    // Print details
    System.out.println("User: " + user.getName());
    System.out.println("Email: " + user.getEmail());
    System.out.println("City: " + user.getAddress().getCity());
}
```

**Why use POJOs for responses:**
- ✅ Type-safe access to fields
- ✅ IDE auto-completion
- ✅ Compile-time error checking
- ✅ Cleaner code
- ✅ Easy to work with nested structures

---

## 🏗️ Framework Architecture Explained

### Service Layer (POM)
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

## 📚 Comprehensive Classes & Methods Documentation

### Service Layer Classes

#### BaseService.java
**Purpose:** Abstract base class providing common HTTP operations for all services.

**Key Methods:**
```java
// GET requests
public Response get(String endpoint)
  └─ Sends GET request to specified endpoint
  └─ Returns: Response object with status, headers, body
  └─ Example: get("/users") → GET https://api.example.com/users

// POST requests  
public Response post(String endpoint, Object body)
  └─ Sends POST request with JSON body
  └─ Returns: Response object (usually with 201 status for creation)
  └─ Example: post("/users", userObject) → Creates new user

// PUT requests (Full update)
public Response put(String endpoint, Object body)
  └─ Sends PUT request to replace entire resource
  └─ Returns: Updated resource in response
  └─ Example: put("/users/1", updatedUser) → Replaces user #1

// PATCH requests (Partial update)
public Response patch(String endpoint, Object body)
  └─ Sends PATCH request to partially update resource
  └─ Returns: Partially updated resource
  └─ Example: patch("/users/1", partialData) → Updates user #1 fields

// DELETE requests
public Response delete(String endpoint)
  └─ Sends DELETE request to remove resource
  └─ Returns: Response with 200 or 204 status
  └─ Example: delete("/users/1") → Removes user #1

// Helper Methods
private void logRequestDetails(String method, String uri, Object body)
  └─ Logs HTTP method, URI, and request body
  └─ Used internally for debugging

private void logResponseDetails(Response response)
  └─ Logs status code, headers, and response body
  └─ Used internally for verification
```

**Usage Example:**
```java
public class UserService extends BaseService {
    public Response getUserById(int userId) {
        return get("/users/" + userId);  // Uses BaseService.get()
    }
    
    public Response createUser(User user) {
        return post("/users", user);     // Uses BaseService.post()
    }
}
```

---

#### UserService.java
**Purpose:** API service for User endpoint operations.

**Key Methods:**
```java
// READ Operations
public Response getAllUsers()
  └─ Retrieves all users from JSONPlaceholder
  └─ Returns: Response with array of User objects
  └─ Endpoint: GET /users
  └─ Status: 200 OK

public Response getUserById(int userId)
  └─ Retrieves specific user by ID
  └─ Parameter: userId (1-10)
  └─ Returns: Single User object
  └─ Endpoint: GET /users/{id}
  └─ Status: 200 OK

public User getUserByIdAsObject(int userId)
  └─ Retrieves user and converts to POJO
  └─ Parameter: userId
  └─ Returns: User object (not Response)
  └─ Useful for: Direct object manipulation

// CREATE Operation
public Response createUser(User user)
  └─ Creates new user
  └─ Parameter: User object with name, email, address, etc.
  └─ Returns: Response with created user and new ID
  └─ Endpoint: POST /users
  └─ Status: 201 Created
  └─ Example:
    User newUser = User.builder()
        .name("Jane Doe")
        .email("jane@example.com")
        .build();
    Response response = userService.createUser(newUser);

// UPDATE Operations
public Response updateUser(int userId, User user)
  └─ Updates entire user record (PUT)
  └─ Parameters: userId, updated User object
  └─ Returns: Updated User in response
  └─ Endpoint: PUT /users/{id}
  └─ Status: 200 OK
  └─ Note: PUT replaces entire resource

public Response partialUpdateUser(int userId, User user)
  └─ Updates specific user fields (PATCH)
  └─ Parameters: userId, partial User object
  └─ Returns: Partially updated User
  └─ Endpoint: PATCH /users/{id}
  └─ Status: 200 OK
  └─ Note: PATCH updates only provided fields

// DELETE Operation
public Response deleteUser(int userId)
  └─ Deletes user from system
  └─ Parameter: userId
  └─ Returns: Empty response body
  └─ Endpoint: DELETE /users/{id}
  └─ Status: 200 OK
  └─ Caution: Cannot undo deletion
```

---

#### PostService.java
**Purpose:** API service for Post endpoint operations.

**Key Methods:**
```java
// READ Operations
public Response getAllPosts()
  └─ Retrieves all posts (100 posts total)
  └─ Returns: Response with Post array
  └─ Endpoint: GET /posts
  └─ Status: 200 OK

public Response getPostById(int postId)
  └─ Retrieves specific post by ID
  └─ Parameter: postId (1-100)
  └─ Returns: Single Post object
  └─ Endpoint: GET /posts/{id}
  └─ Status: 200 OK

public Post getPostByIdAsObject(int postId)
  └─ Retrieves post as POJO object
  └─ Parameter: postId
  └─ Returns: Post object (not Response)
  └─ Usage: Direct object access without Response parsing

public Response getPostsByUserId(int userId)
  └─ Retrieves all posts by specific user
  └─ Parameter: userId (1-10)
  └─ Returns: Array of User's posts
  └─ Endpoint: GET /posts?userId={userId}
  └─ Status: 200 OK

// CREATE Operation
public Response createPost(Post post)
  └─ Creates new post
  └─ Parameter: Post object (userId, title, body)
  └─ Returns: Response with new post and ID
  └─ Endpoint: POST /posts
  └─ Status: 201 Created

// UPDATE Operations
public Response updatePost(int postId, Post post)
  └─ Full update of post (PUT)
  └─ Parameters: postId, updated Post object
  └─ Returns: Updated post
  └─ Endpoint: PUT /posts/{id}
  └─ Status: 200 OK

public Response partialUpdatePost(int postId, Post post)
  └─ Partial update of post (PATCH)
  └─ Parameters: postId, partial Post object
  └─ Returns: Partially updated post
  └─ Endpoint: PATCH /posts/{id}
  └─ Status: 200 OK

// DELETE Operation
public Response deletePost(int postId)
  └─ Deletes post
  └─ Parameter: postId
  └─ Returns: Empty response
  └─ Endpoint: DELETE /posts/{id}
  └─ Status: 200 OK
```

---

#### ObjectService.java
**Purpose:** API service for restful-api.dev Object endpoints.

**Key Methods:**
```java
// CREATE Operation
public Response createObject(ApiObject object)
  └─ Creates new object resource
  └─ Parameter: ApiObject with name and data (Map)
  └─ Returns: Created object with ID
  └─ Endpoint: POST /objects
  └─ Status: 201 Created
  └─ Example:
    Map<String, Object> data = new HashMap<>();
    data.put("year", 2023);
    data.put("price", 2499.99);
    
    ApiObject obj = ApiObject.builder()
        .name("MacBook Pro")
        .data(data)
        .build();
    
    Response response = objectService.createObject(obj);

// READ Operations
public Response getObject(String objectId)
  └─ Retrieves specific object by ID
  └─ Parameter: objectId (UUID string)
  └─ Returns: ApiObject with all properties
  └─ Endpoint: GET /objects/{id}
  └─ Status: 200 OK

public Response getAllObjects()
  └─ Retrieves all objects
  └─ Returns: Array of all objects
  └─ Endpoint: GET /objects
  └─ Status: 200 OK

public Response getObjectsByIds(List<String> ids)
  └─ Retrieves specific objects by ID list
  └─ Parameter: List of object IDs
  └─ Returns: Array of matching objects
  └─ Endpoint: GET /objects?id=id1&id=id2&id=id3
  └─ Status: 200 OK
  └─ Usage: Query multiple objects efficiently

// UPDATE Operations
public Response updateObject(String objectId, ApiObject object)
  └─ Full update of object (PUT)
  └─ Parameters: objectId, updated ApiObject
  └─ Returns: Updated object
  └─ Endpoint: PUT /objects/{id}
  └─ Status: 200 OK
  └─ Note: Replaces entire object

public Response partialUpdateObject(String objectId, ApiObject object)
  └─ Partial update of object (PATCH)
  └─ Parameters: objectId, partial ApiObject
  └─ Returns: Partially updated object
  └─ Endpoint: PATCH /objects/{id}
  └─ Status: 200 OK

// DELETE Operation
public Response deleteObject(String objectId)
  └─ Deletes object
  └─ Parameter: objectId
  └─ Returns: Confirmation message
  └─ Endpoint: DELETE /objects/{id}
  └─ Status: 200 OK
```

---

### Model Layer Classes (POJOs)

#### User.java
**Purpose:** POJO representing User resource with nested objects.

**Fields:**
```java
private int id                          // User ID (auto-generated)
private String name                     // User's full name
private String username                 // Login username
private String email                    // User's email address
private Address address                 // Nested Address object
private String phone                    // Phone number
private String website                  // Website URL
private Company company                 // Nested Company object

// Nested Classes
static class Address {
    String street, suite, city, zipcode
    Geo geo (latitude, longitude)
}

static class Company {
    String name, catchPhrase, bs
}

// Lombok Annotations
@Data                                   // Generates getters, setters, toString, equals, hashCode
@Builder                                // Generates builder pattern
@NoArgsConstructor                      // Generates no-arg constructor
@AllArgsConstructor                     // Generates all-arg constructor
```

**Usage Example:**
```java
// Create user with builder
User user = User.builder()
    .name("John Doe")
    .email("john@example.com")
    .phone("1-770-736-8031")
    .build();

// Update user
user.setName("Jane Doe");
String name = user.getName();
```

---

#### Post.java
**Purpose:** POJO representing Post resource.

**Fields:**
```java
private int userId                      // ID of post creator (1-10)
private int id                          // Post ID (auto-generated)
private String title                    // Post title
private String body                     // Post content/body
```

**Usage Example:**
```java
// Create post with builder
Post post = Post.builder()
    .userId(1)
    .title("What is REST API?")
    .body("REST API is an architectural style...")
    .build();

// Use post object
int userId = post.getUserId();
String title = post.getTitle();
```

---

#### ApiObject.java
**Purpose:** POJO for dynamic object with flexible data structure.

**Fields:**
```java
private String id                       // Object ID (UUID)
private String name                     // Object name
private Map<String, Object> data        // Dynamic properties (flexible)

// Lombok Annotations
@Data                                   // Auto-generates common methods
@Builder                                // Builder pattern support
```

**Usage Example:**
```java
// Create object with flexible data
Map<String, Object> specs = new HashMap<>();
specs.put("year", 2023);
specs.put("price", 2499.99);
specs.put("color", "Space Gray");

ApiObject laptop = ApiObject.builder()
    .name("MacBook Pro")
    .data(specs)
    .build();

// Access dynamic properties
String year = (String) laptop.getData().get("year");
```

---

### Utility Layer Classes

#### TestDataProvider.java
**Purpose:** Centralized hub for all test data and data provider methods.

**Core Methods:**
```java
@DataProvider(name = "userIds")
public Object[][] getUserIds()
  └─ Provides test data for user ID parameterization
  └─ Returns: {{1}, {2}, {3}, {5}, {10}}
  └─ Usage: @Test(dataProvider = "userIds") void testUser(int userId)
  └─ Executes: Test runs 5 times with different user IDs

@DataProvider(name = "postIds")
public Object[][] getPostIds()
  └─ Provides test data for post ID parameterization
  └─ Returns: {{1}, {2}, {5}, {10}, {50}}
  └─ Usage: Multiple test executions with post data

@DataProvider(name = "paginationParams")
public Object[][] getPaginationParams()
  └─ Provides pagination parameter combinations
  └─ Returns: {{1, 5}, {2, 10}, {3, 20}}
  └─ Usage: Test pagination with different page/size combos

@DataProvider(name = "userCounts")
public Object[][] getUserCounts()
  └─ Provides different user count scenarios
  └─ Returns: {{5}, {10}, {20}}
  └─ Usage: Test with various dataset sizes

public Object[][] getTestIdsFromCsv()
  └─ Loads test data from testdata.csv file
  └─ Returns: {{email1, password1}, {email2, password2}, ...}
  └─ File Location: src/test/resources/testdata.csv
  └─ Usage: CSV-driven parameterization

public Object[][] getTestConfigFromJson()
  └─ Loads configuration from testdata.json
  └─ Returns: Test configuration objects
  └─ File Location: src/test/resources/testdata.json

public static User[] getUsersFromJson()
  └─ Loads User array from testdata.json
  └─ Returns: Array of User POJOs
  └─ Usage: Pre-configured test users

public static String getProperty(String key, String defaultValue)
  └─ Retrieves property from testdata.properties
  └─ Parameters: property key, default if not found
  └─ Returns: Property value or default
  └─ Example: getProperty("admin.username", "admin")
```

**Nested Classes:**
```java
// TestConstants - Configuration values and constants
static class TestConstants {
    static class StatusCodes {
        int OK = 200;                  // Successful GET/PUT/PATCH/DELETE
        int CREATED = 201;             // Successful POST
        int BAD_REQUEST = 400;         // Invalid request
        int NOT_FOUND = 404;           // Resource not found
        int SERVER_ERROR = 500;        // Server error
    }
    
    static class ApiConfig {
        String BASE_URL_JSONPLACEHOLDER;  // JSONPlaceholder base URL
        String BASE_URL_RESTFUL;          // Restful-api.dev base URL
        int TIMEOUT = 5000;               // Request timeout (ms)
    }
    
    static class ValidationValues {
        int MIN_USER_ID = 1;
        int MAX_USER_ID = 10;
        int MIN_POST_ID = 1;
        int MAX_POST_ID = 100;
    }
}

// TestIdBuilder - Fluent builder for test data
class TestIdBuilder {
    public TestIdBuilder withUserId(int id)
        └─ Sets user ID
        └─ Returns: this (for chaining)
    
    public TestIdBuilder withPostId(int id)
        └─ Sets post ID
        └─ Returns: this
    
    public TestIdBuilder withName(String name)
        └─ Sets name
        └─ Returns: this
    
    public Object[] build()
        └─ Builds final test data
        └─ Returns: Object[] for parameterization
}

// TestScenario - Enum for test scenarios
enum TestScenario {
    HAPPY_PATH,          // Normal flow
    EDGE_CASE,           // Boundary conditions
    INVALID_DATA,        // Invalid inputs
    BOUNDARY,            // Edge values
    PERFORMANCE          // Performance testing
}
```

---

#### RestClient.java
**Purpose:** Configures REST Assured specifications for HTTP requests/responses.

**Key Methods:**
```java
public static RequestSpecification getRequestSpec()
  └─ Returns: Configured RequestSpecification
  └─ Includes: Base URL, headers, content type
  └─ Usage: All service classes use this for requests
  └─ Example:
    RequestSpecification spec = RestClient.getRequestSpec();
    Response response = given()
        .spec(spec)
        .get("/users/1");

public static ResponseSpecification getResponseSpec()
  └─ Returns: Configured ResponseSpecification
  └─ Includes: Default assertions and validations
  └─ Usage: Validates responses automatically

public static RequestSpecBuilder buildCustomRequest(...)
  └─ Creates custom request specification
  └─ Parameters: Headers, params, body
  └─ Returns: Customized RequestSpecification
```

---

#### JsonUtils.java
**Purpose:** Utilities for JSON serialization, deserialization, and formatting.

**Key Methods:**
```java
public static String serialize(Object object)
  └─ Converts Java object to JSON string
  └─ Parameter: Any Java object
  └─ Returns: Formatted JSON string
  └─ Example:
    User user = new User(...);
    String json = JsonUtils.serialize(user);
    // Output: {"id":1,"name":"John","email":"john@example.com"}

public static <T> T deserialize(String json, Class<T> type)
  └─ Converts JSON string to Java object
  └─ Parameters: JSON string, target class
  └─ Returns: Deserialized object
  └─ Example:
    String json = "{\"id\":1,\"name\":\"John\"}";
    User user = JsonUtils.deserialize(json, User.class);

public static String prettyPrint(String json)
  └─ Formats JSON with indentation and line breaks
  └─ Parameter: JSON string
  └─ Returns: Formatted JSON for readability
  └─ Usage: Logging formatted responses

public static Object getJsonPathValue(String json, String path)
  └─ Extracts value from JSON using JSONPath
  └─ Parameters: JSON string, JSONPath expression
  └─ Returns: Extracted value
  └─ Example:
    String value = JsonUtils.getJsonPathValue(json, "$.name");
```

---

#### TestUtils.java
**Purpose:** Helper utilities for test execution.

**Key Methods:**
```java
public static void printSeparator()
  └─ Prints separator line for readability
  └─ Usage: Organize console output

public static void waitFor(long milliseconds)
  └─ Pauses test execution
  └─ Parameter: Milliseconds to wait
  └─ Usage: Handle asynchronous operations

public static void verifyResponseTime(long actualTime, long maxTime)
  └─ Asserts response time is within limit
  └─ Parameters: Actual time, max allowed time
  └─ Usage: Performance validation

public static boolean isValidEmail(String email)
  └─ Validates email format
  └─ Parameter: Email string
  └─ Returns: true if valid, false otherwise

public static List<String> extractEmails(List<User> users)
  └─ Extracts emails from user list
  └─ Parameter: User list
  └─ Returns: List of email addresses
```

---

#### ConfigManager.java
**Purpose:** Singleton configuration manager for application settings.

**Key Methods:**
```java
public static ConfigManager getInstance()
  └─ Returns: Single ConfigManager instance (Singleton)
  └─ Usage: ConfigManager.getInstance().getProperty(...)

public String getProperty(String key)
  └─ Retrieves property from config.properties
  └─ Parameter: Property key
  └─ Returns: Property value
  └─ Example: getInstance().getProperty("base.url")

public String getBaseUrl()
  └─ Returns: Base URL for API
  └─ Usage: Service initialization

public int getTimeout()
  └─ Returns: Request timeout value
  └─ Usage: HTTP request configuration

public void loadProperties(String propertiesFile)
  └─ Loads properties from file
  └─ Parameter: File path
  └─ Usage: Initialize with custom properties
```

---

### Test Layer Classes

#### BaseTest.java
**Purpose:** Base class with setup/teardown for all tests.

**Key Methods:**
```java
@BeforeClass
public void setUp()
  └─ Runs once before all tests in class
  └─ Initializes: Logger, Services, Assertions
  └─ Usage: Common initialization

@BeforeMethod
public void testSetup()
  └─ Runs before each test method
  └─ Initializes: Test-specific resources
  └─ Usage: Per-test setup

@AfterMethod
public void tearDown()
  └─ Runs after each test method
  └─ Cleanup: Test-specific resources
  └─ Usage: Per-test cleanup

@AfterClass
public void tearDownClass()
  └─ Runs once after all tests
  └─ Cleanup: Global resources
  └─ Usage: Final cleanup
```

---

#### UserTests.java
**Purpose:** Test cases for User API endpoints.

**Test Methods with Data Providers:**
```java
@DataProvider(name = "userIds")
public Object[][] provideUserIds()
  └─ Returns: {{1}, {2}, {3}, {5}, {10}}
  └─ Parameterizes: 5 different user IDs

@Test(dataProvider = "userIds")
public void testGetUserById(int userId)
  └─ Executes 5 times (once per user ID)
  └─ Validates: User retrieval

@DataProvider(name = "userCounts")
public Object[][] provideUserCounts()
  └─ Returns: {{5}, {10}, {20}}

@Test(dataProvider = "userCounts")
public void testGetUserCount(int count)
  └─ Tests with different dataset sizes
```

**Additional Test Methods:**
```java
@Test
public void testCreateUser()
  └─ Tests POST /users endpoint
  └─ Validates: New user creation

@Test
public void testUpdateUser()
  └─ Tests PUT /users/{id} endpoint
  └─ Validates: Full user update

@Test
public void testPartialUpdateUser()
  └─ Tests PATCH /users/{id} endpoint
  └─ Validates: Partial user update

@Test
public void testDeleteUser()
  └─ Tests DELETE /users/{id} endpoint
  └─ Validates: User deletion
```

---

#### PostTests.java
**Purpose:** Test cases for Post API endpoints.

**Test Methods with Parameterization:**
```java
@DataProvider(name = "postIds")
public Object[][] providePostIds()
  └─ Parameterizes post IDs for multiple executions

@DataProvider(name = "userIds")
public Object[][] provideUserIds()
  └─ Parameterizes user IDs

@Test(dataProvider = "postIds")
public void testGetPostById(int postId)
  └─ Tests GET /posts/{id}
  └─ Executes multiple times with different post IDs

@Test(dataProvider = "userIds")
public void testGetPostsByUserId(int userId)
  └─ Tests GET /posts?userId={userId}
  └─ Retrieves posts filtered by user
```

---

#### DataProvidersIntegrationTest.java
**Purpose:** Integration tests demonstrating all 13+ data provider methods.

**Test Coverage:**
```java
@Test
public void testWithSimpleArrayDataProvider()
  └─ Basic 2D array parameterization

@Test
public void testWithCsvDataProvider()
  └─ CSV file-based parameterization

@Test
public void testWithJsonDataProvider()
  └─ JSON file-based parameterization

@Test
public void testWithPropertiesDataProvider()
  └─ Properties file-based parameterization

@Test
public void testWithMultipleParameters()
  └─ Multiple parameter combinations

@Test
public void testWithBuilderPattern()
  └─ TestIdBuilder usage

@Test
public void testWithScenarioEnum()
  └─ TestScenario enum usage

// ... 20+ more examples
```

---

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

---

## 🎓 Learning Path for Beginners

### Week 1: Basics
1. **Understand REST APIs**
   - Read about HTTP methods (GET, POST, PUT, DELETE)
   - Learn about status codes (200, 201, 404, 500)
   - Understand JSON format
   
2. **Run Existing Tests**
   - Clone this repository
   - Run `mvn test -Dtest=ObjectGetTests#testGetObjectById`
   - Study the test code and logs
   
3. **Modify a Test**
   - Open `ObjectGetTests.java`
   - Change the object ID from "6" to "7"
   - Run the test again

### Week 2: Write Your First Test
1. **Create a Simple GET Test**
   - Copy an existing test method
   - Change the endpoint or ID
   - Add your own assertions
   
2. **Create a POST Test**
   - Study `ObjectPostTests.java`
   - Create a new object with different data
   - Verify it was created successfully

### Week 3: Advanced Concepts
1. **Learn Soft Assertions**
   - Add multiple validations to one test
   - See how all assertions execute
   
2. **Work with POJOs**
   - Create a custom data object
   - Use Builder pattern
   - Deserialize response to POJO

### Week 4: Best Practices
1. **Add Logging**
   - Log important test steps
   - Log request/response data
   
2. **Organize Tests**
   - Group related tests
   - Use TestNG annotations
   - Create test suites

---

## 🔍 Common Scenarios & Solutions

### Scenario 1: How to verify response contains specific field?
```java
Response response = userService.getUserById(1);
Assert.assertNotNull(response.jsonPath().getString("name"));
```

### Scenario 2: How to verify status code?
```java
Response response = userService.getUserById(1);
Assert.assertEquals(response.getStatusCode(), 200);
```

### Scenario 3: How to extract value from response?
```java
Response response = userService.getUserById(1);
String userName = response.jsonPath().getString("name");
String userEmail = response.jsonPath().getString("email");
```

### Scenario 4: How to verify response time?
```java
Response response = userService.getUserById(1);
long responseTime = response.getTime();
Assert.assertTrue(responseTime < 3000, "Response took too long!");
```

### Scenario 5: How to verify multiple conditions?
```java
SoftAssert softAssert = new SoftAssert();
Response response = userService.getUserById(1);

softAssert.assertEquals(response.getStatusCode(), 200);
softAssert.assertNotNull(response.jsonPath().getString("name"));
softAssert.assertTrue(response.getTime() < 3000);

softAssert.assertAll(); // Execute all assertions
```

### Scenario 6: How to create object with nested data?
```java
Map<String, Object> data = new HashMap<>();
data.put("year", 2023);
data.put("price", 2499.99);
data.put("CPU", "M2 Pro");
data.put("RAM", "16 GB");

ApiObject newObject = ApiObject.builder()
    .name("Apple MacBook Pro 14")
    .data(data)
    .build();
```

### Scenario 7: How to handle authentication?
```java
// In BaseService.java, add to request specification:
.header("Authorization", "Bearer " + token)
```

### Scenario 8: How to test error responses?
```java
Response response = userService.getUserById(9999); // Non-existent ID
Assert.assertEquals(response.getStatusCode(), 404); // Not Found
```

---

## 🐛 Troubleshooting

### Issue: Tests fail with connection timeout
**Solution:**
- Check internet connection
- Verify API endpoints are accessible
- Increase timeout in `config.properties`

### Issue: Maven dependencies not downloading
**Solution:**
```bash
mvn clean install -U
```

### Issue: Java version error
**Solution:**
- Verify Java 21+ is installed: `java -version`
- Set JAVA_HOME environment variable
- Update pom.xml if using different Java version

### Issue: Allure report not generating
**Solution:**
```bash
# Install Allure
brew install allure  # macOS
# or download from https://github.com/allure-framework/allure2/releases

# Then run
mvn clean test
mvn allure:serve
```

### Issue: Lombok not working in IDE
**Solution:**
- Install Lombok plugin in your IDE
- Enable annotation processing in IDE settings
- Restart IDE

---

## 📚 Additional Resources

### Documentation
- [REST Assured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Documentation](https://docs.qameta.io/allure/)
- [Lombok Documentation](https://projectlombok.org/)

### Tutorials
- [REST API Tutorial](https://restfulapi.net/)
- [JSON Tutorial](https://www.json.org/)
- [Maven Tutorial](https://maven.apache.org/guides/getting-started/)

### Practice APIs
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) - Fake REST API
- [ReqRes](https://reqres.in/) - Practice REST API
- [Restful-API.dev](https://restful-api.dev/) - Test API for objects

---

## ❓ FAQ

**Q: Do I need API development experience to use this framework?**
A: No! This framework is designed for QA engineers and beginners. You only need to understand how to call APIs, not build them.

**Q: Can I use this for my company's APIs?**
A: Yes! Just update the base URLs in service classes and create POJOs for your API responses.

**Q: How do I add authentication?**
A: Add headers or tokens in the `BaseService` class request specification.

**Q: Can I run tests in parallel?**
A: Yes! Configure TestNG XML with `parallel="tests"` or `parallel="methods"`.

**Q: How do I integrate with CI/CD?**
A: Run `mvn test` in your CI pipeline (Jenkins, GitHub Actions, GitLab CI, etc.).

**Q: What if I don't know Java well?**
A: This framework uses simple Java concepts. Start with the examples and learn as you go!

---

## 🌟 Best Practices Implemented

✅ **Page Object Model** - Service layer separates test logic from API endpoints
✅ **POJO Pattern** - Type-safe request/response handling
✅ **Builder Pattern** - Clean object creation
✅ **Soft Assertions** - Comprehensive validation without stopping
✅ **Detailed Logging** - SLF4J for debugging
✅ **Proper Structure** - Organized packages and classes
✅ **Reusable Code** - Base classes and utility methods
✅ **Meaningful Names** - Clear method and variable names
✅ **Comments** - Explains what code does
✅ **Test Independence** - Each test can run standalone
✅ **Data Validation** - Status codes, response times, content types
✅ **Allure Reports** - Beautiful visual test results

---

## 🧩 Annotated Code Walkthroughs (Line-by-Line)

This section explains key parts of the framework line-by-line so you can connect tests → services → POJOs → utilities → CI.

### 1) Service Layer — `ObjectService`

```java
public class ObjectService {                                        // [1]
    private static final String BASE_URI = "https://api.restful-api.dev"; // [2]
    private static final String OBJECTS_PATH = "/objects";               // [3]

    public Response getObjectById(String objectId) {               // [4]
        return given()                                             // [5]
                .log().method().log().uri()                        // [6]
                .baseUri(BASE_URI)                                 // [7]
                .basePath(OBJECTS_PATH)                            // [8]
                .when()                                            // [9]
                .get("/" + objectId);                              // [10]
    }

    public Response updateObject(String objectId, ApiObject body) { // [11]
        return given()                                              // [12]
                .log().method().log().uri()                         // [13]
                .baseUri(BASE_URI)                                  // [14]
                .basePath(OBJECTS_PATH)                             // [15]
                .contentType("application/json")                    // [16]
                .body(body)                                         // [17]
                .when()                                             // [18]
                .put("/" + objectId);                               // [19]
    }
}
```

- [1]: A dedicated service class per API domain (POM pattern).
- [2]: Central base URL for the API under test.
- [3]: All object endpoints share the same path; keeps requests consistent.
- [4]: Method signature: accepts the target resource ID.
- [5]: Starts a REST Assured request (fluent builder API).
- [6]: Logs method and URI for clear request visibility.
- [7]-[8]: Sets base URI and resource path once, avoiding duplication.
- [9]: Switches from request building to execution stage.
- [10]: Executes GET on a specific resource using the provided ID.
- [11]-[19]: PUT request flow with JSON body (POJO is auto-serialized by Jackson).

### 2) Model Layer — `ApiObject` (POJO with Lombok)

```java
@Data               // [1]
@Builder            // [2]
@NoArgsConstructor  // [3]
@AllArgsConstructor // [4]
public class ApiObject {               // [5]
    private String id;                 // [6]
    private String name;               // [7]
    private Map<String, Object> data;  // [8]
}
```

- [1]: Generates getters, setters, equals, hashCode, toString.
- [2]: Adds a fluent Builder for readable object creation in tests.
- [3]-[4]: Adds both empty and full-args constructors for flexibility.
- [5]-[8]: Simple, type-safe structure mirroring API payloads.

### 3) Test Base — `BaseTest`

```java
@BeforeClass
public void setUp() {                  // [1]
    RestClient.getRequestSpec();       // [2]
    RestClient.getResponseSpec();      // [3]
}

@AfterClass
public void tearDown() {               // [4]
    RestClient.resetSpecs();           // [5]
}
```

- [1]: Runs once per class; prepares common REST Assured specs.
- [2]: Request spec sets base URI, content type, and logging filters.
- [3]: Response spec centralizes expectations like JSON content type.
- [4]-[5]: Cleanly resets global state after the class finishes.

### 4) REST Assured Utility — `RestClient`

```java
public static RequestSpecification getRequestSpec() {              // [1]
    if (requestSpec == null) {                                     // [2]
        requestSpec = new RequestSpecBuilder()                      // [3]
                .setBaseUri(ConfigManager.getInstance().getBaseUri()) // [4]
                .setContentType(ContentType.JSON)                   // [5]
                .addFilter(new RequestLoggingFilter())              // [6]
                .addFilter(new ResponseLoggingFilter())             // [7]
                .build();                                           // [8]
    }
    return requestSpec;                                            // [9]
}
```

- [1]: Provides a singleton-like request specification instance.
- [2]: Lazily initializes the spec once per JVM to avoid repetition.
- [3]-[5]: Establish consistent base URI and JSON content type.
- [6]-[7]: Enable full request/response logging for debugging.
- [8]-[9]: Build and reuse the configured specification.

### 5) A Real Test — `ObjectPutTests#testUpdateObject`

```java
@Test(priority = 1, description = "Verify updating an object with complete data replacement") // [1]
public void testUpdateObject() {
    String objectId = "7";                                  // [2]

    Map<String, Object> updatedData = new HashMap<>();      // [3]
    updatedData.put("year", 2024);                          // [4]
    updatedData.put("price", 2999.99);                      // [5]
    updatedData.put("CPU model", "M3 Max");                // [6]
    updatedData.put("Hard disk size", "4 TB");             // [7]

    ApiObject updatedObject = ApiObject.builder()           // [8]
            .name("Apple MacBook Pro 16 Updated")           // [9]
            .data(updatedData)                              // [10]
            .build();                                       // [11]

    Response response = objectService.updateObject(objectId, updatedObject); // [12]

    softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200"); // [13]
    softAssert.assertEquals(response.jsonPath().getString("name"),
            "Apple MacBook Pro 16 Updated", "Name should be updated"); // [14]
    softAssert.assertAll();                                    // [15]
}
```

- [1]: Prioritization helps compose BVT vs. regression suites.
- [2]: Target resource ID under test (stable example from API docs).
- [3]-[7]: Build a flexible JSON map for dynamic fields like specs/pricing.
- [8]-[11]: Use the Lombok Builder to create a clean request body.
- [12]: Call into the service layer (POM) to perform the PUT.
- [13]-[15]: Validate status + body using TestNG SoftAssert, then finalize.

### 6) CI Pipeline — Key Bits Explained

```yaml
stages: [build, test, deploy]                     # [1]

maven:build:                                      # [2]
  stage: build
  script:
    - mvn -B -Dmaven.test.failure.ignore=false \
      -Dmaven.repo.local=$CI_PROJECT_DIR/.m2 \
      clean install -DskipTests                  # [3]

maven:test:                                       # [4]
  stage: test
  needs: ["maven:build"]                          # [5]
  script:
    - mvn -B -Dmaven.test.failure.ignore=false \
      -Dmaven.repo.local=$CI_PROJECT_DIR/.m2 \
      clean test -DsuiteXmlFile=test-suites/testng-bvt.xml  # [6]
```

- [1]: Standard pipeline flow; fails fast if any stage fails.
- [2]: Uses official Maven+JDK image; consistent CI env.
- [3]: Build artifacts quickly by skipping tests.
- [4]: Isolated test stage runs after a successful build.
- [5]: `needs` ensures build success before tests start.
- [6]: Runs the BVT suite explicitly from `test-suites/`.

### 7) POST Test — `ObjectPostTests#testCreateObject` (with Builder Pattern)

```java
@Test(priority = 1, description = "Verify creating a new object with complete data")  // [1]
public void testCreateObject() {
    logger.info("Starting test: testCreateObject");                       // [2]
    logger.info("Preparing test data for new object creation");          // [3]
    
    Map<String, Object> data = new HashMap<>();                          // [4]
    data.put("year", 2023);                                              // [5]
    data.put("price", 2499.99);                                          // [6]
    data.put("CPU model", "M2 Max");                                     // [7]
    data.put("Hard disk size", "2 TB");                                  // [8]

    ApiObject newObject = ApiObject.builder()                            // [9]
            .name("Apple MacBook Pro 14")                                // [10]
            .data(data)                                                   // [11]
            .build();                                                     // [12]
    
    logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject)); // [13]
    logger.info("Creating new object: {}", newObject.getName());         // [14]
    Response response = objectService.createObject(newObject);           // [15]
    logger.info("Response received with status code: {}", response.getStatusCode()); // [16]
    
    logger.info("Validating created object data");                       // [17]
    softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200"); // [18]
    softAssert.assertNotNull(response, "Response should not be null");   // [19]
    softAssert.assertNotNull(response.jsonPath().getString("id"), 
                             "Response should contain ID");              // [20]
    softAssert.assertEquals(response.jsonPath().getString("name"), 
                           "Apple MacBook Pro 14", "Name should match"); // [21]
    
    response.then().log().status().log().body()                          // [22]
            .statusCode(200)                                              // [23]
            .body("name", equalTo(newObject.getName()))                  // [24]
            .body("data.year", equalTo(2023))                            // [25]
            .body("data.price", equalTo(2499.99f))                       // [26]
            .body("data.'CPU model'", equalTo("M2 Max"))                // [27]
            .body("data.'Hard disk size'", equalTo("2 TB"));            // [28]
    
    softAssert.assertAll();                                              // [29]
    logger.info("Test testCreateObject completed successfully - object created"); // [30]
}
```

- [1]: Test metadata (priority for suite ordering, description for reports).
- [2]-[3]: Structured logging for debugging and Allure report enrichment.
- [4]-[8]: Build a dynamic `Map<String, Object>` for nested JSON fields (flexible schema).
- [9]-[12]: Lombok Builder pattern in action; chainable, readable object creation.
- [13]: JSON serialization utility for request logging (pretty-print).
- [14]-[16]: Service call with pre/post logging; separates concerns (test logic vs. API call).
- [17]: Clear validation checkpoint for test step clarity.
- [18]-[21]: TestNG SoftAssert for status, non-null, ID presence, and name match.
- [22]-[28]: Fluent Hamcrest-style assertions via REST Assured DSL (nested path `data.year`).
- [29]: Finalize all soft assertions; fails test if any assertion failed.
- [30]: Test completion log with contextual detail.

### 8) DELETE Test with AssertionHelper — `ObjectDeleteTests#testDeleteObjectStatusCode`

```java
@Test(priority = 1, description = "Verify deleting an existing object returns 200 status") // [1]
public void testDeleteObjectStatusCode() {
    assertionHelper.logTestStart("testDeleteObjectStatusCode");          // [2]
    
    String objectId = "6";                                               // [3]
    assertionHelper.logRequest("DELETE", objectId);                      // [4]
    Response response = objectService.deleteObject(objectId);            // [5]
    assertionHelper.assertStatusCodeAndLogging(response, 200);           // [6]
    
    response.then().log().status().log().body()                          // [7]
            .statusCode(200);                                             // [8]
    
    assertionHelper.assertAll();                                         // [9]
    assertionHelper.logTestCompletion("testDeleteObjectStatusCode");     // [10]
}
```

- [1]: Standard TestNG annotation with priority (1 = runs first) and description.
- [2]: `AssertionHelper` centralizes repetitive log patterns; keeps test concise.
- [3]: Test data (immutable resource ID from API docs).
- [4]: Logs HTTP method + resource for request traceability.
- [5]: Service-layer call (POM); encapsulates DELETE endpoint logic.
- [6]: Combined assertion + logging via helper method; validates status and logs outcome.
- [7]-[8]: REST Assured's fluent validation DSL; can chain multiple body/header checks.
- [9]: Delegates to SoftAssert.assertAll() via helper; preserves all assertion results.
- [10]: Final log marker; test passed if we reach here.

### 9) AssertionHelper Utility — Centralized Assertion Logic

```java
public class AssertionHelper {                                           // [1]
    private final SoftAssert softAssert;                                 // [2]
    private final Logger logger;                                         // [3]

    public AssertionHelper(SoftAssert softAssert, Logger logger) {       // [4]
        this.softAssert = softAssert;                                    // [5]
        this.logger = logger;                                            // [6]
    }

    public void logTestStart(String testMethodName) {                    // [7]
        logger.info("Starting test: {}", testMethodName);                // [8]
    }

    public void assertStatusCode(Response response, int expectedStatusCode, String context) { // [9]
        logger.info("Response received with status code: {}", response.getStatusCode()); // [10]
        softAssert.assertEquals(response.getStatusCode(), expectedStatusCode, 
                               "Status code should be " + expectedStatusCode + " - " + context); // [11]
    }

    public void assertAll() {                                            // [12]
        softAssert.assertAll();                                          // [13]
    }
}
```

- [1]: Utility class following single-responsibility principle (assertions + logging).
- [2]-[3]: Immutable dependencies injected via constructor (thread-safe per test).
- [4]-[6]: Constructor injection; each test method gets a fresh instance (via `@BeforeMethod`).
- [7]-[8]: Reusable log method; standardizes test start messages across all tests.
- [9]-[11]: Combines assertion + logging; reduces 3 lines to 1 in every test.
- [12]-[13]: Delegates to SoftAssert; preserves test failure if any assertion failed.

**Why AssertionHelper?**
- **DRY Principle**: Eliminates duplicated logger/assert patterns across 50+ test methods.
- **Maintainability**: Change log format once; all tests inherit the update.
- **Readability**: Tests focus on "what" (business logic) vs. "how" (logging syntax).

---

## 🎯 Data Providers & Parameterized Testing Guide

### What Are Data Providers?

**Data Providers** are a TestNG feature that allows you to run the same test method multiple times with different data sets. Instead of writing the same test logic repeatedly for different inputs, you define the data once and the framework iterates through it automatically.

#### Key Benefits:
✅ **Test Reusability** - Write test logic once, run with multiple data sets  
✅ **Code Reduction** - Eliminate duplicate test methods  
✅ **Maintainability** - Update test logic in one place  
✅ **Coverage** - Easily test with various scenarios and edge cases  
✅ **Reporting** - Each iteration appears as a separate test in reports  

### Data Provider Types Implemented

This framework now supports **13 different data passing methods**:

| # | Method | Location | Example |
|---|--------|----------|---------|
| 1 | **Object[][] Arrays** | In-memory inline data | `@DataProvider public Object[][] data()` |
| 2 | **CSV Files** | testdata.csv | `TestDataProvider.getTestIdsFromCsv()` |
| 3 | **JSON Files** | testdata.json | `TestDataProvider.getTestConfigFromJson()` |
| 4 | **Properties Files** | testdata.properties | `TestDataProvider.getProperty(key, default)` |
| 5 | **Builder Pattern** | Utility class | `TestIdBuilder.builder().id(1).name("Test")` |
| 6 | **Custom Annotations** | @TestData decorator | `@TestData on test methods` |
| 7 | **Enum Constants** | TestScenario enum | `TestScenario.HAPPY_PATH` |
| 8 | **Static Constants** | TestConstants class | `TestConstants.StatusCodes.OK` |
| 9 | **Database Queries** | SQL queries | Parameterized result sets |
| 10 | **Excel Files** | Apache POI integration | Excel workbook data |
| 11 | **XML Configuration** | XML-based configs | TestNG XML files |
| 12 | **Stream API** | Java 8+ Streams | `stream().filter().map()` |
| 13 | **Custom Iterables** | Custom implementation | Lazy loading patterns |

### TestDataProvider Utility Class

This is the **centralized hub** for all test data. It provides reusable data providers and constants.

#### Location:
```
src/test/java/com/api/automation/tests/utils/TestDataProvider.java
```

#### Key Methods:

**1. User ID Data Provider**
```java
@DataProvider(name = "userIds")
public Object[][] provideUserIds() {
    return TestDataProvider.getUserIds();  // Returns: {1, 2, 3, 5, 10}
}
```
**Usage**: Tests that need user IDs (get user, update user, delete user)

**2. Post ID Data Provider**
```java
@DataProvider(name = "postIds")
public Object[][] providePostIds() {
    return TestDataProvider.getPostIds();  // Returns: {1, 2, 5, 10, 50}
}
```
**Usage**: Tests that need post IDs (get post, update post, delete post)

**3. Pagination Parameters Provider**
```java
public Object[][] getPaginationParams() {
    // Returns combinations of: {page:0, pageSize:10}, {page:1, pageSize:20}, etc.
}
```
**Usage**: Tests that need pagination data

**4. User Count Provider**
```java
public Object[][] getUserCounts() {
    // Returns different count values for validation
}
```
**Usage**: Tests that validate user counts

**5. CSV File Loader**
```java
public Object[][] getTestIdsFromCsv() {
    // Loads data from testdata.csv
    // Returns user records with email, password, role, enabled, firstName, lastName
}
```
**CSV Format** (testdata.csv):
```csv
email,password,role,enabled,firstName,lastName
admin@example.com,securepass123,ADMIN,true,Admin,User
user@example.com,userpass456,USER,true,John,Doe
editor@example.com,editpass789,EDITOR,true,Jane,Smith
readonly@example.com,readonly111,READONLY,true,Bob,Johnson
guest@example.com,guestpass222,GUEST,false,Alice,Brown
moderator@example.com,modpass333,MODERATOR,true,Charlie,Davis
superuser@example.com,superpass444,SUPERUSER,true,David,Wilson
```

**6. JSON File Loader**
```java
public Object[][] getTestConfigFromJson() {
    // Extracts testConfig from testdata.json
    // Returns admin credentials as test data
}

public Object[][] getUsersFromJson() {
    // Loads user array from testdata.json
    // Returns user objects for parameterized tests
}

public Object[][] getApiEndpointFromJson() {
    // Gets API endpoints from testdata.json
    // Returns endpoint URLs
}
```
**JSON Format** (testdata.json):
```json
{
  "users": [
    {"id": 1, "name": "Test User 1", "email": "user1@example.com"},
    {"id": 2, "name": "Test User 2", "email": "user2@example.com"},
    {"id": 3, "name": "Test User 3", "email": "user3@example.com"},
    {"id": 4, "name": "Test User 4", "email": "user4@example.com"}
  ],
  "testConfig": {
    "admin.username": "testadmin",
    "admin.password": "testpass123"
  },
  "endpoints": {
    "users": "/api/users",
    "posts": "/api/posts",
    "comments": "/api/comments"
  }
}
```

**7. Properties File Loader**
```java
public String getProperty(String key, String defaultValue) {
    // Loads configuration from testdata.properties
}
```
**Properties Format** (testdata.properties):
```properties
admin.username=testadmin
admin.password=testpass123
api.baseurl=https://jsonplaceholder.typicode.com
api.timeout=5000
retry.count=3
```

### TestDataProvider Helper Classes

#### TestConstants (Nested Class)
Organized constants for your tests:

```java
// API Configuration
TestConstants.API.BASE_URL              // Base URL for all tests
TestConstants.API.USERS_ENDPOINT        // "/users"
TestConstants.API.POSTS_ENDPOINT        // "/posts"
TestConstants.API.TIMEOUT_MS            // 5000
TestConstants.API.RETRY_COUNT           // 3

// HTTP Status Codes
TestConstants.StatusCodes.OK             // 200
TestConstants.StatusCodes.CREATED        // 201
TestConstants.StatusCodes.BAD_REQUEST    // 400
TestConstants.StatusCodes.NOT_FOUND      // 404
TestConstants.StatusCodes.SERVER_ERROR   // 500

// Expected Values for Validation
TestConstants.Validation.USER_IDS        // {1, 2, 3, 5, 10}
TestConstants.Validation.POST_IDS        // {1, 2, 5, 10, 50}
TestConstants.Validation.EXPECTED_USERS  // 10 (count)

// Timeout Values
TestConstants.Timeouts.SHORT             // 2000 ms
TestConstants.Timeouts.MEDIUM            // 5000 ms
TestConstants.Timeouts.LONG              // 10000 ms
```

#### TestIdBuilder (Builder Pattern)
Create flexible test data with builder pattern:

```java
// Example 1: Create a test ID object
TestId testId = TestIdBuilder.builder()
    .id(1)
    .name("Test User")
    .email("test@example.com")
    .role("ADMIN")
    .build();

// Example 2: Chain multiple attributes
TestId testId = TestIdBuilder.builder()
    .id(5)
    .name("Advanced User")
    .email("advanced@example.com")
    .role("EDITOR")
    .active(true)
    .metadata("key", "value")
    .build();
```

#### TestScenario (Enum)
Classify test scenarios:

```java
enum TestScenario {
    HAPPY_PATH,      // Valid data, expected success
    EDGE_CASE,       // Boundary conditions
    INVALID_DATA,    // Invalid inputs
    BOUNDARY,        // Min/max values
    PERFORMANCE      // Performance test scenarios
}
```

### Real-World Examples

#### Example 1: UserTests with Parameterized Testing

**Before Data Providers** (7 test methods):
```java
@Test
public void testGetUserById1() { ... }

@Test
public void testGetUserById2() { ... }

@Test
public void testGetUserById3() { ... }
// And so on... duplicated logic 5 times
```

**After Data Providers** (1 parameterized method = 5 test executions):
```java
@DataProvider(name = "userIds")
public Object[][] provideUserIds() {
    return TestDataProvider.getUserIds();  // [1, 2, 3, 5, 10]
}

@Test(dataProvider = "userIds")
public void testGetUserById(int userId) {                              // [Line 1]
    logger.info("Loading userIds data provider");                      // [Line 2]
                                                                        // [Line 3]
    // Test Code (single implementation)                               // [Line 4]
    Response response = userService.getUser(userId);                  // [Line 5]
                                                                        // [Line 6]
    softAssert.assertEquals(response.getStatusCode(), 200,            // [Line 7]
        "User " + userId + " should return 200");                     // [Line 8]
    softAssert.assertNotNull(response.jsonPath().get("id"),           // [Line 9]
        "Response should contain user ID");                            // [Line 10]
                                                                        // [Line 11]
    softAssert.assertAll();                                            // [Line 12]
}
```

**Line-by-Line Explanation:**
- **[Line 1]**: Test method declares it uses "userIds" data provider
- **[Line 2]**: Logging message for traceability
- **[Line 3]**: Blank line for readability
- **[Line 4-5]**: Call service method with parameterized userId
- **[Line 6]**: Blank line
- **[Line 7-8]**: Assert HTTP status code is 200
- **[Line 9-10]**: Assert response contains user ID
- **[Line 11]**: Blank line
- **[Line 12]**: Execute all accumulated assertions

**Test Execution:**
```
Test Run 1: testGetUserById(1)   ✅ PASS
Test Run 2: testGetUserById(2)   ✅ PASS
Test Run 3: testGetUserById(3)   ✅ PASS
Test Run 4: testGetUserById(5)   ✅ PASS
Test Run 5: testGetUserById(10)  ✅ PASS

Total: 5 executions from 1 test method
```

#### Example 2: PostTests with Multiple Data Providers

```java
@DataProvider(name = "postIds")
public Object[][] providePostIds() {
    return TestDataProvider.getPostIds();  // [1, 2, 5, 10, 50]
}

@DataProvider(name = "userIds")
public Object[][] provideUserIds() {
    return TestDataProvider.getUserIds();  // [1, 2, 3, 5, 10]
}

@Test(dataProvider = "postIds")
public void testGetPostById(int postId) {                              // [Line 1]
    logger.info("Loading postIds data provider");                      // [Line 2]
                                                                        // [Line 3]
    Response response = postService.getPost(postId);                  // [Line 4]
                                                                        // [Line 5]
    softAssert.assertEquals(response.getStatusCode(),                 // [Line 6]
        TestDataProvider.TestConstants.StatusCodes.OK,                // [Line 7]
        "Post should be retrievable");                                // [Line 8]
                                                                        // [Line 9]
    softAssert.assertAll();                                            // [Line 10]
}

@Test(dataProvider = "userIds")
public void testGetPostsByUserId(int userId) {                         // [Line 1]
    logger.info("Loading userIds data provider");                      // [Line 2]
                                                                        // [Line 3]
    Response response = postService.getPostsByUser(userId);           // [Line 4]
                                                                        // [Line 5]
    softAssert.assertEquals(response.getStatusCode(),                 // [Line 6]
        TestDataProvider.TestConstants.StatusCodes.OK);               // [Line 7]
                                                                        // [Line 8]
    softAssert.assertAll();                                            // [Line 9]
}
```

**Test Execution:**
```
testGetPostById(1)        ✅ PASS
testGetPostById(2)        ✅ PASS
testGetPostById(5)        ✅ PASS
testGetPostById(10)       ✅ PASS
testGetPostById(50)       ✅ PASS

testGetPostsByUserId(1)   ✅ PASS
testGetPostsByUserId(2)   ✅ PASS
testGetPostsByUserId(3)   ✅ PASS
testGetPostsByUserId(5)   ✅ PASS
testGetPostsByUserId(10)  ✅ PASS

Total: 10 test executions from 2 parameterized methods
```

### Integration Summary

#### Files Changed:

**1. New Files Created:**
- `src/test/java/com/api/automation/tests/utils/TestDataProvider.java` (300+ lines)
  - Contains all data providers and constants
  - Nested classes: TestConstants, TestIdBuilder, TestScenario

- `src/test/java/com/api/automation/tests/utils/DataProvidersIntegrationTest.java` (400+ lines)
  - 27 test cases demonstrating all 13 data passing methods
  - Reference implementation for learning

- `src/test/resources/testdata.csv` (7 records)
  - Sample user data in CSV format
  - Used by CSV data provider

- `src/test/resources/testdata.json` (users, config, endpoints)
  - Structured test data in JSON format
  - Used by JSON data providers

- `src/test/resources/testdata.properties`
  - Configuration values (credentials, URLs, timeouts)
  - Used by Properties file loader

**2. Updated Files:**
- `src/test/java/com/api/automation/tests/jsonplaceholder/UserTests.java`
  - Added imports: TestDataProvider, DataProvider
  - Added data provider methods: provideUserIds(), provideUserCounts()
  - Updated test methods: testGetUserById, testGetUserAsObject to use data providers
  - Test executions increased from 7 to 16

- `src/test/java/com/api/automation/tests/jsonplaceholder/PostTests.java`
  - Added imports: TestDataProvider, DataProvider
  - Added data provider methods: providePostIds(), provideUserIds()
  - Updated test methods: testGetPostById, testGetPostAsObject, testGetPostsByUserId
  - Test executions increased from 8 to 21

**3. Dependencies Added to pom.xml:**
```xml
<!-- For JSON Processing -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
    <scope>test</scope>
</dependency>

<!-- For Excel File Processing -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.0.0</version>
    <scope>test</scope>
</dependency>
```

### How to Use Data Providers in Your Tests

#### Step 1: Import Required Classes
```java
import com.api.automation.tests.utils.TestDataProvider;
import org.testng.annotations.DataProvider;
```

#### Step 2: Create Data Provider Method
```java
@DataProvider(name = "myTestData")
public Object[][] provideData() {
    return TestDataProvider.getUserIds();  // Or any other provider
}
```

#### Step 3: Use in Test Method
```java
@Test(dataProvider = "myTestData")
public void myTestMethod(int dataValue) {
    // Test implementation using dataValue
}
```

#### Step 4: Access Constants
```java
response.then()
    .statusCode(TestDataProvider.TestConstants.StatusCodes.OK)
    .timeout(TestDataProvider.TestConstants.Timeouts.MEDIUM);
```

### Test Results & Metrics

#### Before Data Providers Integration:
- Total Test Methods: 15
- Total Test Executions: 15
- Test Classes Updated: 0
- Data Providers Used: 0

#### After Data Providers Integration:
- Total Test Methods: 15 (same)
- Total Test Executions: 37 (from parameterization)
- Test Classes Updated: 2 (UserTests, PostTests)
- Data Providers Used: 6 built-in providers
- Data Passing Methods Demonstrated: 13
- Pass Rate: 35/37 (94.6%)

#### Test Execution Breakdown:
```
UserTests:
  - testGetUserById(userId):        5 executions (IDs: 1, 2, 3, 5, 10)
  - testGetUserAsObject(userId):    5 executions (IDs: 1, 2, 3, 5, 10)
  - Other tests:                    6 executions
  - Total:                          16 test runs ✅

PostTests:
  - testGetPostById(postId):        5 executions (IDs: 1, 2, 5, 10, 50)
  - testGetPostAsObject(postId):    5 executions (IDs: 1, 2, 5, 10, 50)
  - testGetPostsByUserId(userId):   5 executions (IDs: 1, 2, 3, 5, 10)
  - Other tests:                    6 executions
  - Total:                          21 test runs ✅

DataProvidersIntegrationTest:
  - All 13 methods:                 27 executions
  - Total:                          27 test runs ✅

Grand Total: 64 test executions
```

### Best Practices for Data Providers

✅ **DO:**
- Centralize data providers in utility classes
- Use meaningful provider names
- Document what each provider returns
- Keep data separate from test logic
- Load data from external files for large datasets
- Use builders for complex objects
- Cache expensive data loads

❌ **DON'T:**
- Hardcode test data in test methods
- Create separate test methods for each data point
- Mix data loading with business logic
- Use providers for single data value
- Load data from test methods (do it in providers)
- Share mutable state between parameterized test runs

### Running Parameterized Tests

**Run all tests:**
```bash
mvn clean test
```

**Run specific test class with data providers:**
```bash
mvn test -Dtest=UserTests
mvn test -Dtest=PostTests
```

**Run specific test method:**
```bash
mvn test -Dtest=UserTests#testGetUserById
```

**View results:**
- Console output shows each parameterized iteration
- Allure reports show separate results per iteration
- TestNG XML reports include all iterations

### Advanced Parameterization Patterns

#### Pattern 1: Multiple Parameters per Test

```java
@DataProvider(name = "userAndPostIds")
public Object[][] provideUserAndPostIds() {
    return new Object[][] {
        {1, 1},   // userId, postId
        {1, 2},
        {2, 3},
        {5, 10},
        {10, 50}
    };
}

@Test(dataProvider = "userAndPostIds")
public void testGetUserPost(int userId, int postId) {
    logger.info("Fetching post {} from user {}", postId, userId);
    
    Response userResponse = userService.getUserById(userId);
    Response postResponse = postService.getPostById(postId);
    
    softAssert.assertEquals(userResponse.getStatusCode(), 200);
    softAssert.assertEquals(postResponse.getStatusCode(), 200);
    softAssert.assertAll();
}
```

#### Pattern 2: Data Provider with Complex Objects

```java
@DataProvider(name = "objectsWithMetadata")
public Object[][] provideObjectsWithMetadata() {
    Map<String, Object> data1 = new HashMap<>();
    data1.put("year", 2023);
    data1.put("price", 2499.99);
    
    Map<String, Object> data2 = new HashMap<>();
    data2.put("year", 2024);
    data2.put("price", 2999.99);
    
    return new Object[][] {
        {
            ApiObject.builder().name("MacBook Pro").data(data1).build(),
            "MacBook Pro"
        },
        {
            ApiObject.builder().name("MacBook Air").data(data2).build(),
            "MacBook Air"
        }
    };
}

@Test(dataProvider = "objectsWithMetadata")
public void testCreateObjectWithMetadata(ApiObject object, String expectedName) {
    logger.info("Creating object: {}", expectedName);
    Response response = objectService.createObject(object);
    
    softAssert.assertEquals(response.getStatusCode(), 200);
    softAssert.assertEquals(response.jsonPath().getString("name"), expectedName);
    softAssert.assertAll();
}
```

#### Pattern 3: Combining CSV with Custom Logic

```java
@DataProvider(name = "csvDataWithValidation")
public Object[][] provideCsvDataWithValidation() throws Exception {
    Object[][] csvData = TestDataProvider.getTestIdsFromCsv();
    
    // Enhance CSV data with computed values
    Object[][] enhancedData = new Object[csvData.length][3];
    for (int i = 0; i < csvData.length; i++) {
        String[] row = (String[]) csvData[i];
        enhancedData[i][0] = row[0]; // email
        enhancedData[i][1] = row[1]; // password
        enhancedData[i][2] = row[0].split("@")[0]; // username extracted from email
    }
    return enhancedData;
}

@Test(dataProvider = "csvDataWithValidation")
public void testUserWithEmailDerivedUsername(String email, String password, String username) {
    logger.info("Testing user: {} with derived username: {}", email, username);
    
    User user = User.builder()
        .email(email)
        .username(username)
        .password(password)
        .build();
    
    Response response = userService.createUser(user);
    softAssert.assertEquals(response.getStatusCode(), 201);
    softAssert.assertEquals(response.jsonPath().getString("username"), username);
    softAssert.assertAll();
}
```

#### Pattern 4: JSON File with Filtering

```java
@DataProvider(name = "activeUsersFromJson")
public Object[][] provideActiveUsersFromJson() throws Exception {
    Object[][] allUsers = TestDataProvider.getUsersFromJson();
    
    // Filter only active users
    List<Object[]> activeUsers = new ArrayList<>();
    for (Object[] user : allUsers) {
        // Custom logic to check if user is active
        // This example assumes additional data in TestDataProvider
        activeUsers.add(user);
    }
    
    return activeUsers.toArray(new Object[0][0]);
}

@Test(dataProvider = "activeUsersFromJson")
public void testActiveUserOperations(Object userData) {
    logger.info("Testing active user: {}", userData);
    
    // Test implementation
    Response response = userService.performOperation(userData);
    softAssert.assertEquals(response.getStatusCode(), 200);
    softAssert.assertAll();
}
```

#### Pattern 5: Dynamically Generated Data

```java
@DataProvider(name = "dynamicUserIds")
public Object[][] provideDynamicUserIds() {
    // Generate data dynamically based on test environment
    int maxUserId = Integer.parseInt(TestDataProvider.getProperty("max.user.id", "10"));
    
    List<Object[]> data = new ArrayList<>();
    for (int i = 1; i <= maxUserId; i++) {
        if (i % 2 == 0) { // Only even IDs for this example
            data.add(new Object[]{i});
        }
    }
    
    return data.toArray(new Object[0][0]);
}

@Test(dataProvider = "dynamicUserIds")
public void testDynamicallyGeneratedUserIds(int userId) {
    logger.info("Testing dynamically generated user ID: {}", userId);
    Response response = userService.getUserById(userId);
    softAssert.assertEquals(response.getStatusCode(), 200);
    softAssert.assertAll();
}
```

### Performance Optimization for Data Providers

#### 1. Lazy Loading Data

```java
@DataProvider(name = "lazyLoadedData")
public Iterator<Object[]> provideLazyLoadedData() {
    return new Iterator<Object[]>() {
        private int current = 0;
        private final int max = 100; // Large dataset
        
        @Override
        public boolean hasNext() {
            return current < max;
        }
        
        @Override
        public Object[] next() {
            // Load data on-demand, not all at once
            return new Object[]{++current};
        }
    };
}

@Test(dataProvider = "lazyLoadedData")
public void testWithLazyLoadedData(int value) {
    logger.info("Testing with lazy-loaded value: {}", value);
    // Test implementation
}
```

#### 2. Caching Data Provider Results

```java
public class CachedDataProvider {
    private static final Map<String, Object[][]> cache = new HashMap<>();
    
    public static Object[][] getCachedData(String key) {
        if (!cache.containsKey(key)) {
            // Load data only once
            cache.put(key, loadDataFromSource(key));
        }
        return cache.get(key);
    }
    
    private static Object[][] loadDataFromSource(String key) {
        // Expensive operation (file I/O, API call, etc.)
        logger.info("Loading data for key: {} (first time)", key);
        return TestDataProvider.getUserIds();
    }
}

@DataProvider(name = "cachedUserIds")
public Object[][] provideCachedUserIds() {
    return CachedDataProvider.getCachedData("userIds");
}

@Test(dataProvider = "cachedUserIds")
public void testWithCachedData(int userId) {
    logger.info("Testing with cached user ID: {}", userId);
    Response response = userService.getUserById(userId);
    softAssert.assertEquals(response.getStatusCode(), 200);
    softAssert.assertAll();
}
```

### Handling Data Provider Failures

#### Pattern: Data Provider Error Handling

```java
@DataProvider(name = "robustDataProvider")
public Object[][] provideRobustData() {
    try {
        return TestDataProvider.getTestIdsFromCsv();
    } catch (Exception e) {
        logger.error("Failed to load CSV data, using fallback", e);
        // Return fallback data if primary source fails
        return new Object[][] {
            {1}, {2}, {3}, {5}, {10}
        };
    }
}

@Test(dataProvider = "robustDataProvider")
public void testWithFallbackData(int value) {
    logger.info("Testing with data (CSV or fallback): {}", value);
    Response response = userService.getUserById(value);
    softAssert.assertEquals(response.getStatusCode(), 200);
    softAssert.assertAll();
}
```

### Data Provider Best Practices Checklist

✅ **Data Organization**
- Keep data files in `src/test/resources/`
- Use meaningful file names (testdata.csv, testdata.json, etc.)
- Document data format and values

✅ **Performance**
- Cache data that's expensive to load
- Use lazy loading for large datasets
- Consider Iterator pattern for streaming data

✅ **Maintainability**
- Centralize all providers in TestDataProvider utility
- Document what each provider returns
- Use meaningful provider names

✅ **Error Handling**
- Provide sensible fallback data
- Log errors clearly for debugging
- Validate data integrity before use

✅ **Testing Strategy**
- Use different data sets for different test types
- Include edge cases in data
- Separate happy path from error scenarios

### Troubleshooting Data Providers

**Issue: Data Provider returning null**
```java
// ❌ Wrong
@DataProvider(name = "myData")
public Object[][] provideData() {
    return null; // This will cause issues
}

// ✅ Correct
@DataProvider(name = "myData")
public Object[][] provideData() {
    return new Object[][] {
        {1}, {2}, {3}
    };
}
```

**Issue: Data Provider data not found**
```java
// ❌ Wrong
public Object[][] provideData() {
    // File not in classpath
    FileReader fr = new FileReader("testdata.csv");
}

// ✅ Correct
public Object[][] provideData() {
    // Use ClassLoader to access resources
    InputStream is = this.getClass()
        .getClassLoader()
        .getResourceAsStream("testdata.csv");
}
```

**Issue: Data type mismatch**
```java
// ❌ Wrong
@DataProvider(name = "ids")
public Object[][] provideIds() {
    return new Object[][] {
        {"1"}, {"2"}, {"3"} // Strings instead of Integers
    };
}

@Test(dataProvider = "ids")
public void testWithIds(int id) { // Expects int
    // Type mismatch error
}

// ✅ Correct
@DataProvider(name = "ids")
public Object[][] provideIds() {
    return new Object[][] {
        {1}, {2}, {3} // Integers
    };
}

@Test(dataProvider = "ids")
public void testWithIds(int id) {
    // Works correctly
}
```

### Additional Documentation

For comprehensive examples of all 13 data passing methods:
- **DATA_PASSING_GUIDE.md** - Complete guide with all 13 methods explained
- **INTEGRATION_SUMMARY.md** - Summary of integration work completed
- **DataProvidersIntegrationTest.java** - Working examples of all 13 methods
- **README_UPDATE_SUMMARY.md** - Detailed breakdown of README changes

---

## 📄 License

This project is licensed under the MIT License - feel free to use it for learning and commercial projects!

## 🤝 Contributing

Contributions are welcome! Whether you're fixing bugs, improving documentation, or adding new features:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 💬 Questions or Issues?

- 📧 Open an issue in this repository
- 💡 Check existing issues for solutions
- 🌟 Star this repo if it helped you learn!

---

## 🎉 Conclusion

You now have a **professional-grade API automation framework** that you can:
- Learn from and practice with
- Customize for your own projects
- Use as a template for work projects
- Showcase in your portfolio

**Happy Testing! 🚀**

# Test commit for auto-PR