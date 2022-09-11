package restassured;

import Constants.Urls;
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
                .patch(Urls.USERS_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void checkUserDeletedTest() {
        RestAssured
                .given()
                .when()
                .delete(Urls.USERS_URL)
                .then()
                .statusCode(204);
    }

    @Test
    public void checkRegisterSuccessfulTest() {
        UserModel registerBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(registerBody)
                .when()
                .post(Urls.REGISTER_URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void checkRegisterUnsuccessfulTest() {
        UserModel registerBody = UserModel
                .builder()
                .email("sydney@fife")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(registerBody)
                .when()
                .post(Urls.REGISTER_URL)
                .then()
                .statusCode(400);
    }
    @Test
    public void checkLoginSuccessfulTest() {
        UserModel loginBody = UserModel
                .builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(loginBody)
                .when()
                .post(Urls.LOGIN_URL)
                .then()
                .statusCode(200);
    }
    @Test
    public void checkLoginUnsuccessfulTest() {
        UserModel loginBody = UserModel
                .builder()
                .email("peter@klaven")
                .build();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .body(loginBody)
                .when()
                .post(Urls.LOGIN_URL)
                .then()
                .statusCode(400);
    }
    @Test
    public void checkDelayedResponse() {
        RestAssured
                .given()
                .when()
                .get(Urls.DELAY_URL)
                .then()
                .statusCode(200)
                .body("page", instanceOf(Integer.class))
                .body("per_page", equalTo(6));
    }
}
