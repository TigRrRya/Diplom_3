import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import stellarburgers.model.User;
import stellarburgers.page.PageLogin;
import stellarburgers.page.PageRegister;
import utils.BaseTest;
import utils.LoginAndDeleteUserApi;
import utils.UserGenerator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private User user;
    private boolean isUserRegistered = false;
    private String accessToken;

    @After
    public void cleanupUser() {
        if (user != null && isUserRegistered) {

            Response response = LoginAndDeleteUserApi.loginUser(user);

            if (response.statusCode() == 200) {
                accessToken = response.path("accessToken");

                if (accessToken != null) {
                    LoginAndDeleteUserApi.deleteUser(accessToken);
                }
            }
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет, что пользователь может успешно зарегистрироваться с корректными данными и автоматически перенаправляется на страницу авторизации (логина).")
    public void successfulRegistrationTest() {
        PageRegister page = new PageRegister(driver);
        PageLogin pageLogin = new PageLogin(driver);

        user = UserGenerator.getValidUser();

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.register(user.getName(), user.getEmail(), user.getPassword());


        assertFalse("Кнопка 'Войти' не видна после регистрации", driver.findElements(pageLogin.loginButtonInAccount).isEmpty());

        isUserRegistered = true;
    }

    @Test
    @DisplayName("Ошибка с коротким паролем")
    @Description("Проверяет, что при попытке регистрации с паролем менее 6 символов, система отображает сообщение об ошибке 'Некорректный пароль' и регистрация не происходит.")
    public void invalidPasswordRegistrationTest() {
        PageRegister page = new PageRegister(driver);

        user = UserGenerator.getUserWithCustomPassword("12345");

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("Ошибка для некорректного пароля не отображается",
                page.isPasswordErrorVisible());

        isUserRegistered = false;
        this.accessToken = null;
    }
}