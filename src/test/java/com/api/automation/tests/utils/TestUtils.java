package com.api.automation.tests.utils;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for common test helper methods
 */
public class TestUtils {

    /**
     * Print response details for debugging
     * @param response Response object
     * @param testName Name of the test
     */
    public static void printResponseDetails(Response response, String testName) {
        System.out.println("\n========== " + testName + " ==========");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Content Type: " + response.getContentType());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Response Body:\n" + response.getBody().prettyPrint());
        System.out.println("=======================================\n");
    }

    /**
     * Create a sample data map for object creation
     * @param year Year
     * @param price Price
     * @param cpuModel CPU Model
     * @param diskSize Hard disk size
     * @return Map with data
     */
    public static Map<String, Object> createSampleDataMap(int year, double price, 
                                                           String cpuModel, String diskSize) {
        Map<String, Object> data = new HashMap<>();
        data.put("year", year);
        data.put("price", price);
        data.put("CPU model", cpuModel);
        data.put("Hard disk size", diskSize);
        return data;
    }

    /**
     * Verify if response status code is success (2xx)
     * @param statusCode Status code
     * @return true if success, false otherwise
     */
    public static boolean isSuccessStatusCode(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    /**
     * Verify if response status code is client error (4xx)
     * @param statusCode Status code
     * @return true if client error, false otherwise
     */
    public static boolean isClientErrorStatusCode(int statusCode) {
        return statusCode >= 400 && statusCode < 500;
    }

    /**
     * Verify if response status code is server error (5xx)
     * @param statusCode Status code
     * @return true if server error, false otherwise
     */
    public static boolean isServerErrorStatusCode(int statusCode) {
        return statusCode >= 500 && statusCode < 600;
    }

    /**
     * Get response header value
     * @param response Response object
     * @param headerName Header name
     * @return Header value
     */
    public static String getResponseHeader(Response response, String headerName) {
        return response.getHeader(headerName);
    }

    /**
     * Check if response contains a specific header
     * @param response Response object
     * @param headerName Header name
     * @return true if header exists, false otherwise
     */
    public static boolean hasResponseHeader(Response response, String headerName) {
        return response.getHeaders().hasHeaderWithName(headerName);
    }

    /**
     * Extract value from response body using JSONPath
     * @param response Response object
     * @param jsonPath JSONPath expression
     * @return Value at the specified path
     */
    public static Object extractFromResponse(Response response, String jsonPath) {
        return response.jsonPath().get(jsonPath);
    }

    /**
     * Compare two response bodies
     * @param response1 First response
     * @param response2 Second response
     * @return true if bodies are equal, false otherwise
     */
    public static boolean compareResponseBodies(Response response1, Response response2) {
        return response1.getBody().asString().equals(response2.getBody().asString());
    }

    /**
     * Retry a response request with exponential backoff
     * @param maxRetries Maximum number of retries
     * @param delayInMs Initial delay in milliseconds
     * @param responseFetcher Functional interface to fetch response
     * @return Response object
     * @throws Exception if all retries fail
     */
    public static Response retryWithExponentialBackoff(int maxRetries, long delayInMs,
                                                        ResponseFetcher responseFetcher) throws Exception {
        Exception lastException = null;
        long currentDelay = delayInMs;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                Response response = responseFetcher.fetch();
                if (isSuccessStatusCode(response.getStatusCode())) {
                    return response;
                }
            } catch (Exception e) {
                lastException = e;
            }

            if (attempt < maxRetries) {
                Thread.sleep(currentDelay);
                currentDelay *= 2; // Double the delay for exponential backoff
            }
        }

        throw new Exception("All retries failed", lastException);
    }

    /**
     * Functional interface for fetching response
     */
    @FunctionalInterface
    public interface ResponseFetcher {
        Response fetch();
    }
}
