package com.api.automation.tests.utils;

import com.api.automation.utils.RestClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base test class for all test classes
 */
public class BaseTest {

    @BeforeClass
    public void setUp() {
        // Initialize REST Assured specifications
        RestClient.getRequestSpec();
        RestClient.getResponseSpec();
    }

    @AfterClass
    public void tearDown() {
        // Reset REST Assured specifications
        RestClient.resetSpecs();
    }
}
