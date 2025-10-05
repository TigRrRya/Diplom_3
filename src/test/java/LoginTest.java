import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.page.PageLogin;
import utils.BaseUserTest;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseUserTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверяет возможность входа в аккаунт через большую кнопку 'Войти в аккаунт' на главной странице конструктора.")
    public void loginButtonMainTest() {
        PageLogin page = new PageLogin(driver);

        driver.get("https://stellarburgers.nomoreparties.site");
        page.clickLoginButtonMain();
        page.login(user.getEmail(), user.getPassword());
        assertTrue("Ошибка: Вход не выполнен или имя пользователя не совпадает", page.isLoginSuccessful(user.getName()));
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверяет возможность входа в аккаунт через кнопку 'Личный кабинет' в хедере страницы.")
    public void loginPersonalAccount() {
        PageLogin page = new PageLogin(driver);

        driver.get("https://stellarburgers.nomoreparties.site");
        page.goToPersonalAccount();
        page.login(user.getEmail(), user.getPassword());
        assertTrue("Ошибка: Вход не выполнен или имя пользователя не совпадает", page.isLoginSuccessful(user.getName()));
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' в форме регистрации")
    @Description("Проверяет возможность перехода на форму логина и последующего входа, используя ссылку 'Войти' на странице регистрации.")
    public void loginButtonInRegisterForm() {
        PageLogin page = new PageLogin(driver);

        driver.get("https://stellarburgers.nomoreparties.site/register");
        page.goToLogin();
        page.login(user.getEmail(), user.getPassword());
        assertTrue("Ошибка: Вход не выполнен или имя пользователя не совпадает", page.isLoginSuccessful(user.getName()));

    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' в форме восстановления пароля")
    @Description("Проверяет возможность перехода на форму логина и последующего входа, используя ссылку 'Войти' на странице восстановления пароля.")
    public void loginButtonInForgotPassword() {
        PageLogin page = new PageLogin(driver);

        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        page.goToLogin();
        page.login(user.getEmail(), user.getPassword());
        assertTrue("Ошибка: Вход не выполнен или имя пользователя не совпадает", page.isLoginSuccessful(user.getName()));

    }
}