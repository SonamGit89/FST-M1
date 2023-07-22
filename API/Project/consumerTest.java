package Project;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
@ExtendWith(PactConsumerTestExt.class)

public class consumerTest {
    // Create Map for the headers
    Map<String, String> headers = new HashMap<String, String>();
    String createUser = "/api/users";
    // Create Pact contract
    @Pact(consumer = "UserConsumer",provider = "UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder) throws ParseException {
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id", 889)
                .stringType("firstName", "Sonam")
                .stringType("lastName", "Dutta")
                .stringType("email", "sonam.dutta@gmail.com");
        return builder.given("A request to create a user")
                .uponReceiving("A request to create a user")
                .path(createUser)
                .method("POST")
                .headers(headers)
                .body(requestResponseBody)
                .willRespondWith()
                .status(201)
                .body(requestResponseBody)
                .toPact();

    }
    @Test
    @PactTestFor(providerName = "UserProvider", port = "8080")
    public void postRequestTest(){
        RestAssured.baseURI = "http://localhost:8080";
        RequestSpecification rq = RestAssured.given().headers(headers).when();
        Map<String, Object> reqBody=new HashMap<String, Object>();
        reqBody.put("id",889);
        reqBody.put("firstName","Sonam");
        reqBody.put("lastName","Dutta");
        reqBody.put("email","sonam.dutta@gmail.com");
        Response response = rq.body(reqBody).post(createUser);
        assert (response.getStatusCode() == 201);

    }

}
