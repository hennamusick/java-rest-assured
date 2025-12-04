package com.api.automation.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.api.automation.config.ConfigManager;

/**
 * REST Client utility to configure REST Assured specifications
 */
public class RestClient {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ConfigManager.getInstance().getBaseUri())
                    .setContentType(ContentType.JSON)
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .build();
        }
        return requestSpec;
    }

    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) {
            responseSpec = new ResponseSpecBuilder()
                    .expectContentType(ContentType.JSON)
                    .build();
        }
        return responseSpec;
    }

    public static void resetSpecs() {
        requestSpec = null;
        responseSpec = null;
        RestAssured.reset();
    }
}
