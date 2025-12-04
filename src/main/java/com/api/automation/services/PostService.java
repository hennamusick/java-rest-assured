package com.api.automation.services;

import com.api.automation.models.Post;
import io.restassured.response.Response;

/**
 * Service class for Post API endpoints (Page Object Model)
 * Handles all post-related API operations
 */
public class PostService extends BaseService {
    private static final String POSTS_PATH = "/posts";

    public PostService() {
        super(POSTS_PATH);
    }

    /**
     * Get all posts
     * @return Response object
     */
    public Response getAllPosts() {
        return get();
    }

    /**
     * Get post by ID
     * @param postId Post ID
     * @return Response object
     */
    public Response getPostById(int postId) {
        return get("/" + postId);
    }

    /**
     * Get posts by user ID
     * @param userId User ID
     * @return Response object
     */
    public Response getPostsByUserId(int userId) {
        return get("?userId=" + userId);
    }

    /**
     * Create a new post
     * @param post Post object
     * @return Response object
     */
    public Response createPost(Post post) {
        return post(post);
    }

    /**
     * Update post
     * @param postId Post ID
     * @param post Post object
     * @return Response object
     */
    public Response updatePost(int postId, Post post) {
        return put(post, "/" + postId);
    }

    /**
     * Partially update post
     * @param postId Post ID
     * @param post Post object
     * @return Response object
     */
    public Response patchPost(int postId, Post post) {
        return patch(post, "/" + postId);
    }

    /**
     * Delete post
     * @param postId Post ID
     * @return Response object
     */
    public Response deletePost(int postId) {
        return delete("/" + postId);
    }

    /**
     * Get post by ID and deserialize to Post object
     * @param postId Post ID
     * @return Post object
     */
    public Post getPostByIdAsObject(int postId) {
        return getPostById(postId).as(Post.class);
    }

    /**
     * Get all posts and deserialize to Post array
     * @return Post array
     */
    public Post[] getAllPostsAsArray() {
        return getAllPosts().as(Post[].class);
    }
}
