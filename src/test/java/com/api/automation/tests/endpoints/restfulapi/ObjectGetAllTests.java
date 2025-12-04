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

import java.util.List;

import static org.hamcrest.Matchers.*;

/**
 * Test class for Object API GET all objects endpoints from restful-api.dev
 * Comprehensive tests for listing all available objects
 */
public class ObjectGetAllTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectGetAllTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectGetAllTests() {
        logger.info("Setting up ObjectGetAllTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify getting all objects returns 200 status code")
    public void testGetAllObjectsStatusCode() {
        logger.info("Starting test: testGetAllObjectsStatusCode");
        logger.info("Fetching all objects from API");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating status code is 200");
        response.then().log().status()
                .statusCode(200);
        
        logger.info("Test testGetAllObjectsStatusCode completed successfully");
    }

    @Test(priority = 2, description = "Verify all objects response is an array")
    public void testGetAllObjectsIsArray() {
        logger.info("Starting test: testGetAllObjectsIsArray");
        logger.info("Fetching all objects");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response is a JSON array");
        response.then().log().status()
                .statusCode(200)
                .body("$", isA(List.class));
        
        logger.info("Test testGetAllObjectsIsArray completed successfully");
    }

    @Test(priority = 3, description = "Verify all objects contain required fields (id, name, data)")
    public void testGetAllObjectsRequiredFields() {
        logger.info("Starting test: testGetAllObjectsRequiredFields");
        logger.info("Fetching all objects and validating required fields");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating each object has id and name fields");
        response.then().log().status()
                .statusCode(200)
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()));
        
        logger.info("Test testGetAllObjectsRequiredFields completed successfully");
    }

    @Test(priority = 4, description = "Verify all objects are returned as ApiObject array")
    public void testGetAllObjectsAsArray() {
        logger.info("Starting test: testGetAllObjectsAsArray");
        logger.info("Fetching all objects and deserializing to ApiObject array");
        
        ApiObject[] apiObjects = objectService.getAllObjectsAsArray();
        logger.info("ApiObject array received with {} objects", apiObjects.length);
        
        logger.info("Validating array is not empty");
        softAssert.assertTrue(apiObjects.length > 0, "Objects array should not be empty");
        softAssert.assertTrue(apiObjects.length >= 13, "Should have at least 13 objects");
        
        logger.info("Validating first object contains expected data");
        softAssert.assertNotNull(apiObjects[0], "First object should not be null");
        softAssert.assertNotNull(apiObjects[0].getId(), "First object ID should not be null");
        softAssert.assertNotNull(apiObjects[0].getName(), "First object name should not be null");
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsAsArray completed successfully");
    }

    @Test(priority = 5, description = "Verify minimum object count returned")
    public void testGetAllObjectsMinimumCount() {
        logger.info("Starting test: testGetAllObjectsMinimumCount");
        logger.info("Fetching all objects and verifying count");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        int objectCount = response.jsonPath().getList("$").size();
        logger.info("Total objects returned: {}", objectCount);
        
        logger.info("Validating at least 13 objects are returned");
        softAssert.assertTrue(objectCount >= 13, "Should have at least 13 objects");
        
        response.then().log().status()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(13));
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsMinimumCount completed successfully");
    }

    @Test(priority = 6, description = "Verify all objects have unique IDs")
    public void testGetAllObjectsUniqueIds() {
        logger.info("Starting test: testGetAllObjectsUniqueIds");
        logger.info("Fetching all objects and validating ID uniqueness");
        
        ApiObject[] apiObjects = objectService.getAllObjectsAsArray();
        logger.info("ApiObject array received with {} objects", apiObjects.length);
        
        logger.info("Validating all IDs are unique");
        java.util.Set<String> idSet = new java.util.HashSet<>();
        for (ApiObject obj : apiObjects) {
            softAssert.assertTrue(idSet.add(obj.getId()), "ID " + obj.getId() + " appears more than once");
        }
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsUniqueIds completed successfully");
    }

    @Test(priority = 7, description = "Verify specific objects exist in the list")
    public void testGetAllObjectsContainsSpecificItems() {
        logger.info("Starting test: testGetAllObjectsContainsSpecificItems");
        logger.info("Fetching all objects and verifying specific items");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating specific objects exist in the response");
        response.then().log().status()
                .statusCode(200)
                .body("id", hasItems("1", "2", "3", "5", "7", "10"))
                .body("name", hasItem("Google Pixel 6 Pro"))
                .body("name", hasItem("Apple MacBook Pro 16"))
                .body("name", hasItem("Samsung Galaxy Z Fold2"))
                .body("name", hasItem("Apple Watch Series 8"));
        
        logger.info("Test testGetAllObjectsContainsSpecificItems completed successfully");
    }

    @Test(priority = 8, description = "Verify objects with null data field")
    public void testGetAllObjectsWithNullData() {
        logger.info("Starting test: testGetAllObjectsWithNullData");
        logger.info("Fetching all objects and verifying null data handling");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains object with null data");
        // Apple iPhone 12 Mini (id=2) has null data
        response.then().log().status()
                .statusCode(200)
                .body("find { it.id == '2' }.data", nullValue());
        
        logger.info("Test testGetAllObjectsWithNullData completed successfully");
    }

    @Test(priority = 9, description = "Verify objects with different data structures")
    public void testGetAllObjectsVariableDataStructures() {
        logger.info("Starting test: testGetAllObjectsVariableDataStructures");
        logger.info("Fetching all objects and validating diverse data structures");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating objects have different data field types and contents");
        response.then().log().status()
                .statusCode(200)
                // Object with color and capacity
                .body("find { it.id == '1' }.data.color", equalTo("Cloudy White"))
                .body("find { it.id == '1' }.data.capacity", equalTo("128 GB"))
                // Object with price and color
                .body("find { it.id == '4' }.data.price", equalTo(389.99f))
                .body("find { it.id == '4' }.data.color", equalTo("Purple"))
                // Object with generation and price
                .body("find { it.id == '6' }.data.generation", equalTo("3rd"))
                .body("find { it.id == '6' }.data.price", equalTo(120))
                // Object with year, price, CPU model, and hard disk size
                .body("find { it.id == '7' }.data.year", equalTo(2019))
                .body("find { it.id == '7' }.data.price", equalTo(1849.99f))
                .body("find { it.id == '7' }.data.'CPU model'", equalTo("Intel Core i9"));
        
        logger.info("Test testGetAllObjectsVariableDataStructures completed successfully");
    }

    @Test(priority = 10, description = "Verify content type is application/json")
    public void testGetAllObjectsContentType() {
        logger.info("Starting test: testGetAllObjectsContentType");
        logger.info("Fetching all objects and validating content type");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        logger.info("Response content type: {}", response.getContentType());
        
        logger.info("Validating content type is application/json");
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json");
        
        logger.info("Test testGetAllObjectsContentType completed successfully");
    }

    @Test(priority = 11, description = "Verify response time for getting all objects")
    public void testGetAllObjectsResponseTime() {
        logger.info("Starting test: testGetAllObjectsResponseTime");
        logger.info("Fetching all objects and measuring response time");
        
        Response response = objectService.getAllObjects();
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time is acceptable");
        response.then().log().status()
                .statusCode(200)
                .time(lessThan(3000L));
        
        logger.info("Test testGetAllObjectsResponseTime completed - response time: {} ms", responseTime);
    }

    @Test(priority = 12, description = "Verify no empty ID values")
    public void testGetAllObjectsNoEmptyIds() {
        logger.info("Starting test: testGetAllObjectsNoEmptyIds");
        logger.info("Fetching all objects and verifying ID values");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        List<String> ids = response.jsonPath().getList("id");
        logger.info("Validating all IDs are non-empty");
        
        softAssert.assertTrue(ids.size() > 0, "Should have IDs");
        for (String id : ids) {
            softAssert.assertNotNull(id, "ID should not be null");
            softAssert.assertFalse(id.isEmpty(), "ID should not be empty");
        }
        
        response.then().log().status()
                .statusCode(200);
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsNoEmptyIds completed successfully");
    }

    @Test(priority = 13, description = "Verify all object names are non-empty")
    public void testGetAllObjectsNonEmptyNames() {
        logger.info("Starting test: testGetAllObjectsNonEmptyNames");
        logger.info("Fetching all objects and verifying name values");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        List<String> names = response.jsonPath().getList("name");
        logger.info("Validating all names are non-empty");
        
        softAssert.assertTrue(names.size() > 0, "Should have names");
        for (String name : names) {
            softAssert.assertNotNull(name, "Name should not be null");
            softAssert.assertFalse(name.isEmpty(), "Name should not be empty");
        }
        
        response.then().log().status()
                .statusCode(200);
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsNonEmptyNames completed successfully");
    }

    @Test(priority = 14, description = "Verify objects with duplicate names")
    public void testGetAllObjectsDuplicateNames() {
        logger.info("Starting test: testGetAllObjectsDuplicateNames");
        logger.info("Fetching all objects and checking for duplicate names");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        // iPad Mini 5th Gen and iPad Air appear twice
        logger.info("Validating duplicate names exist as expected");
        List<String> names = response.jsonPath().getList("name");
        java.util.Collections.frequency(names, "Apple iPad Mini 5th Gen");
        int ipadMiniCount = java.util.Collections.frequency(names, "Apple iPad Mini 5th Gen");
        int ipadAirCount = java.util.Collections.frequency(names, "Apple iPad Air");
        
        softAssert.assertEquals(ipadMiniCount, 2, "Should have 2 iPad Mini 5th Gen entries");
        softAssert.assertEquals(ipadAirCount, 2, "Should have 2 iPad Air entries");
        
        response.then().log().status()
                .statusCode(200);
        
        softAssert.assertAll();
        logger.info("Test testGetAllObjectsDuplicateNames completed successfully");
    }

    @Test(priority = 15, description = "Verify consistent response structure across all objects")
    public void testGetAllObjectsConsistentStructure() {
        logger.info("Starting test: testGetAllObjectsConsistentStructure");
        logger.info("Fetching all objects and validating structure consistency");
        
        Response response = objectService.getAllObjects();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating all objects have consistent structure");
        response.then().log().status()
                .statusCode(200)
                .body("every { it.containsKey('id') && it.containsKey('name') && it.containsKey('data') }", equalTo(true));
        
        logger.info("Test testGetAllObjectsConsistentStructure completed successfully");
    }
}
