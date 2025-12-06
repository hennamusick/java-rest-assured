package com.api.automation.tests.endpoints.restfulapi;

import com.api.automation.services.ObjectService;
import com.api.automation.tests.utils.AssertionHelper;
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
    private AssertionHelper assertionHelper;

    @BeforeClass
    public void setUpObjectDeleteTests() {
        logger.info("Setting up ObjectDeleteTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
        assertionHelper = new AssertionHelper(softAssert, logger);
    }

    @Test(priority = 1, description = "Verify deleting an existing object returns 200 status")
    public void testDeleteObjectStatusCode() {
        assertionHelper.logTestStart("testDeleteObjectStatusCode");
        
        String objectId = "6";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        response.then().log().status().log().body()
                .statusCode(200);
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectStatusCode");
    }

    @Test(priority = 2, description = "Verify delete response contains success message")
    public void testDeleteObjectResponseMessage() {
        assertionHelper.logTestStart("testDeleteObjectResponseMessage");
        
        String objectId = "7";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response message format");
        String message = response.jsonPath().getString("message");
        assertionHelper.assertFieldNotEmpty(response, "message", "Message");
        assertionHelper.assertFieldContains(response, "message", "deleted", "Message");
        logger.info("Delete message received: {}", message);
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", notNullValue())
                .body("message", containsString("deleted"));
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectResponseMessage");
    }

    @Test(priority = 3, description = "Verify delete response contains object ID in message")
    public void testDeleteObjectResponseContainsId() {
        assertionHelper.logTestStart("testDeleteObjectResponseContainsId");
        
        String objectId = "8";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response message contains the deleted object ID");
        String message = response.jsonPath().getString("message");
        assertionHelper.assertFieldNotNull(response, "message", "Message");
        softAssert.assertTrue(message.contains(objectId), "Message should contain ID: " + objectId);
        softAssert.assertTrue(message.contains("deleted"), "Message should contain 'deleted'");
        logger.info("Verified message contains ID {}: {}", objectId, message);
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", containsString(objectId));
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectResponseContainsId");
    }

    @Test(priority = 4, description = "Verify content type of delete response is JSON")
    public void testDeleteObjectContentType() {
        assertionHelper.logTestStart("testDeleteObjectContentType");
        
        String objectId = "9";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response content type");
        assertionHelper.assertContentType(response, "application/json");
        
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json");
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectContentType");
    }

    @Test(priority = 5, description = "Verify response time of delete operation")
    public void testDeleteObjectResponseTime() {
        assertionHelper.logTestStart("testDeleteObjectResponseTime");
        
        String objectId = "10";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        long responseTime = response.getTime();
        
        assertionHelper.logValidation("response time is acceptable (less than 3000 ms)");
        assertionHelper.assertStatusCode(response, 200, "DELETE object response");
        assertionHelper.assertResponseTime(response, 3000, "DELETE operation");
        
        response.then().log().status()
                .statusCode(200)
                .time(lessThan(3000L));
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectResponseTime", "response time: " + responseTime + " ms");
    }

    @Test(priority = 6, description = "Verify delete response has only message field")
    public void testDeleteObjectResponseStructure() {
        assertionHelper.logTestStart("testDeleteObjectResponseStructure");
        
        String objectId = "11";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response structure contains message field");
        assertionHelper.assertFieldNotEmpty(response, "message", "Message");
        assertionHelper.assertResponseHasKey(response, "message");
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("message"))
                .body("message", notNullValue())
                .body("message", not(emptyString()));
        
        assertionHelper.assertAll();
        logger.info("Response structure validated successfully");
        assertionHelper.logTestCompletion("testDeleteObjectResponseStructure");
    }

    @Test(priority = 7, description = "Verify deleting multiple objects sequentially")
    public void testDeleteMultipleObjectsSequentially() {
        assertionHelper.logTestStart("testDeleteMultipleObjectsSequentially");
        logger.info("Deleting multiple objects sequentially to verify API consistency");
        
        String[] objectIds = {"12", "13", "14"};
        
        for (String objectId : objectIds) {
            assertionHelper.logRequest("DELETE", objectId);
            Response response = objectService.deleteObject(objectId);
            assertionHelper.assertStatusCode(response, 200, "object " + objectId);
            assertionHelper.assertFieldNotNull(response, "message", "Message for object " + objectId);
            
            String message = response.jsonPath().getString("message");
            logger.info("Object {} deleted with message: {}", objectId, message);
        }
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteMultipleObjectsSequentially");
    }

    @Test(priority = 8, description = "Verify message format matches pattern: Object with id = X, has been deleted")
    public void testDeleteObjectMessageFormat() {
        assertionHelper.logTestStart("testDeleteObjectMessageFormat");
        
        String objectId = "15";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        
        String message = response.jsonPath().getString("message");
        assertionHelper.logValidation("message format against pattern");
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("message", matchesPattern("Object with id = \\d+, has been deleted\\."));
        
        logger.info("Message format verified: {}", message);
        assertionHelper.logTestCompletion("testDeleteObjectMessageFormat");
    }

    @Test(priority = 9, description = "Verify delete object with specific ID contains correct ID in response")
    public void testDeleteObjectIdInResponseMessage() {
        assertionHelper.logTestStart("testDeleteObjectIdInResponseMessage");
        
        String objectId = "16";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        
        String message = response.jsonPath().getString("message");
        logger.info("Response message: {}", message);
        
        assertionHelper.logExtraction("ID from message and comparing with request ID");
        assertionHelper.assertStatusCode(response, 200, "DELETE operation");
        softAssert.assertTrue(message.contains(objectId), "Message should contain the deleted object ID: " + objectId);
        softAssert.assertTrue(message.endsWith("deleted."), "Message should end with 'deleted.'");
        
        response.then().log().status().log().body();
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectIdInResponseMessage");
    }

    @Test(priority = 10, description = "Verify delete operation does not return object data")
    public void testDeleteObjectResponseDoesNotContainObjectData() {
        assertionHelper.logTestStart("testDeleteObjectResponseDoesNotContainObjectData");
        
        String objectId = "17";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response does not contain object fields (name, data, createdAt)");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", not(hasKey("name")))
                .body("$", not(hasKey("data")))
                .body("$", not(hasKey("id")))
                .body("$", not(hasKey("createdAt")));
        
        logger.info("Verified response contains only message field");
        assertionHelper.logTestCompletion("testDeleteObjectResponseDoesNotContainObjectData");
    }

    @Test(priority = 11, description = "Verify delete response has only one JSON key (message)")
    public void testDeleteObjectResponseHasOnlyMessageKey() {
        assertionHelper.logTestStart("testDeleteObjectResponseHasOnlyMessageKey");
        
        String objectId = "18";
        assertionHelper.logRequest("DELETE", objectId);
        Response response = objectService.deleteObject(objectId);
        assertionHelper.assertStatusCodeAndLogging(response, 200);
        
        assertionHelper.logValidation("response has only message key");
        int keyCount = response.jsonPath().getMap("$").size();
        logger.info("Number of keys in response: {}", keyCount);
        
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("message"));
        
        softAssert.assertEquals(keyCount, 1, "Response should contain exactly one key (message)");
        assertionHelper.assertResponseHasKey(response, "message");
        
        assertionHelper.assertAll();
        assertionHelper.logTestCompletion("testDeleteObjectResponseHasOnlyMessageKey");
    }
}
