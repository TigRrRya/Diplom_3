import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.model.User;
import stellarburgers.page.PageLoginAndRegister;
import utils.BaseTest;
import utils.LoginAndDeleteUserApi;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    private User user;
    private String accessToken;

    @After
    public void cleanupUser() {
        if (accessToken != null) {
            LoginAndDeleteUserApi.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Проверка регистрации")
    public void successfulRegistrationTest() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);

        user = UserGenerator.getValidUser();

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.register(user.getName(), user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(page.loginButtonInAccount));

        assertTrue("Кнопка 'Войти' не видна после регистрации",
                !driver.findElements(page.loginButtonInAccount).isEmpty());

        Response response = LoginAndDeleteUserApi.loginUser(user);
        accessToken = response.path("accessToken");
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    public void invalidPasswordRegistrationTest() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);

        user = UserGenerator.getUserWithCustomPassword("12345");

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("Ошибка для некорректного пароля не отображается",
                page.isPasswordErrorVisible());

        accessToken = null;
    }
}
