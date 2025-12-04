package com.api.automation.services;

import com.api.automation.models.ApiObject;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Service class for Object API endpoints (Page Object Model)
 * Handles all object-related API operations for restful-api.dev
 */
public class ObjectService {
    private static final String BASE_URI = "https://api.restful-api.dev";
    private static final String OBJECTS_PATH = "/objects";

    /**
     * Get all objects
     * @return Response object
     */
    public Response getAllObjects() {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .when()
                .get();
    }

    /**
     * Get object by ID
     * @param objectId Object ID
     * @return Response object
     */
    public Response getObjectById(String objectId) {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .when()
                .get("/" + objectId);
    }

    /**
     * Create a new object
     * @param apiObject ApiObject object
     * @return Response object
     */
    public Response createObject(ApiObject apiObject) {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .contentType("application/json")
                .body(apiObject)
                .when()
                .post();
    }

    /**
     * Update object
     * @param objectId Object ID
     * @param apiObject ApiObject object
     * @return Response object
     */
    public Response updateObject(String objectId, ApiObject apiObject) {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .contentType("application/json")
                .body(apiObject)
                .when()
                .put("/" + objectId);
    }

    /**
     * Partially update object
     * @param objectId Object ID
     * @param apiObject ApiObject object
     * @return Response object
     */
    public Response patchObject(String objectId, ApiObject apiObject) {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .contentType("application/json")
                .body(apiObject)
                .when()
                .patch("/" + objectId);
    }

    /**
     * Delete object
     * @param objectId Object ID
     * @return Response object
     */
    public Response deleteObject(String objectId) {
        return given()
                .log().method().log().uri()
                .baseUri(BASE_URI)
                .basePath(OBJECTS_PATH)
                .when()
                .delete("/" + objectId);
    }

    /**
     * Get object by ID and deserialize to ApiObject object
     * @param objectId Object ID
     * @return ApiObject object
     */
    public ApiObject getObjectByIdAsObject(String objectId) {
        return getObjectById(objectId).as(ApiObject.class);
    }

    /**
     * Get all objects and deserialize to ApiObject array
     * @return ApiObject array
     */
    public ApiObject[] getAllObjectsAsArray() {
        return getAllObjects().as(ApiObject[].class);
    }
}
