package com.api.automation.tests;

import com.api.automation.models.Post;
import com.api.automation.services.PostService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test class for Post API endpoints
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class PostTests extends BaseTest {
    private PostService postService;

    @BeforeClass
    public void setUpPostTests() {
        postService = new PostService();
    }

    @Test(priority = 1, description = "Verify getting all posts")
    public void testGetAllPosts() {
        Response response = postService.getAllPosts();
        
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()));
    }

    @Test(priority = 2, description = "Verify getting a specific post by ID")
    public void testGetPostById() {
        int postId = 1;
        Response response = postService.getPostById(postId);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("userId", notNullValue());
    }

    @Test(priority = 3, description = "Verify getting post as object")
    public void testGetPostAsObject() {
        int postId = 1;
        Post post = postService.getPostByIdAsObject(postId);
        
        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(postId);
        assertThat(post.getTitle()).isNotEmpty();
        assertThat(post.getUserId()).isPositive();
    }

    @Test(priority = 4, description = "Verify getting posts by user ID")
    public void testGetPostsByUserId() {
        int userId = 1;
        Response response = postService.getPostsByUserId(userId);
        
        response.then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(userId)));
    }

    @Test(priority = 5, description = "Verify creating a new post")
    public void testCreatePost() {
        Post newPost = Post.builder()
                .userId(1)
                .title("Test Post Title")
                .body("This is a test post body created for automation testing.")
                .build();

        Response response = postService.createPost(newPost);
        
        response.then()
                .statusCode(201)
                .body("title", equalTo(newPost.getTitle()))
                .body("body", equalTo(newPost.getBody()))
                .body("userId", equalTo(newPost.getUserId()));
    }

    @Test(priority = 6, description = "Verify updating a post")
    public void testUpdatePost() {
        int postId = 1;
        Post updatedPost = Post.builder()
                .id(postId)
                .userId(1)
                .title("Updated Post Title")
                .body("This is an updated post body.")
                .build();

        Response response = postService.updatePost(postId, updatedPost);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", equalTo(updatedPost.getTitle()));
    }

    @Test(priority = 7, description = "Verify partially updating a post")
    public void testPatchPost() {
        int postId = 1;
        Post partialPost = Post.builder()
                .title("Partially Updated Title")
                .build();

        Response response = postService.patchPost(postId, partialPost);
        
        response.then()
                .statusCode(200)
                .body("id", equalTo(postId));
    }

    @Test(priority = 8, description = "Verify deleting a post")
    public void testDeletePost() {
        int postId = 1;
        Response response = postService.deletePost(postId);
        
        response.then()
                .statusCode(200);
    }

    @Test(priority = 9, description = "Verify getting all posts as array")
    public void testGetAllPostsAsArray() {
        Post[] posts = postService.getAllPostsAsArray();
        
        assertThat(posts).isNotEmpty();
        assertThat(posts.length).isGreaterThan(0);
        assertThat(posts[0].getId()).isNotNull();
        assertThat(posts[0].getUserId()).isNotNull();
    }
}
