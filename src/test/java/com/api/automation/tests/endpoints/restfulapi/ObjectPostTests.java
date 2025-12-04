package com.api.automation.tests.endpoints.restfulapi;

import com.api.automation.models.ApiObject;
import com.api.automation.services.ObjectService;
import com.api.automation.tests.utils.BaseTest;
import com.api.automation.utils.JsonUtils;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

/**
 * Test class for Object API POST endpoints from restful-api.dev
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class ObjectPostTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectPostTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectPostTests() {
        logger.info("Setting up ObjectPostTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify creating a new object with complete data")
    public void testCreateObject() {
        logger.info("Starting test: testCreateObject");
        logger.info("Preparing test data for new object creation");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2023);
        data.put("price", 2499.99);
        data.put("CPU model", "M2 Max");
        data.put("Hard disk size", "2 TB");

        ApiObject newObject = ApiObject.builder()
                .name("Apple MacBook Pro 14")
                .data(data)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
        logger.info("Creating new object: {}", newObject.getName());
        Response response = objectService.createObject(newObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating created object data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("name", equalTo(newObject.getName()))
                .body("data.year", equalTo(2023))
                .body("data.price", equalTo(2499.99f))
                .body("data.'CPU model'", equalTo("M2 Max"))
                .body("data.'Hard disk size'", equalTo("2 TB"));
        
        logger.info("Test testCreateObject completed successfully - object created");
    }

    @Test(priority = 2, description = "Verify creating Apple MacBook Pro 16 as specified in API documentation")
    public void testCreateAppleMacBookPro16() {
        logger.info("Starting test: testCreateAppleMacBookPro16");
        logger.info("Preparing test data matching API documentation example");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");

        ApiObject newObject = ApiObject.builder()
                .name("Apple MacBook Pro 16")
                .data(data)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
        logger.info("Creating new object: {}", newObject.getName());
        Response response = objectService.createObject(newObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating created object matches request data");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16", "Name should match");
        softAssert.assertEquals(response.jsonPath().getInt("data.year"), 2019, "Year should be 2019");
        softAssert.assertEquals(response.jsonPath().getFloat("data.price"), 1849.99f, "Price should be 1849.99");
        softAssert.assertEquals(response.jsonPath().getString("data.'CPU model'"), "Intel Core i9", "CPU model should match");
        softAssert.assertEquals(response.jsonPath().getString("data.'Hard disk size'"), "1 TB", "Hard disk size should match");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testCreateAppleMacBookPro16 completed successfully");
    }

    @Test(priority = 3, description = "Verify created object contains ID and createdAt fields")
    public void testCreateObjectResponseStructure() {
        logger.info("Starting test: testCreateObjectResponseStructure");
        logger.info("Preparing test data for object creation");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2023);
        data.put("price", 1999.99);
        data.put("CPU model", "M3 Pro");
        data.put("Hard disk size", "1 TB");

        ApiObject newObject = ApiObject.builder()
                .name("Apple MacBook Pro 13")
                .data(data)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
        logger.info("Creating new object and validating response structure");
        Response response = objectService.createObject(newObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains all required fields including ID and createdAt");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("data"))
                .body("$", hasKey("createdAt"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue())
                .body("createdAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
        
        logger.info("Test testCreateObjectResponseStructure completed successfully");
    }

    @Test(priority = 4, description = "Verify creating object with minimum required fields")
    public void testCreateObjectWithMinimalData() {
        logger.info("Starting test: testCreateObjectWithMinimalData");
        logger.info("Creating object with only name field");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2024);

        ApiObject newObject = ApiObject.builder()
                .name("Test Device")
                .data(data)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
        logger.info("Creating new object with minimal data");
        Response response = objectService.createObject(newObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating object was created successfully");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Test Device", "Name should match");
        softAssert.assertNotNull(response.jsonPath().getString("id"), "ID should be generated");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testCreateObjectWithMinimalData completed successfully");
    }

    @Test(priority = 5, description = "Verify creating multiple objects with different data")
    public void testCreateMultipleObjects() {
        logger.info("Starting test: testCreateMultipleObjects");
        logger.info("Creating multiple objects to verify API consistency");
        
        // First object
        logger.info("Creating first object: iPhone 14 Pro");
        Map<String, Object> data1 = new HashMap<>();
        data1.put("year", 2023);
        data1.put("price", 999.99);
        data1.put("Storage", "256 GB");
        
        ApiObject object1 = ApiObject.builder()
                .name("Apple iPhone 14 Pro")
                .data(data1)
                .build();
        
        logger.info("Request JSON body for first object: \n{}", JsonUtils.serialize(object1));
        Response response1 = objectService.createObject(object1);
        logger.info("First object created with status: {}", response1.getStatusCode());
        
        // Second object
        logger.info("Creating second object: iPad Air");
        Map<String, Object> data2 = new HashMap<>();
        data2.put("year", 2023);
        data2.put("price", 599.99);
        data2.put("Storage", "128 GB");
        
        ApiObject object2 = ApiObject.builder()
                .name("Apple iPad Air")
                .data(data2)
                .build();
        
        logger.info("Request JSON body for second object: \n{}", JsonUtils.serialize(object2));
        Response response2 = objectService.createObject(object2);
        logger.info("Second object created with status: {}", response2.getStatusCode());
        
        logger.info("Validating both objects were created successfully");
        softAssert.assertEquals(response1.getStatusCode(), 200, "First object status should be 200");
        softAssert.assertEquals(response2.getStatusCode(), 200, "Second object status should be 200");
        softAssert.assertNotEquals(response1.jsonPath().getString("id"), 
                                    response2.jsonPath().getString("id"), 
                                    "Each object should have unique ID");
        
        response1.then().log().status().log().body();
        response2.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testCreateMultipleObjects completed successfully");
    }

    @Test(priority = 6, description = "Verify POST request content type and response time")
    public void testCreateObjectPerformance() {
        logger.info("Starting test: testCreateObjectPerformance");
        logger.info("Testing POST request performance and content type");
        
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2023);
        data.put("price", 1299.99);

        ApiObject newObject = ApiObject.builder()
                .name("Performance Test Device")
                .data(data)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(newObject));
        logger.info("Creating object and measuring response time");
        Response response = objectService.createObject(newObject);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time and content type");
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json")
                .time(lessThan(3000L));
        
        logger.info("Test testCreateObjectPerformance completed - response time: {} ms", responseTime);
    }
}
