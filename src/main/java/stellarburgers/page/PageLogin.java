package stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageLogin {

    public final By loginButtonInAccount = By.xpath("//button[text()='Войти']");
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By userNameField = By.name("Name");
    private final By personalAccountLocator = By.cssSelector("a[href*='account']");
    private final By loginButtonMain = By.xpath("//button[text()='Войти в аккаунт']");
    private final By emailInputLogin = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputLogin = By.name("Пароль");
    private final By loginLink = By.linkText("Войти");
    public PageLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public void clickLoginButtonMain() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMain));
        driver.findElement(loginButtonMain).click();
    }


    @Step("Перейти в Личный Кабинет")
    public void goToPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountLocator));
        driver.findElement(personalAccountLocator).click();
    }

    @Step("Ввести email: {email}")
    public void enterEmailLogin(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLogin));
        driver.findElement(emailInputLogin).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPasswordLogin(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLogin));
        driver.findElement(passwordInputLogin).sendKeys(password);
    }

    @Step("Заполнить форму авторизации и войти")
    public void login(String email, String password) {
        enterEmailLogin(email);
        enterPasswordLogin(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonInAccount));
        driver.findElement(loginButtonInAccount).click();
    }

    @Step("Перейти на страницу логина")
    public void goToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
    }

    // Для проверки входа
    @Step("Проверить успешный вход пользователя: {expectedName}")
    public boolean isLoginSuccessful(String expectedName) {
        goToPersonalAccount();
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
        String actualName = driver.findElement(userNameField).getAttribute("value");
        return expectedName.equals(actualName);
    }


}
