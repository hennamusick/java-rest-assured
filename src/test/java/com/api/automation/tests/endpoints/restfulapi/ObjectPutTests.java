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
 * Test class for Object API PUT endpoints from restful-api.dev
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class ObjectPutTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectPutTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectPutTests() {
        logger.info("Setting up ObjectPutTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify updating an object with complete data replacement")
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
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertNotNull(response, "Response should not be null");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16 Updated", "Name should be updated");
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("name", equalTo(updatedObject.getName()));
        
        softAssert.assertAll();
        logger.info("Test testUpdateObject completed successfully - object updated");
    }

    @Test(priority = 2, description = "Verify PUT update with API documentation example")
    public void testUpdateObjectApiDocExample() {
        logger.info("Starting test: testUpdateObjectApiDocExample");
        String objectId = "7";
        logger.info("Preparing update data matching API documentation example");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2019);
        updatedData.put("price", 2049.99);
        updatedData.put("CPU model", "Intel Core i9");
        updatedData.put("Hard disk size", "1 TB");
        updatedData.put("color", "silver");

        ApiObject updatedObject = ApiObject.builder()
                .name("Apple MacBook Pro 16")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} with API doc example data", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating updated object matches request data");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16", "Name should match");
        softAssert.assertEquals(response.jsonPath().getInt("data.year"), 2019, "Year should be 2019");
        softAssert.assertEquals(response.jsonPath().getFloat("data.price"), 2049.99f, "Price should be 2049.99");
        softAssert.assertEquals(response.jsonPath().getString("data.'CPU model'"), "Intel Core i9", "CPU model should match");
        softAssert.assertEquals(response.jsonPath().getString("data.'Hard disk size'"), "1 TB", "Hard disk size should match");
        softAssert.assertEquals(response.jsonPath().getString("data.color"), "silver", "Color should be silver");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testUpdateObjectApiDocExample completed successfully");
    }

    @Test(priority = 3, description = "Verify PUT update response contains updatedAt field")
    public void testUpdateObjectResponseStructure() {
        logger.info("Starting test: testUpdateObjectResponseStructure");
        String objectId = "7";
        logger.info("Preparing update data to validate response structure");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2023);
        updatedData.put("price", 1999.99);
        updatedData.put("CPU model", "M2 Pro");
        updatedData.put("Hard disk size", "512 GB");

        ApiObject updatedObject = ApiObject.builder()
                .name("Apple MacBook Pro 14")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} and validating response structure", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains all required fields including updatedAt");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("data"))
                .body("$", hasKey("updatedAt"))
                .body("id", equalTo(objectId))
                .body("updatedAt", notNullValue())
                .body("updatedAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
        
        logger.info("Test testUpdateObjectResponseStructure completed successfully");
    }

    @Test(priority = 4, description = "Verify PUT update with additional fields")
    public void testUpdateObjectWithAdditionalFields() {
        logger.info("Starting test: testUpdateObjectWithAdditionalFields");
        String objectId = "7";
        logger.info("Preparing update with additional custom fields");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2023);
        updatedData.put("price", 2299.99);
        updatedData.put("CPU model", "M3");
        updatedData.put("Hard disk size", "1 TB");
        updatedData.put("RAM", "16 GB");
        updatedData.put("Screen size", "14 inch");
        updatedData.put("color", "Space Gray");

        ApiObject updatedObject = ApiObject.builder()
                .name("Apple MacBook Pro 14 Enhanced")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} with additional fields", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating all fields including additional ones");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 14 Enhanced", "Name should match");
        softAssert.assertEquals(response.jsonPath().getString("data.RAM"), "16 GB", "RAM should be present");
        softAssert.assertEquals(response.jsonPath().getString("data.'Screen size'"), "14 inch", "Screen size should be present");
        softAssert.assertEquals(response.jsonPath().getString("data.color"), "Space Gray", "Color should be present");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testUpdateObjectWithAdditionalFields completed successfully");
    }

    @Test(priority = 5, description = "Verify PUT update with price change only")
    public void testUpdateObjectPriceChange() {
        logger.info("Starting test: testUpdateObjectPriceChange");
        String objectId = "7";
        logger.info("Preparing update to change only price field");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2019);
        updatedData.put("price", 1749.99);
        updatedData.put("CPU model", "Intel Core i9");
        updatedData.put("Hard disk size", "1 TB");

        ApiObject updatedObject = ApiObject.builder()
                .name("Apple MacBook Pro 16")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} with new price", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating price was updated successfully");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("data.price", equalTo(1749.99f))
                .body("name", equalTo("Apple MacBook Pro 16"));
        
        logger.info("Test testUpdateObjectPriceChange completed successfully");
    }

    @Test(priority = 6, description = "Verify PUT update with minimal data")
    public void testUpdateObjectMinimalData() {
        logger.info("Starting test: testUpdateObjectMinimalData");
        String objectId = "7";
        logger.info("Preparing update with minimal data fields");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2024);

        ApiObject updatedObject = ApiObject.builder()
                .name("Updated Device")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object ID: {} with minimal data", objectId);
        Response response = objectService.updateObject(objectId, updatedObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating minimal update was successful");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Updated Device", "Name should be updated");
        softAssert.assertEquals(response.jsonPath().getInt("data.year"), 2024, "Year should be updated");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testUpdateObjectMinimalData completed successfully");
    }

    @Test(priority = 7, description = "Verify PUT request response time and content type")
    public void testUpdateObjectPerformance() {
        logger.info("Starting test: testUpdateObjectPerformance");
        String objectId = "7";
        logger.info("Testing PUT request performance and content type");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2023);
        updatedData.put("price", 1899.99);
        updatedData.put("CPU model", "M2");
        updatedData.put("Hard disk size", "512 GB");

        ApiObject updatedObject = ApiObject.builder()
                .name("Performance Test Update")
                .data(updatedData)
                .build();

        logger.info("Request JSON body for update: \n{}", JsonUtils.serialize(updatedObject));
        logger.info("Updating object and measuring response time");
        Response response = objectService.updateObject(objectId, updatedObject);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time and content type");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertTrue(responseTime < 3000, "Response time should be less than 3000 ms");
        softAssert.assertTrue(responseTime > 0, "Response time should be greater than 0");
        String contentType = response.getContentType();
        softAssert.assertNotNull(contentType, "Content-Type should not be null");
        softAssert.assertTrue(contentType.contains("application/json"), "Content-Type should be application/json");
        
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json")
                .time(lessThan(3000L));
        
        softAssert.assertAll();
        logger.info("Test testUpdateObjectPerformance completed - response time: {} ms", responseTime);
    }
}
