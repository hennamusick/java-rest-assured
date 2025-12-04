package com.api.automation.services;

import com.api.automation.models.User;
import io.restassured.response.Response;

/**
 * Service class for User API endpoints (Page Object Model)
 * Handles all user-related API operations
 */
public class UserService extends BaseService {
    private static final String USERS_PATH = "/users";

    public UserService() {
        super(USERS_PATH);
    }

    /**
     * Get all users
     * @return Response object
     */
    public Response getAllUsers() {
        return get();
    }

    /**
     * Get user by ID
     * @param userId User ID
     * @return Response object
     */
    public Response getUserById(int userId) {
        return get("/" + userId);
    }

    /**
     * Create a new user
     * @param user User object
     * @return Response object
     */
    public Response createUser(User user) {
        return post(user);
    }

    /**
     * Update user
     * @param userId User ID
     * @param user User object
     * @return Response object
     */
    public Response updateUser(int userId, User user) {
        return put(user, "/" + userId);
    }

    /**
     * Partially update user
     * @param userId User ID
     * @param user User object
     * @return Response object
     */
    public Response patchUser(int userId, User user) {
        return patch(user, "/" + userId);
    }

    /**
     * Delete user
     * @param userId User ID
     * @return Response object
     */
    public Response deleteUser(int userId) {
        return delete("/" + userId);
    }

    /**
     * Get user by ID and deserialize to User object
     * @param userId User ID
     * @return User object
     */
    public User getUserByIdAsObject(int userId) {
        return getUserById(userId).as(User.class);
    }

    /**
     * Get all users and deserialize to User array
     * @return User array
     */
    public User[] getAllUsersAsArray() {
        return getAllUsers().as(User[].class);
    }
}
