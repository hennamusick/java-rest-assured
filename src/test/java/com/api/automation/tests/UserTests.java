package com.api.automation.tests;

import com.api.automation.models.User;
import com.api.automation.services.UserService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test class for User API endpoints
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class UserTests extends BaseTest {
    private UserService userService;

    @BeforeClass
    public void setUpUserTests() {
        userService = new UserService();
    }

    @Test(priority = 1, description = "Verify getting all users")
    public void testGetAllUsers() {
        Response response = userService.getAllUsers();
        
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("id", everyItem(notNullValue()))
                .body("name", everyItem(notNullValue()));
    }

    @Test(priority = 2, description = "Verify getting a specific user by ID")
    public void testGetUserById() {
        int userId = 1;
        Response response = userService.getUserById(userId);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    @Test(priority = 3, description = "Verify getting user as object")
    public void testGetUserAsObject() {
        int userId = 1;
        User user = userService.getUserByIdAsObject(userId);
        
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getName()).isNotEmpty();
        assertThat(user.getEmail()).contains("@");
    }

    @Test(priority = 4, description = "Verify creating a new user")
    public void testCreateUser() {
        User newUser = User.builder()
                .name("John Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .phone("1-555-123-4567")
                .website("johndoe.com")
                .build();

        Response response = userService.createUser(newUser);
        
        response.then()
                .statusCode(201)
                .body("name", equalTo(newUser.getName()))
                .body("email", equalTo(newUser.getEmail()));
    }

    @Test(priority = 5, description = "Verify updating a user")
    public void testUpdateUser() {
        int userId = 1;
        User updatedUser = User.builder()
                .id(userId)
                .name("Jane Doe Updated")
                .username("janedoe")
                .email("jane.updated@example.com")
                .build();

        Response response = userService.updateUser(userId, updatedUser);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", equalTo(updatedUser.getName()));
    }

    @Test(priority = 6, description = "Verify partially updating a user")
    public void testPatchUser() {
        int userId = 1;
        User partialUser = User.builder()
                .name("Partially Updated Name")
                .build();

        Response response = userService.patchUser(userId, partialUser);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    @Test(priority = 7, description = "Verify deleting a user")
    public void testDeleteUser() {
        int userId = 1;
        Response response = userService.deleteUser(userId);
        
        response.then()
                .statusCode(200);
    }

    @Test(priority = 8, description = "Verify getting all users as array")
    public void testGetAllUsersAsArray() {
        User[] users = userService.getAllUsersAsArray();
        
        assertThat(users).isNotEmpty();
        assertThat(users.length).isGreaterThan(0);
        assertThat(users[0].getId()).isNotNull();
    }
}
