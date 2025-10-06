package utils;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import stellarburgers.model.User;


public abstract class BaseUserTest extends BaseTest {

    protected User user;
    protected String accessToken;


    @Before
    public void createUser() {
        user = UserGenerator.getValidUser();
        Response response = LoginAndDeleteUserApi.registerUser(user);
        accessToken = response.path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            LoginAndDeleteUserApi.deleteUser(accessToken);
        }
    }
}
