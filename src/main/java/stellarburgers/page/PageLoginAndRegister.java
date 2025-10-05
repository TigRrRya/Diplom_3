package stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageLoginAndRegister {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By personalAccountLocator = By.cssSelector("a[href*='account']");
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By passwordErrorRegister = By.xpath("//p[text()='Некорректный пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.linkText("Войти");
    public final By loginButtonInAccount = By.xpath("//button[text()='Войти']");
    private final By loginButtonMain = By.xpath("//button[text()='Войти в аккаунт']");


    private final By userNameField = By.name("Name");

    public PageLoginAndRegister(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Шаги и методы
    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на главной")
    public void clickLoginButtonMain() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonMain));
        driver.findElement(loginButtonMain).click();
    }

    @Step("Перейти на страницу логина")
    public void goToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        driver.findElement(loginLink).click();
    }

    @Step("Проверить сообщение ошибки для некорректного пароля")
    public boolean isPasswordErrorVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorRegister));
        return !driver.findElements(passwordErrorRegister).isEmpty();
    }

    @Step("Перейти в Личный Кабинет")
    public void goToPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountLocator));
        driver.findElement(personalAccountLocator).click();
    }

    @Step("Заполнить форму регистрации и зарегистрировать пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegister();
    }


    @Step("Заполнить форму авторизации и войти")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonInAccount));
        driver.findElement(loginButtonInAccount).click();
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
