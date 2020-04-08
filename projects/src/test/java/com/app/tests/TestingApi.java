package com.app.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.app.tests.Pojos.Posts;

public class TestingApi {

    @BeforeClass
    public static void setUp(){
    	RestAssured.baseURI="http://qainterview.merchante-solutions.com:3030/";
    }

    
    
  //get method for POSTS end point
    @Test
    public void getPosts(){

        Response response=RestAssured.get("posts/4");
        response.prettyPrint();
        String actualContentType = response.contentType();
        Assert.assertEquals("application/json; charset=utf-8", actualContentType);
        
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(200, actualStatusCode);
        
        String statusLine=response.statusLine();
        Assert.assertTrue(statusLine.contains("200"));
       
        Assert.assertTrue(response.asString().contains("eum et est occaecati"));

    }
    
    
    
  //get method for POSTS end point
    @Test
    public void getAllPosts(){

        Response response=RestAssured.get("posts");
        response.prettyPrint();
        String actualContentType = response.contentType();
        Assert.assertEquals("application/json; charset=utf-8", actualContentType);
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(200, actualStatusCode);
        String statusLine=response.statusLine();
        Assert.assertTrue(statusLine.contains("200"));
        Assert.assertTrue(response.asString().contains("Quality"));

    }
    

  //get method for COMMENTS end point
    @Test
    public void getCommentsByID(){
        
    	Response response= RestAssured.given().
                pathParam("id", "5").
                when().
                get("/posts/{id}");             
                response.then().log().all();;    	
    	String statusLine=response.statusLine();
    	Assert.assertTrue(statusLine.contains("200"));
    }
    

    //get method for USERS end point 
    @Test
    public void getUsers1(){
        //passing wrong url, verify if the status code is 404.
        given().
                when().get("users/a").
                then().statusCode(404);
        
    }



    //get method for USERS end point 
    @Test
    public void testBody(){
        given(). 
        		
                when().get("users/4").
                
                then().assertThat().body("name",notNullValue());
   
    }

  
   
    
    private static String payloadForComments = "{\n" +
            "  \"name\": \"Jhon Black\",\n" +
            "  \"postId\": \"20000\",\n" +
            "  \"body\": \"Some name text\"\n" +
            "}";
    
  //post method for COMMENTS end point 
    @Test
    public void postNewComment() {
    	
    	given().
    			contentType(ContentType.JSON)
    			.body(payloadForComments)
    			.post("/comments")
    			.then()
    			.statusCode(201);
    	}
    
    
  //post method for POSTS end point 
    @Test
    public void postNewPost() {
    	Posts newPost = new Posts();
    	newPost.setUserID(1400);
    	newPost.setTitle("My first post");
    	newPost.setBody("This is my first post");
    	
    	Response response = given().
    			contentType(ContentType.JSON)
    			.body(newPost)
    			.post("/posts");
    	
    	String statusLine=response.statusLine();
    	Assert.assertTrue(statusLine.contains("201"));
    	
    }
    
    
    private static String payloadForUsers = "{\"name\":\"Clementine Bauch\",\"username\":"
    		+ "\"Samantha\",\"email\":\"Nathan@yesenia.net\","
    		+ "\"address\":{\"street\":\"Douglas Extension\","
    		+ "\"suite\":\"Suite 847\",\"city\":\"McKenziehaven"
    		+ "\",\"zipcode\":\"59590-4157\",\"geo\":"
    		+ "{\"lat\":\"-68.6102\",\"lng\":\"-47.0653\"}},"
    		+ "\"phone\":\"1-463-123-4447\",\"website\":"
    		+ "\"ramiro.info\",\"company\":{\"name\":"
    		+ "\"Romaguera-Jacobson\",\"catchPhrase\":"
    		+ "\"Face to face bifurcated interface\",\"bs\":"
    		+ "\"e-enable strategic applications\"}}";
    
    
    
    //post method for USERS end point 
    @Test
    public void postNewUser() {
    	
    	given().
    			contentType(ContentType.JSON)
    			.body(payloadForUsers)
    			.post("/users")
    			.then()
    			.statusCode(201)
    			.extract()
    			.response();
    	}


	//put method for POSTS end point 
	
    @Test
	public void putPost() {
		Posts post2 = new Posts();
		
		post2.setTitle("My changed post");
		post2.setBody("I decided to update my post");
		Response response = given().
				pathParam("id",3)
				.contentType(ContentType.JSON)
				.when()
				.body(post2)
				.put("/posts/{id}");
		response.prettyPrint();
		String statusLine=response.statusLine();
		Assert.assertTrue(statusLine.contains("200"));
		
	}
    
    
    private static String payload2ForUsers = "{\"name\":\"Jhon Bach\",\"username\":"
    		+ "\"Samantha\",\"email\":\"Nathan@yesenia.net\","
    		+ "\"address\":{\"street\":\"Douglas Extension\","
    		+ "\"suite\":\"Suite 847\",\"city\":\"McKenziehaven"
    		+ "\",\"zipcode\":\"59590-4157\",\"geo\":"
    		+ "{\"lat\":\"-68.6102\",\"lng\":\"-47.0653\"}},"
    		+ "\"phone\":\"1-463-123-4447\",\"website\":"
    		+ "\"ramiro.info\",\"company\":{\"name\":"
    		+ "\"Romaguera-Jacobson\",\"catchPhrase\":"
    		+ "\"Face to face bifurcated interface\",\"bs\":"
    		+ "\"e-enable strategic applications\"}}";
    
  //put method for USERS end point 
    @Test
    public void putUsers() {
    	
    	Response response = given().
				pathParam("id",3)
				.contentType(ContentType.JSON)
				.when()
				.body(payload2ForUsers)
				.put("/users/{id}");
		response.prettyPrint();
		String statusLine=response.statusLine();
		Assert.assertTrue(statusLine.contains("200"));
    	
    }

    
    private static String payload2ForComments = "{\n" +
            "  \"name\": \"Jimmy Black\",\n" +
            "  \"postId\": \"20000\",\n" +
            "  \"body\": \"Some name text\"\n" +
            "}";
    
    
    //put method for Comments end point 
    @Test
    public void putComments() {
    	
    	Response response = given().
				pathParam("id",11)
				.contentType(ContentType.JSON)
				.when()
				.body(payload2ForComments)
				.put("/comments/{id}");
		response.prettyPrint();
		String statusLine=response.statusLine();
		Assert.assertTrue(statusLine.contains("200"));
		Assert.assertTrue(response.asString().contains("Jimmy"));
    	
    }
    
    
    
	
	
}
