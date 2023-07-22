package Project;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;


public class gitHubProject {
    String sshKey = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIMB/LHJOzLxJy4ovrGKHXwdMN6cxH4cSR9W95l1+MU6Q";
    int sshKeyId;

    //Request Specification
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.github.com/user/keys")
            .addHeader("Authorization", "token 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")
            .addHeader("Content-Type", "application/json")
            .build();

    //Response Specification
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectResponseTime(lessThan(5000L))
            .expectBody("key",equalTo(sshKey))
            .expectBody("title",equalTo("TestAPIKey"))
            .build();

    @Test(priority = 1)
    public void postTest(){
        Map<String,String> reqBody=new HashMap<>();
        reqBody.put("title","TestAPIKey");
        reqBody.put("key",sshKey);
        Response response= given().spec(requestSpec).body(reqBody).post();
        System.out.println(response.getBody().asPrettyString());
        sshKeyId= response.then().extract().path("id");
        System.out.println(sshKeyId);
        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().statusCode(201).spec(responseSpec);
    }

    @Test(priority = 2)
    public void getTest(){
        given().spec(requestSpec).pathParam("keyId",sshKeyId)
                .when().get("/{keyId}")
                .then().statusCode(200).spec(responseSpec);
    }
    @Test(priority = 3)
    public void deleteTest(){
        given().spec(requestSpec).pathParam("keyId",sshKeyId)
                .when().delete("/{keyId}")
                .then().statusCode(204).time(lessThan(3000L));
    }

}
