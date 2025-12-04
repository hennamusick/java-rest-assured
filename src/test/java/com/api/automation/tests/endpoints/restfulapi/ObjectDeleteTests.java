package com.api.automation.tests.endpoints.restfulapi;

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
 * Test class for Object API DELETE endpoints from restful-api.dev
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class ObjectDeleteTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDeleteTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectDeleteTests() {
        logger.info("Setting up ObjectDeleteTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify deleting an existing object returns 200 status")
    public void testDeleteObjectStatusCode() {
        logger.info("Starting test: testDeleteObjectStatusCode");
        logger.info("Test will delete object with ID: 6");
        
        String objectId = "6";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating status code is 200 (OK)");
        response.then().log().status().log().body()
                .statusCode(200);
        
        logger.info("Test testDeleteObjectStatusCode completed successfully");
    }

    @Test(priority = 2, description = "Verify delete response contains success message")
    public void testDeleteObjectResponseMessage() {
        logger.info("Starting test: testDeleteObjectResponseMessage");
        logger.info("Test will delete object with ID: 7");
        
        String objectId = "7";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response message format");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", notNullValue())
                .body("message", containsString("deleted"));
        
        String message = response.jsonPath().getString("message");
        logger.info("Delete message received: {}", message);
        logger.info("Test testDeleteObjectResponseMessage completed successfully");
    }

    @Test(priority = 3, description = "Verify delete response contains object ID in message")
    public void testDeleteObjectResponseContainsId() {
        logger.info("Starting test: testDeleteObjectResponseContainsId");
        logger.info("Test will delete object with ID: 8");
        
        String objectId = "8";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response message contains the deleted object ID");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", containsString(objectId));
        
        String message = response.jsonPath().getString("message");
        logger.info("Verified message contains ID {}: {}", objectId, message);
        logger.info("Test testDeleteObjectResponseContainsId completed successfully");
    }

    @Test(priority = 4, description = "Verify content type of delete response is JSON")
    public void testDeleteObjectContentType() {
        logger.info("Starting test: testDeleteObjectContentType");
        logger.info("Test will delete object with ID: 9");
        
        String objectId = "9";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response content type");
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json");
        
        logger.info("Content type verified as application/json");
        logger.info("Test testDeleteObjectContentType completed successfully");
    }

    @Test(priority = 5, description = "Verify response time of delete operation")
    public void testDeleteObjectResponseTime() {
        logger.info("Starting test: testDeleteObjectResponseTime");
        logger.info("Test will delete object with ID: 10");
        
        String objectId = "10";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        
        logger.info("Validating response time is acceptable (less than 3000 ms)");
        response.then().log().status()
                .statusCode(200)
                .time(lessThan(3000L));
        
        logger.info("Test testDeleteObjectResponseTime completed successfully - response time: {} ms", responseTime);
    }

    @Test(priority = 6, description = "Verify delete response has only message field")
    public void testDeleteObjectResponseStructure() {
        logger.info("Starting test: testDeleteObjectResponseStructure");
        logger.info("Test will delete object with ID: 11");
        
        String objectId = "11";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response structure contains message field");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("message"))
                .body("message", notNullValue())
                .body("message", not(emptyString()));
        
        logger.info("Response structure validated successfully");
        logger.info("Test testDeleteObjectResponseStructure completed successfully");
    }

    @Test(priority = 7, description = "Verify deleting multiple objects sequentially")
    public void testDeleteMultipleObjectsSequentially() {
        logger.info("Starting test: testDeleteMultipleObjectsSequentially");
        logger.info("Deleting multiple objects sequentially to verify API consistency");
        
        String[] objectIds = {"12", "13", "14"};
        
        for (String objectId : objectIds) {
            logger.info("Deleting object with ID: {}", objectId);
            Response response = objectService.deleteObject(objectId);
            logger.info("Response received with status code: {}", response.getStatusCode());
            
            softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 for object " + objectId);
            softAssert.assertNotNull(response.jsonPath().getString("message"), 
                                    "Message should not be null for object " + objectId);
            
            String message = response.jsonPath().getString("message");
            logger.info("Object {} deleted with message: {}", objectId, message);
        }
        
        softAssert.assertAll();
        logger.info("Test testDeleteMultipleObjectsSequentially completed successfully");
    }

    @Test(priority = 8, description = "Verify message format matches pattern: Object with id = X, has been deleted")
    public void testDeleteObjectMessageFormat() {
        logger.info("Starting test: testDeleteObjectMessageFormat");
        logger.info("Test will delete object with ID: 15");
        
        String objectId = "15";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        String message = response.jsonPath().getString("message");
        logger.info("Validating message format against pattern");
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", matchesPattern("Object with id = \\d+, has been deleted\\."));
        
        logger.info("Message format verified: {}", message);
        logger.info("Test testDeleteObjectMessageFormat completed successfully");
    }

    @Test(priority = 9, description = "Verify delete object with specific ID contains correct ID in response")
    public void testDeleteObjectIdInResponseMessage() {
        logger.info("Starting test: testDeleteObjectIdInResponseMessage");
        logger.info("Test will delete object with ID: 16");
        
        String objectId = "16";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        String message = response.jsonPath().getString("message");
        logger.info("Response message: {}", message);
        
        logger.info("Extracting ID from message and comparing with request ID");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertTrue(message.contains(objectId), "Message should contain the deleted object ID: " + objectId);
        softAssert.assertTrue(message.endsWith("deleted."), "Message should end with 'deleted.'");
        
        response.then().log().status().log().body();
        softAssert.assertAll();
        logger.info("Test testDeleteObjectIdInResponseMessage completed successfully");
    }

    @Test(priority = 10, description = "Verify delete operation does not return object data")
    public void testDeleteObjectResponseDoesNotContainObjectData() {
        logger.info("Starting test: testDeleteObjectResponseDoesNotContainObjectData");
        logger.info("Test will delete object with ID: 17");
        
        String objectId = "17";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response does not contain object fields (name, data, createdAt)");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", not(hasKey("name")))
                .body("$", not(hasKey("data")))
                .body("$", not(hasKey("id")))
                .body("$", not(hasKey("createdAt")));
        
        logger.info("Verified response contains only message field");
        logger.info("Test testDeleteObjectResponseDoesNotContainObjectData completed successfully");
    }

    @Test(priority = 11, description = "Verify delete response has only one JSON key (message)")
    public void testDeleteObjectResponseHasOnlyMessageKey() {
        logger.info("Starting test: testDeleteObjectResponseHasOnlyMessageKey");
        logger.info("Test will delete object with ID: 18");
        
        String objectId = "18";
        logger.info("Sending DELETE request for object ID: {}", objectId);
        Response response = objectService.deleteObject(objectId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response has only message key");
        int keyCount = response.jsonPath().getMap("$").size();
        logger.info("Number of keys in response: {}", keyCount);
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("message"));
        
        softAssert.assertEquals(keyCount, 1, "Response should contain exactly one key (message)");
        softAssert.assertTrue(response.jsonPath().getMap("$").containsKey("message"), 
                            "Response should contain message key");
        
        softAssert.assertAll();
        logger.info("Test testDeleteObjectResponseHasOnlyMessageKey completed successfully");
    }
}
