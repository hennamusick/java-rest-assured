package com.api.automation.services;

import io.restassured.response.Response;
import com.api.automation.utils.RestClient;

import static io.restassured.RestAssured.given;

/**
 * Base service class for common API operations
 */
public abstract class BaseService {
    protected String basePath;

    protected BaseService(String basePath) {
        this.basePath = basePath;
    }

    protected Response get(String endpoint) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .when()
                .get(endpoint);
    }

    protected Response get() {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .when()
                .get();
    }

    protected Response post(Object body, String endpoint) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .body(body)
                .when()
                .post(endpoint);
    }

    protected Response post(Object body) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .body(body)
                .when()
                .post();
    }

    protected Response put(Object body, String endpoint) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .body(body)
                .when()
                .put(endpoint);
    }

    protected Response patch(Object body, String endpoint) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .body(body)
                .when()
                .patch(endpoint);
    }

    protected Response delete(String endpoint) {
        return given()
                .spec(RestClient.getRequestSpec())
                .basePath(basePath)
                .when()
                .delete(endpoint);
    }
}
