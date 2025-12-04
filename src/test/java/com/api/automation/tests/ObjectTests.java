package com.api.automation.tests;

import com.api.automation.models.ApiObject;
import com.api.automation.services.ObjectService;
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
 * Test class for Object API endpoints from restful-api.dev
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class ObjectTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectTests() {
        logger.info("Setting up ObjectTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify getting a single object by ID - Apple MacBook Pro 16")
    public void testGetObjectById() {
        logger.info("Starting test: testGetObjectById");
        String objectId = "7";
        logger.info("Fetching object with ID: {}", objectId);
        
        Response response = objectService.getObjectById(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response body fields");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(objectId))
                .body("name", equalTo("Apple MacBook Pro 16"))
                .body("data.year", equalTo(2019))
                .body("data.price", equalTo(1849.99f))
                .body("data.'CPU model'", equalTo("Intel Core i9"))
                .body("data.'Hard disk size'", equalTo("1 TB"));
        
        logger.info("Test testGetObjectById completed successfully");
    }

    @Test(priority = 2, description = "Verify getting object as ApiObject POJO")
    public void testGetObjectAsObject() {
        logger.info("Starting test: testGetObjectAsObject");
        String objectId = "7";
        logger.info("Fetching object with ID: {} and deserializing to ApiObject", objectId);
        
        ApiObject apiObject = objectService.getObjectByIdAsObject(objectId);
        logger.info("ApiObject received: {}", apiObject);
        
        logger.info("Validating ApiObject fields using SoftAssert");
        softAssert.assertNotNull(apiObject, "ApiObject should not be null");
        softAssert.assertEquals(apiObject.getId(), objectId, "Object ID should match");
        softAssert.assertEquals(apiObject.getName(), "Apple MacBook Pro 16", "Object name should match");
        softAssert.assertNotNull(apiObject.getData(), "Data should not be null");
        softAssert.assertEquals(apiObject.getData().get("year"), 2019, "Year should be 2019");
        softAssert.assertEquals(apiObject.getData().get("price"), 1849.99, "Price should match");
        softAssert.assertEquals(apiObject.getData().get("CPU model"), "Intel Core i9", "CPU model should match");
        softAssert.assertEquals(apiObject.getData().get("Hard disk size"), "1 TB", "Hard disk size should match");
        
        softAssert.assertAll();
        logger.info("Test testGetObjectAsObject completed successfully");
    }

    @Test(priority = 3, description = "Verify response contains all required fields")
    public void testObjectResponseStructure() {
        logger.info("Starting test: testObjectResponseStructure");
        String objectId = "7";
        logger.info("Fetching object with ID: {}", objectId);
        
        Response response = objectService.getObjectById(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response structure - checking for required fields");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("data"))
                .body("data", hasKey("year"))
                .body("data", hasKey("price"))
                .body("data", hasKey("CPU model"))
                .body("data", hasKey("Hard disk size"));
        
        logger.info("Test testObjectResponseStructure completed successfully - all required fields present");
    }

    @Test(priority = 4, description = "Verify data types in response")
    public void testObjectDataTypes() {
        logger.info("Starting test: testObjectDataTypes");
        String objectId = "7";
        logger.info("Fetching object with ID: {}", objectId);
        
        Response response = objectService.getObjectById(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating data types of response fields");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", isA(String.class))
                .body("name", isA(String.class))
                .body("data.year", isA(Integer.class))
                .body("data.price", isA(Float.class))
                .body("data.'CPU model'", isA(String.class))
                .body("data.'Hard disk size'", isA(String.class));
        
        logger.info("Test testObjectDataTypes completed successfully - all data types are correct");
    }

    @Test(priority = 5, description = "Verify response time is acceptable")
    public void testObjectResponseTime() {
        logger.info("Starting test: testObjectResponseTime");
        String objectId = "7";
        logger.info("Fetching object with ID: {}", objectId);
        
        Response response = objectService.getObjectById(objectId);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time is less than 3000ms");
        response.then().log().status()
                .statusCode(200)
                .time(lessThan(3000L));
        
        logger.info("Test testObjectResponseTime completed successfully - response time: {} ms", responseTime);
    }

    @Test(priority = 6, description = "Verify content type is application/json")
    public void testObjectContentType() {
        logger.info("Starting test: testObjectContentType");
        String objectId = "7";
        logger.info("Fetching object with ID: {}", objectId);
        
        Response response = objectService.getObjectById(objectId);
        logger.info("Response received with content type: {}", response.getContentType());
        
        logger.info("Validating content type is application/json");
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json");
        
        logger.info("Test testObjectContentType completed successfully");
    }

    @Test(priority = 7, description = "Verify getting all objects")
    public void testGetAllObjects() {
        logger.info("Starting test: testGetAllObjects");
        logger.info("Fetching all objects from API");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains multiple objects");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertTrue(response.jsonPath().getList("$").size() > 0, "Response should contain at least one object");
        
        response.then().log().status()
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()));
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjects completed successfully");
    }

    @Test(priority = 8, description = "Verify creating a new object with complete data")
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

    @Test(priority = 11, description = "Verify creating Apple MacBook Pro 16 as specified in API documentation")
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

    @Test(priority = 12, description = "Verify created object contains ID and createdAt fields")
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

    @Test(priority = 13, description = "Verify creating object with minimum required fields")
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

    @Test(priority = 14, description = "Verify creating multiple objects with different data")
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

    @Test(priority = 15, description = "Verify POST request content type and response time")
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

    @Test(priority = 9, description = "Verify updating an object")
    public void testUpdateObject() {
        logger.info("Starting test: testUpdateObject");
        String objectId = "7";
        logger.info("Preparing updated data for object ID: {}", objectId);
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2024);
        updatedData.put("price", 2999.99);
        updatedData.put("CPU model", "M3 Max");
        updatedData.put("Hard disk size", "4 TB");

        ApiObject updatedObject = ApiObject.builder()
                .name("Apple MacBook Pro 16 Updated")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} with new data", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating updated object data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("name", equalTo(updatedObject.getName()));
        
        logger.info("Test testUpdateObject completed successfully - object updated");
    }

    @Test(priority = 10, description = "Verify deleting an object")
    public void testDeleteObject() {
        logger.info("Starting test: testDeleteObject");
        String objectId = "7";
        logger.info("Deleting object with ID: {}", objectId);
        
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating delete operation");
        response.then().log().status()
                .statusCode(200);
        
        logger.info("Test testDeleteObject completed successfully - object deleted");
    }
}
