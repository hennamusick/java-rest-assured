package com.api.automation.tests.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.testng.asserts.SoftAssert;

/**
 * Utility class for common assertion patterns in API tests
 * Reduces code duplication by centralizing repetitive assertion logic
 */
public class AssertionHelper {
    private final SoftAssert softAssert;
    private final Logger logger;

    public AssertionHelper(SoftAssert softAssert, Logger logger) {
        this.softAssert = softAssert;
        this.logger = logger;
    }

    /**
     * Log test start message
     */
    public void logTestStart(String testMethodName) {
        logger.info("Starting test: {}", testMethodName);
    }

    /**
     * Log test completion message
     */
    public void logTestCompletion(String testMethodName) {
        logger.info("Test {} completed successfully", testMethodName);
    }

    /**
     * Log test completion with additional details
     */
    public void logTestCompletion(String testMethodName, String details) {
        logger.info("Test {} completed successfully - {}", testMethodName, details);
    }

    /**
     * Assert response status code and log result
     */
    public void assertStatusCode(Response response, int expectedStatusCode, String context) {
        logger.info("Response received with status code: {}", response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), expectedStatusCode, 
                               "Status code should be " + expectedStatusCode + " - " + context);
    }

    /**
     * Assert response is not null
     */
    public void assertResponseNotNull(Response response) {
        softAssert.assertNotNull(response, "Response should not be null");
    }

    /**
     * Assert response status code with descriptive message
     */
    public void assertStatusCodeAndLogging(Response response, int expectedStatusCode) {
        logger.info("Response received with status code: {}", response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), expectedStatusCode, 
                               "Status code should be " + expectedStatusCode);
        softAssert.assertNotNull(response, "Response should not be null");
    }

    /**
     * Assert response time is within acceptable range
     */
    public void assertResponseTime(Response response, long maxTimeMs, String context) {
        long responseTime = response.getTime();
        logger.info("Response received in {} ms", responseTime);
        softAssert.assertTrue(responseTime < maxTimeMs, 
                             "Response time should be less than " + maxTimeMs + " ms - " + context);
        softAssert.assertTrue(responseTime > 0, "Response time should be greater than 0 ms");
    }

    /**
     * Assert response content type
     */
    public void assertContentType(Response response, String expectedContentType) {
        String contentType = response.getContentType();
        logger.info("Content type verified as: {}", contentType);
        softAssert.assertNotNull(contentType, "Content-Type should not be null");
        softAssert.assertTrue(contentType.contains(expectedContentType), 
                             "Content-Type should be " + expectedContentType);
    }

    /**
     * Assert JSON field is not null
     */
    public void assertFieldNotNull(Response response, String fieldPath, String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldPath);
        softAssert.assertNotNull(fieldValue, fieldName + " should not be null");
    }

    /**
     * Assert JSON field equals expected value
     */
    public void assertFieldEquals(Response response, String fieldPath, Object expectedValue, String fieldName) {
        Object actualValue = response.jsonPath().get(fieldPath);
        softAssert.assertEquals(actualValue, expectedValue, fieldName + " should equal " + expectedValue);
    }

    /**
     * Assert string field contains substring
     */
    public void assertFieldContains(Response response, String fieldPath, String substring, String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldPath);
        softAssert.assertTrue(fieldValue.contains(substring), 
                             fieldName + " should contain '" + substring + "'");
    }

    /**
     * Assert string field is not empty
     */
    public void assertFieldNotEmpty(Response response, String fieldPath, String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldPath);
        softAssert.assertNotNull(fieldValue, fieldName + " should not be null");
        softAssert.assertFalse(fieldValue.isEmpty(), fieldName + " should not be empty");
    }

    /**
     * Assert response contains key
     */
    public void assertResponseHasKey(Response response, String key) {
        boolean hasKey = response.jsonPath().getMap("$").containsKey(key);
        softAssert.assertTrue(hasKey, "Response should contain key: " + key);
    }

    /**
     * Assert response does not contain key
     */
    public void assertResponseDoesNotHaveKey(Response response, String key) {
        boolean hasKey = response.jsonPath().getMap("$").containsKey(key);
        softAssert.assertFalse(hasKey, "Response should not contain key: " + key);
    }

    /**
     * Assert and finalize all soft assertions
     */
    public void assertAll() {
        softAssert.assertAll();
    }

    /**
     * Log request details
     */
    public void logRequest(String method, String objectId) {
        logger.info("Sending {} request for object ID: {}", method, objectId);
    }

    /**
     * Log request details with custom message
     */
    public void logRequest(String message, String... params) {
        logger.info(message, (Object[]) params);
    }

    /**
     * Log validation step
     */
    public void logValidation(String validationMessage) {
        logger.info("Validating {}", validationMessage);
    }

    /**
     * Log extraction step
     */
    public void logExtraction(String extractionMessage) {
        logger.info("Extracting: {}", extractionMessage);
    }

    /**
     * Combine multiple assertions for response status and structure
     */
    public void assertResponseStatusAndNotNull(Response response, int expectedStatusCode) {
        assertStatusCodeAndLogging(response, expectedStatusCode);
    }

    /**
     * Log completion with response summary
     */
    public void logResponseSummary(String testName, Response response) {
        logger.info("Test {} - Response Status: {}, Time: {}ms", 
                   testName, response.getStatusCode(), response.getTime());
    }
}
