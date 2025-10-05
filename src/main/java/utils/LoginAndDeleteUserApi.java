package utils;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import stellarburgers.model.User;


public class LoginAndDeleteUserApi {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    private static final String AUTH_API = BASE_URL + "/api/auth/";

    @Step("Регистрация пользователя через API: {user.email}")
    public static Response registerUser(User user) {
        JSONObject body = new JSONObject();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());
        body.put("name", user.getName());

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(body.toString())
                .post(AUTH_API + "register");
    }

    @Step("Удаление пользователя через API")
    public static void deleteUser(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .delete(AUTH_API + "user");
    }

    @Step("Логин пользователя через API: {user.email}")
    public static Response loginUser(User user) {
        JSONObject body = new JSONObject();
        body.put("email", user.getEmail());
        body.put("password", user.getPassword());

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(body.toString())
                .post(AUTH_API + "login");
    }
}
