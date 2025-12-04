package com.api.automation.tests.endpoints.restfulapi;

import com.api.automation.models.ApiObject;
import com.api.automation.services.ObjectService;
import com.api.automation.tests.utils.BaseTest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;

/**
 * Test class for Object API GET endpoints from restful-api.dev
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class ObjectGetTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectGetTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectGetTests() {
        logger.info("Setting up ObjectGetTests - initializing ObjectService");
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
}
