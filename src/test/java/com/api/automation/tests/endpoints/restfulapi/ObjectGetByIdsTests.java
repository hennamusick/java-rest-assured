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
 * Test class for Object API GET endpoints - List of objects by IDs from restful-api.dev
 * Tests filtering objects by multiple IDs using query parameters
 */
public class ObjectGetByIdsTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectGetByIdsTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectGetByIdsTests() {
        logger.info("Setting up ObjectGetByIdsTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify getting multiple objects by IDs - basic retrieval")
    public void testGetObjectsByIds() {
        logger.info("Starting test: testGetObjectsByIds");
        String[] objectIds = {"3", "5", "10"};
        logger.info("Fetching objects with IDs: {}", String.join(", ", objectIds));
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains all requested objects");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("size()", equalTo(3))
                .body("id", hasItems("3", "5", "10"))
                .body("name", everyItem(notNullValue()));
        
        logger.info("Test testGetObjectsByIds completed successfully");
    }

    @Test(priority = 2, description = "Verify getting objects by IDs as POJO array")
    public void testGetObjectsByIdsAsArray() {
        logger.info("Starting test: testGetObjectsByIdsAsArray");
        String[] objectIds = {"3", "5", "10"};
        logger.info("Fetching objects with IDs: {} and deserializing to ApiObject array", String.join(", ", objectIds));
        
        ApiObject[] apiObjects = objectService.getObjectsByIdsAsArray(objectIds);
        logger.info("ApiObject array received with {} objects", apiObjects.length);
        
        logger.info("Validating ApiObject array");
        softAssert.assertEquals(apiObjects.length, 3, "Should have 3 objects");
        
        // Validate each object
        softAssert.assertNotNull(apiObjects[0], "First object should not be null");
        softAssert.assertEquals(apiObjects[0].getId(), "3", "First object ID should be 3");
        softAssert.assertEquals(apiObjects[0].getName(), "Apple iPhone 12 Pro Max", "First object name should match");
        
        softAssert.assertNotNull(apiObjects[1], "Second object should not be null");
        softAssert.assertEquals(apiObjects[1].getId(), "5", "Second object ID should be 5");
        softAssert.assertEquals(apiObjects[1].getName(), "Samsung Galaxy Z Fold2", "Second object name should match");
        
        softAssert.assertNotNull(apiObjects[2], "Third object should not be null");
        softAssert.assertEquals(apiObjects[2].getId(), "10", "Third object ID should be 10");
        softAssert.assertEquals(apiObjects[2].getName(), "Apple iPad Mini 5th Gen", "Third object name should match");
        
        softAssert.assertAll();
        logger.info("Test testGetObjectsByIdsAsArray completed successfully");
    }

    @Test(priority = 3, description = "Verify response structure for objects retrieved by IDs")
    public void testObjectsByIdsResponseStructure() {
        logger.info("Starting test: testObjectsByIdsResponseStructure");
        String[] objectIds = {"3", "5", "10"};
        logger.info("Fetching objects with IDs and validating response structure");
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating each object has required fields");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("every { it.containsKey('id') && it.containsKey('name') && it.containsKey('data') }", equalTo(true))
                .body("[0].id", equalTo("3"))
                .body("[0].name", equalTo("Apple iPhone 12 Pro Max"))
                .body("[0].data", hasKey("color"))
                .body("[1].id", equalTo("5"))
                .body("[1].name", equalTo("Samsung Galaxy Z Fold2"))
                .body("[2].id", equalTo("10"))
                .body("[2].name", equalTo("Apple iPad Mini 5th Gen"));
        
        logger.info("Test testObjectsByIdsResponseStructure completed successfully");
    }

    @Test(priority = 4, description = "Verify single object retrieval using query parameter")
    public void testGetSingleObjectByIdQueryParam() {
        logger.info("Starting test: testGetSingleObjectByIdQueryParam");
        String objectId = "3";
        logger.info("Fetching single object with ID: {} using query parameter", objectId);
        
        Response response = objectService.getObjectsByIds(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains the requested object");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].id", equalTo(objectId))
                .body("[0].name", equalTo("Apple iPhone 12 Pro Max"))
                .body("[0].data.color", equalTo("Cloudy White"));
        
        logger.info("Test testGetSingleObjectByIdQueryParam completed successfully");
    }

    @Test(priority = 5, description = "Verify data field contents for each retrieved object")
    public void testObjectsByIdsDataContent() {
        logger.info("Starting test: testObjectsByIdsDataContent");
        String[] objectIds = {"3", "5", "10"};
        logger.info("Fetching objects and validating data field contents");
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating data fields for each object");
        response.then().log().status()
                .statusCode(200)
                // Object 3 - iPhone
                .body("[0].data.color", equalTo("Cloudy White"))
                .body("[0].data.'capacity GB'", equalTo(512))
                // Object 5 - Samsung Galaxy
                .body("[1].data.price", equalTo(689.99f))
                .body("[1].data.color", equalTo("Brown"))
                // Object 10 - iPad
                .body("[2].data.Capacity", equalTo("64 GB"))
                .body("[2].data.'Screen size'", equalTo(7.9f));
        
        logger.info("Test testObjectsByIdsDataContent completed successfully");
    }

    @Test(priority = 6, description = "Verify content type and response headers")
    public void testObjectsByIdsContentType() {
        logger.info("Starting test: testObjectsByIdsContentType");
        String[] objectIds = {"3", "5"};
        logger.info("Fetching objects and validating content type");
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        logger.info("Response content type: {}", response.getContentType());
        
        logger.info("Validating content type is application/json");
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json");
        
        logger.info("Test testObjectsByIdsContentType completed successfully");
    }

    @Test(priority = 7, description = "Verify response time for retrieving multiple objects")
    public void testObjectsByIdsResponseTime() {
        logger.info("Starting test: testObjectsByIdsResponseTime");
        String[] objectIds = {"3", "5", "10"};
        logger.info("Fetching objects and measuring response time");
        
        Response response = objectService.getObjectsByIds(objectIds);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time is acceptable");
        response.then().log().status()
                .statusCode(200)
                .time(lessThan(3000L));
        
        logger.info("Test testObjectsByIdsResponseTime completed - response time: {} ms", responseTime);
    }

    @Test(priority = 8, description = "Verify multiple IDs with mixed order")
    public void testGetObjectsByIdsMixedOrder() {
        logger.info("Starting test: testGetObjectsByIdsMixedOrder");
        String[] objectIds = {"10", "3", "5"};
        logger.info("Fetching objects with mixed order IDs: {}", String.join(", ", objectIds));
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating all objects are returned regardless of ID order");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getList("$").size(), 3, "Should have 3 objects");
        softAssert.assertTrue(response.jsonPath().getList("id").contains("3"), "Should contain ID 3");
        softAssert.assertTrue(response.jsonPath().getList("id").contains("5"), "Should contain ID 5");
        softAssert.assertTrue(response.jsonPath().getList("id").contains("10"), "Should contain ID 10");
        
        response.then().log().status();
        softAssert.assertAll();
        logger.info("Test testGetObjectsByIdsMixedOrder completed successfully");
    }

    @Test(priority = 9, description = "Verify handling of duplicate IDs in query")
    public void testGetObjectsByIdsDuplicates() {
        logger.info("Starting test: testGetObjectsByIdsDuplicates");
        String[] objectIds = {"3", "5", "3"};
        logger.info("Fetching objects with duplicate IDs: {}", String.join(", ", objectIds));
        
        Response response = objectService.getObjectsByIds(objectIds);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response handles duplicate IDs correctly");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", hasItems("3", "5"));
        
        logger.info("Test testGetObjectsByIdsDuplicates completed successfully");
    }
}
