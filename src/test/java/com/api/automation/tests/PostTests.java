package com.api.automation.tests;

import com.api.automation.models.Post;
import com.api.automation.services.PostService;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.*;

/**
 * Test class for Post API endpoints
 * Demonstrates Page Object Model pattern with REST Assured
 */
public class PostTests extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(PostTests.class);
    private PostService postService;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUpPostTests() {
        logger.info("Setting up PostTests - initializing PostService");
        postService = new PostService();
        logger.info("PostService initialized successfully");
    }

    @BeforeMethod
    public void setUpSoftAssert() {
        softAssert = new SoftAssert();
    }

    @Test(priority = 1, description = "Verify getting all posts")
    public void testGetAllPosts() {
        logger.info("Starting test: testGetAllPosts");
        logger.info("Fetching all posts from API");
        
        Response response = postService.getAllPosts();
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating response contains multiple posts");
        response.then().log().status()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("id", everyItem(notNullValue()))
                .body("title", everyItem(notNullValue()));
        
        logger.info("Test testGetAllPosts completed successfully");
    }

    @Test(priority = 2, description = "Verify getting a specific post by ID")
    public void testGetPostById() {
        logger.info("Starting test: testGetPostById");
        int postId = 1;
        logger.info("Fetching post with ID: {}", postId);
        
        Response response = postService.getPostById(postId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating post data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("userId", notNullValue());
        
        logger.info("Test testGetPostById completed successfully");
    }

    @Test(priority = 3, description = "Verify getting post as object")
    public void testGetPostAsObject() {
        logger.info("Starting test: testGetPostAsObject");
        int postId = 1;
        logger.info("Fetching post with ID: {} and deserializing to Post object", postId);
        
        Post post = postService.getPostByIdAsObject(postId);
        logger.info("Post object received: {}", post.getTitle());
        
        logger.info("Validating Post object fields");
        softAssert.assertNotNull(post, "Post should not be null");
        softAssert.assertEquals(post.getId(), postId, "Post ID should match");
        softAssert.assertFalse(post.getTitle().isEmpty(), "Post title should not be empty");
        softAssert.assertTrue(post.getUserId() > 0, "User ID should be positive");
        
        softAssert.assertAll();
        logger.info("Test testGetPostAsObject completed successfully");
    }

    @Test(priority = 4, description = "Verify getting posts by user ID")
    public void testGetPostsByUserId() {
        logger.info("Starting test: testGetPostsByUserId");
        int userId = 1;
        logger.info("Fetching posts for user ID: {}", userId);
        
        Response response = postService.getPostsByUserId(userId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating posts belong to user ID: {}", userId);
        response.then().log().status()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(equalTo(userId)));
        
        logger.info("Test testGetPostsByUserId completed successfully");
    }

    @Test(priority = 5, description = "Verify creating a new post")
    public void testCreatePost() {
        logger.info("Starting test: testCreatePost");
        logger.info("Preparing test data for new post creation");
        
        Post newPost = Post.builder()
                .userId(1)
                .title("Test Post Title")
                .body("This is a test post body created for automation testing.")
                .build();

        logger.info("Creating new post: {}", newPost.getTitle());
        Response response = postService.createPost(newPost);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating created post data");
        response.then().log().status().log().body()
                .statusCode(201)
                .body("title", equalTo(newPost.getTitle()))
                .body("body", equalTo(newPost.getBody()))
                .body("userId", equalTo(newPost.getUserId()));
        
        logger.info("Test testCreatePost completed successfully - post created");
    }

    @Test(priority = 6, description = "Verify updating a post")
    public void testUpdatePost() {
        logger.info("Starting test: testUpdatePost");
        int postId = 1;
        logger.info("Preparing updated data for post ID: {}", postId);
        
        Post updatedPost = Post.builder()
                .id(postId)
                .userId(1)
                .title("Updated Post Title")
                .body("This is an updated post body.")
                .build();

        logger.info("Updating post ID: {} with new data", postId);
        Response response = postService.updatePost(postId, updatedPost);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating updated post data");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("title", equalTo(updatedPost.getTitle()));
        
        logger.info("Test testUpdatePost completed successfully - post updated");
    }

    @Test(priority = 7, description = "Verify partially updating a post")
    public void testPatchPost() {
        logger.info("Starting test: testPatchPost");
        int postId = 1;
        logger.info("Preparing partial update for post ID: {}", postId);
        
        Post partialPost = Post.builder()
                .title("Partially Updated Title")
                .build();

        logger.info("Partially updating post ID: {}", postId);
        Response response = postService.patchPost(postId, partialPost);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating partial update");
        response.then().log().status().log().body()
                .statusCode(200)
                .body("id", equalTo(postId));
        
        logger.info("Test testPatchPost completed successfully - post partially updated");
    }

    @Test(priority = 8, description = "Verify deleting a post")
    public void testDeletePost() {
        logger.info("Starting test: testDeletePost");
        int postId = 1;
        logger.info("Deleting post with ID: {}", postId);
        
        Response response = postService.deletePost(postId);
        logger.info("Response received with status code: {}", response.getStatusCode());
        
        logger.info("Validating delete operation");
        response.then().log().status()
                .statusCode(200);
        
        logger.info("Test testDeletePost completed successfully - post deleted");
    }

    @Test(priority = 9, description = "Verify getting all posts as array")
    public void testGetAllPostsAsArray() {
        logger.info("Starting test: testGetAllPostsAsArray");
        logger.info("Fetching all posts and deserializing to Post array");
        
        Post[] posts = postService.getAllPostsAsArray();
        logger.info("Post array received with {} posts", posts.length);
        
        logger.info("Validating Post array");
        softAssert.assertTrue(posts.length > 0, "Posts array should not be empty");
        softAssert.assertNotNull(posts[0].getId(), "First post ID should not be null");
        softAssert.assertNotNull(posts[0].getUserId(), "First post user ID should not be null");
        softAssert.assertNotNull(posts[0].getTitle(), "First post title should not be null");
        
        softAssert.assertAll();
        logger.info("Test testGetAllPostsAsArray completed successfully");
    }
}
