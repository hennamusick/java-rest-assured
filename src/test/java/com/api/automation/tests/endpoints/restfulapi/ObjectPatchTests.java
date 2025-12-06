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
 * Test class for Object API PATCH endpoints from restful-api.dev
 * Tests partial update functionality for objects
 */
public class ObjectPatchTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ObjectPatchTests.class);
    private ObjectService objectService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpObjectPatchTests() {
        logger.info("Setting up ObjectPatchTests - initializing ObjectService");
        objectService = new ObjectService();
        logger.info("ObjectService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify partially updating object name only")
    public void testPatchObjectNameOnly() {
        logger.info("Starting test: testPatchObjectNameOnly");
        String objectId = "7";
        logger.info("Preparing partial update data for object ID: {} - updating name only", objectId);
        
        ApiObject patchObject = ApiObject.builder()
                .name("Apple MacBook Pro 16 (Updated Name)")
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Partially updating object ID: {} with new name", objectId);
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating partial update response");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(objectId))
                .body("name", equalTo("Apple MacBook Pro 16 (Updated Name)"))
                .body("data.year", equalTo(2019))
                .body("data.price", equalTo(1849.99f))
                .body("updatedAt", notNullValue());
        
        logger.info("Test testPatchObjectNameOnly completed successfully");
    }

    @Test(priority = 2, description = "Verify partial update response contains updatedAt field")
    public void testPatchObjectResponseStructure() {
        logger.info("Starting test: testPatchObjectResponseStructure");
        String objectId = "7";
        logger.info("Preparing partial update data and validating response structure");
        
        ApiObject patchObject = ApiObject.builder()
                .name("MacBook Pro Updated")
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Performing partial update");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response structure includes updatedAt field");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("$", hasKey("name"))
                .body("$", hasKey("data"))
                .body("$", hasKey("updatedAt"))
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("data", notNullValue())
                .body("updatedAt", matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"));
        
        logger.info("Test testPatchObjectResponseStructure completed successfully");
    }

    @Test(priority = 3, description = "Verify partial update of data field only")
    public void testPatchObjectDataOnly() {
        logger.info("Starting test: testPatchObjectDataOnly");
        String objectId = "7";
        logger.info("Preparing partial update data - updating data field only");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("year", 2024);
        updatedData.put("price", 2199.99);

        ApiObject patchObject = ApiObject.builder()
                .data(updatedData)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Partially updating data field");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating data field was partially updated");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(objectId))
                .body("data.year", equalTo(2024))
                .body("data.price", equalTo(2199.99f))
                .body("updatedAt", notNullValue());
        
        logger.info("Test testPatchObjectDataOnly completed successfully");
    }

    @Test(priority = 4, description = "Verify partial update with both name and data")
    public void testPatchObjectNameAndData() {
        logger.info("Starting test: testPatchObjectNameAndData");
        String objectId = "7";
        logger.info("Preparing partial update with both name and data fields");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("price", 1999.99);
        updatedData.put("color", "Space Black");

        ApiObject patchObject = ApiObject.builder()
                .name("Apple MacBook Pro 16 Premium")
                .data(updatedData)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Partially updating both name and data fields");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating both name and data were updated");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Apple MacBook Pro 16 Premium", "Name should be updated");
        softAssert.assertEquals(response.jsonPath().getFloat("data.price"), 1999.99f, "Price should be updated");
        softAssert.assertEquals(response.jsonPath().getString("data.color"), "Space Black", "Color should be added");
        softAssert.assertNotNull(response.jsonPath().getString("updatedAt"), "updatedAt should be present");
        
        response.then().log().status();
        softAssert.assertAll();
        logger.info("Test testPatchObjectNameAndData completed successfully");
    }

    @Test(priority = 5, description = "Verify partial update with additional fields in data")
    public void testPatchObjectAddNewFields() {
        logger.info("Starting test: testPatchObjectAddNewFields");
        String objectId = "7";
        logger.info("Preparing partial update to add new fields to data");
        
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("warranty", "3 years");
        updatedData.put("condition", "Refurbished");
        updatedData.put("color", "Silver");

        ApiObject patchObject = ApiObject.builder()
                .data(updatedData)
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Adding new fields to data");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating new fields were added");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("data.warranty", equalTo("3 years"))
                .body("data.condition", equalTo("Refurbished"))
                .body("data.color", equalTo("Silver"));
        
        logger.info("Test testPatchObjectAddNewFields completed successfully");
    }

    @Test(priority = 6, description = "Verify original data fields are preserved after partial update")
    public void testPatchObjectPreservesOriginalData() {
        logger.info("Starting test: testPatchObjectPreservesOriginalData");
        String objectId = "7";
        logger.info("Verifying original fields are preserved when doing partial update");
        
        // Get original object first
        logger.info("Fetching original object");
        ApiObject originalObject = objectService.getObjectByIdAsObject(objectId);
        logger.info("Original object fetched: {}", originalObject.getName());
        
        // Now perform partial update
        ApiObject patchObject = ApiObject.builder()
                .name("Updated Name Only")
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Performing partial update with name only");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating original data fields are preserved");
        response.then().log().status()
                .statusCode(200)
                .body("data.year", equalTo(originalObject.getData().get("year")))
                .body("data.price", equalTo(originalObject.getData().get("price")))
                .body("data.'CPU model'", equalTo(originalObject.getData().get("CPU model")))
                .body("data.'Hard disk size'", equalTo(originalObject.getData().get("Hard disk size")));
        
        logger.info("Test testPatchObjectPreservesOriginalData completed successfully");
    }

    @Test(priority = 7, description = "Verify content type and response time for PATCH request")
    public void testPatchObjectContentTypeAndTime() {
        logger.info("Starting test: testPatchObjectContentTypeAndTime");
        String objectId = "7";
        logger.info("Testing PATCH request content type and response time");
        
        ApiObject patchObject = ApiObject.builder()
                .name("Patch Performance Test")
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Performing partial update and measuring response time");
        Response response = objectService.patchObject(objectId, patchObject);
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        String contentType = response.getContentType();
        logger.info("Response content type: {}", contentType);
        
        logger.info("Validating content type and response time");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertTrue(responseTime < 3000, "Response time should be less than 3000 ms");
        softAssert.assertTrue(responseTime > 0, "Response time should be greater than 0");
        softAssert.assertNotNull(contentType, "Content-Type should not be null");
        softAssert.assertTrue(contentType.contains("application/json"), "Content-Type should be application/json");
        
        response.then().log().status()
                .statusCode(200)
                .contentType("application/json")
                .time(lessThan(3000L));
        
        softAssert.assertAll();
        logger.info("Test testPatchObjectContentTypeAndTime completed - response time: {} ms", responseTime);
    }

    @Test(priority = 8, description = "Verify patching with empty data object")
    public void testPatchObjectWithEmptyData() {
        logger.info("Starting test: testPatchObjectWithEmptyData");
        String objectId = "7";
        logger.info("Preparing partial update with empty data object");
        
        ApiObject patchObject = ApiObject.builder()
                .name("Updated with Empty Data")
                .data(new HashMap<>())
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        logger.info("Performing partial update with empty data");
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating partial update with empty data");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertEquals(response.jsonPath().getString("name"), "Updated with Empty Data", "Name should be updated");
        softAssert.assertNotNull(response.jsonPath().getString("updatedAt"), "updatedAt should be present");
        
        response.then().log().status();
        softAssert.assertAll();
        logger.info("Test testPatchObjectWithEmptyData completed successfully");
    }

    @Test(priority = 9, description = "Verify patching multiple objects sequentially")
    public void testPatchMultipleObjectsSequentially() {
        logger.info("Starting test: testPatchMultipleObjectsSequentially");
        logger.info("Performing sequential partial updates on multiple objects");
        
        // First object
        logger.info("Patching first object - ID: 5");
        ApiObject patch1 = ApiObject.builder()
                .name("Samsung Galaxy Z Fold2 Updated")
                .build();
        logger.info("Request JSON body for first patch: \n{}", JsonUtils.serialize(patch1));
        Response response1 = objectService.patchObject("5", patch1);
        logger.info("First patch status: {}", response1.getStatusCode());
        
        // Second object
        logger.info("Patching second object - ID: 10");
        ApiObject patch2 = ApiObject.builder()
                .name("Apple iPad Mini Updated")
                .build();
        logger.info("Request JSON body for second patch: \n{}", JsonUtils.serialize(patch2));
        Response response2 = objectService.patchObject("10", patch2);
        logger.info("Second patch status: {}", response2.getStatusCode());
        
        logger.info("Validating both patches were successful");
        softAssert.assertEquals(response1.getStatusCode(), 200, "First patch should succeed");
        softAssert.assertEquals(response2.getStatusCode(), 200, "Second patch should succeed");
        softAssert.assertEquals(response1.jsonPath().getString("name"), "Samsung Galaxy Z Fold2 Updated", "First name should be updated");
        softAssert.assertEquals(response2.jsonPath().getString("name"), "Apple iPad Mini Updated", "Second name should be updated");
        
        response1.then().log().status();
        response2.then().log().status();
        softAssert.assertAll();
        logger.info("Test testPatchMultipleObjectsSequentially completed successfully");
    }

    @Test(priority = 10, description = "Verify PATCH method is idempotent")
    public void testPatchObjectIdempotency() {
        logger.info("Starting test: testPatchObjectIdempotency");
        String objectId = "7";
        logger.info("Testing PATCH idempotency - applying same patch twice");
        
        ApiObject patchObject = ApiObject.builder()
                .name("Idempotency Test Name")
                .build();
        
        logger.info("First PATCH request");
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        Response response1 = objectService.patchObject(objectId, patchObject);
        logger.info("First PATCH status: {}", response1.getStatusCode());
        String firstUpdatedAt = response1.jsonPath().getString("updatedAt");
        logger.info("First updatedAt: {}", firstUpdatedAt);
        
        // Wait a moment before second patch
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted");
        }
        
        logger.info("Second PATCH request with same data");
        Response response2 = objectService.patchObject(objectId, patchObject);
        logger.info("Second PATCH status: {}", response2.getStatusCode());
        String secondUpdatedAt = response2.jsonPath().getString("updatedAt");
        logger.info("Second updatedAt: {}", secondUpdatedAt);
        
        logger.info("Validating idempotency");
        softAssert.assertEquals(response1.getStatusCode(), 200, "First PATCH should succeed");
        softAssert.assertEquals(response2.getStatusCode(), 200, "Second PATCH should succeed");
        softAssert.assertEquals(response1.jsonPath().getString("name"), "Idempotency Test Name", "Name should be same");
        softAssert.assertEquals(response2.jsonPath().getString("name"), "Idempotency Test Name", "Name should be same");
        // updatedAt will be different since object was updated at different times
        softAssert.assertNotEquals(firstUpdatedAt, secondUpdatedAt, "updatedAt should be different");
        
        response1.then().log().status();
        response2.then().log().status();
        softAssert.assertAll();
        logger.info("Test testPatchObjectIdempotency completed successfully");
    }

    @Test(priority = 11, description = "Verify PATCH returns updated object as ApiObject")
    public void testPatchObjectAsApiObject() {
        logger.info("Starting test: testPatchObjectAsApiObject");
        String objectId = "7";
        logger.info("Performing PATCH and deserializing to ApiObject");
        
        ApiObject patchObject = ApiObject.builder()
                .name("POJO Test Name")
                .build();
        
        logger.info("Request JSON body: \n{}", JsonUtils.serialize(patchObject));
        Response response = objectService.patchObject(objectId, patchObject);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        ApiObject updatedObject = response.as(ApiObject.class);
        logger.info("Deserialize response to ApiObject: {}", updatedObject.getName());
        
        logger.info("Validating deserialized object");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        softAssert.assertNotNull(updatedObject, "ApiObject should not be null");
        softAssert.assertEquals(updatedObject.getId(), objectId, "ID should match");
        softAssert.assertEquals(updatedObject.getName(), "POJO Test Name", "Name should match");
        softAssert.assertNotNull(updatedObject.getData(), "Data should not be null");
        
        softAssert.assertAll();
        logger.info("Test testPatchObjectAsApiObject completed successfully");
    }
}
