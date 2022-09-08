package restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class RestAssuredTest {
    @Test
    public void checkUserUpdatedTest() {
        UserModel updateBody = UserModel
                .builder()
                .name("morpheus")
                .job("zion resident")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(updateBody)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void checkUserDeletedTest() {
        RestAssured
                .given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    public void checkRegisterSuccessfulTest() {
        UserModel updateBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(updateBody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200);
    }
    @Test
    public void checkRegisterUnsuccessfulTest() {
        UserModel updateBody = UserModel
                .builder()
                .email("sydney@fife")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(updateBody)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400);
    }
    @Test
    public void checkLoinSuccessfulTest() {
        UserModel updateBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(updateBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200);
    }
    @Test
    public void checkLoginUnsuccessfulTest() {
        UserModel updateBody = UserModel
                .builder()
                .email("peter@klaven")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(updateBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400);
    }
    @Test
    public void checkDelayedResponse() {
        RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("page", instanceOf(Integer.class))
                .body("per_page", equalTo(6));
    }
}
