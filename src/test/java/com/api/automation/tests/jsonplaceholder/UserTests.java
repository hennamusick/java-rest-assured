package com.api.automation.tests.jsonplaceholder;

import com.api.automation.models.User;
import com.api.automation.services.UserService;
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
 * Test class for User API endpoints
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class UserTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTests.class);
    private UserService userService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpUserTests() {
        logger.info("Setting up UserTests - initializing UserService");
        userService = new UserService();
        logger.info("UserService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify getting all users")
    public void testGetAllUsers() {
        logger.info("Starting test: testGetAllUsers");
        logger.info("Fetching all users from API");
        
        Response response = userService.getAllUsers();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains multiple users");
        response.then().log().status()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()));
        
        logger.info("Test testGetAllUsers completed successfully");
    }

    @Test(priority = 2, description = "Verify getting a specific user by ID")
    public void testGetUserById() {
        logger.info("Starting test: testGetUserById");
        int userId = 1;
        logger.info("Fetching user with ID: {}", userId);
        
        Response response = userService.getUserById(userId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating user data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", notNullValue())
                .body("email", notNullValue());
        
        logger.info("Test testGetUserById completed successfully");
    }

    @Test(priority = 3, description = "Verify getting user as object")
    public void testGetUserAsObject() {
        logger.info("Starting test: testGetUserAsObject");
        int userId = 1;
        logger.info("Fetching user with ID: {} and deserializing to User object", userId);
        
        User user = userService.getUserByIdAsObject(userId);
        logger.info("User object received: {}", user.getName());
        
        logger.info("Validating User object fields");
        softAssert.assertNotNull(user, "User should not be null");
        softAssert.assertEquals((int)user.getId(), (int)userId, "User ID should match");
        softAssert.assertFalse(user.getName().isEmpty(), "User name should not be empty");
        softAssert.assertTrue(user.getEmail().contains("@"), "Email should contain @");
        
        softAssert.assertAll();
        logger.info("Test testGetUserAsObject completed successfully");
    }

    @Test(priority = 4, description = "Verify creating a new user")
    public void testCreateUser() {
        logger.info("Starting test: testCreateUser");
        logger.info("Preparing test data for new user creation");
        
        User newUser = User.builder()
                .name("John Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .phone("1-555-123-4567")
                .website("johndoe.com")
                .build();

        logger.info("Creating new user: {}", newUser.getName());
        Response response = userService.createUser(newUser);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating created user data");
        response.then().log().status().log().body()
                .statusCode(201)
                .body("name", equalTo(newUser.getName()))
                .body("email", equalTo(newUser.getEmail()));
        
        logger.info("Test testCreateUser completed successfully - user created");
    }

    @Test(priority = 5, description = "Verify updating a user")
    public void testUpdateUser() {
        logger.info("Starting test: testUpdateUser");
        int userId = 1;
        logger.info("Preparing updated data for user ID: {}", userId);
        
        User updatedUser = User.builder()
                .id(userId)
                .name("Jane Doe Updated")
                .username("janedoe")
                .email("jane.updated@example.com")
                .build();

        logger.info("Updating user ID: {} with new data", userId);
        Response response = userService.updateUser(userId, updatedUser);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating updated user data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo(updatedUser.getName()));
        
        logger.info("Test testUpdateUser completed successfully - user updated");
    }

    @Test(priority = 6, description = "Verify partially updating a user")
    public void testPatchUser() {
        logger.info("Starting test: testPatchUser");
        int userId = 1;
        logger.info("Preparing partial update for user ID: {}", userId);
        
        User partialUser = User.builder()
                .name("Partially Updated Name")
                .build();

        logger.info("Partially updating user ID: {}", userId);
        Response response = userService.patchUser(userId, partialUser);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating partial update");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(userId));
        
        logger.info("Test testPatchUser completed successfully - user partially updated");
    }

    @Test(priority = 7, description = "Verify deleting a user")
    public void testDeleteUser() {
        logger.info("Starting test: testDeleteUser");
        int userId = 1;
        logger.info("Deleting user with ID: {}", userId);
        
        Response response = userService.deleteUser(userId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating delete operation");
        response.then().log().status()
                .statusCode(200);
        
        logger.info("Test testDeleteUser completed successfully - user deleted");
    }

    @Test(priority = 8, description = "Verify getting all users as array")
    public void testGetAllUsersAsArray() {
        logger.info("Starting test: testGetAllUsersAsArray");
        logger.info("Fetching all users and deserializing to User array");
        
        User[] users = userService.getAllUsersAsArray();
        logger.info("User array received with {} users", users.length);
        
        logger.info("Validating User array");
        softAssert.assertTrue(users.length > 0, "Users array should not be empty");
        softAssert.assertNotNull(users[0].getId(), "First user ID should not be null");
        softAssert.assertNotNull(users[0].getName(), "First user name should not be null");
        
        softAssert.assertAll();
        logger.info("Test testGetAllUsersAsArray completed successfully");
    }
}
