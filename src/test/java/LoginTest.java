import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.page.PageLoginAndRegister;
import utils.BaseUserTest;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseUserTest {


    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void loginButtonMainTest() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);


        driver.get("https://stellarburgers.nomoreparties.site");
        page.clickLoginButtonMain();
        page.login(user.getEmail(), user.getPassword());
        assertTrue(page.isLoginSuccessful(user.getName()));


    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void loginPersonalAccount() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);


        driver.get("https://stellarburgers.nomoreparties.site");
        page.goToPersonalAccount();
        page.login(user.getEmail(), user.getPassword());
        assertTrue(page.isLoginSuccessful(user.getName()));
    }

    @Test
    @DisplayName("вход через кнопку Войти в форме регистрации;")
    public void loginButtonInRegisterForm() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.goToLogin();
        page.login(user.getEmail(), user.getPassword());
        assertTrue(page.isLoginSuccessful(user.getName()));

    }

    @Test
    @DisplayName("вход через кнопку Войти в форме восстановление пароля;")
    public void loginButtonInForgotPassword() {
        PageLoginAndRegister page = new PageLoginAndRegister(driver);

        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        page.goToLogin();
        page.login(user.getEmail(), user.getPassword());
        assertTrue(page.isLoginSuccessful(user.getName()));

    }


}
