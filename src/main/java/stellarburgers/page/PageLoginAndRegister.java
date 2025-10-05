package stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocatorsAndMethod {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By personalAccountLocator = By.cssSelector("a[href*='account']");
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By passwordError = By.xpath("//p[text()='Некорректный пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.linkText("Войти");
    public final By loginButtonAfterRegistration = By.xpath("//button[text()='Войти']");
    private final By loginButtonMain = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By userNameField = By.name("Name");

    public LocatorsAndMethod(WebDriver driver) {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
        return !driver.findElements(passwordError).isEmpty();
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

    @Step("Перейти во вкладку Булки")
    public void goToBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        driver.findElement(bunsTab).click();
    }

    @Step("Перейти во вкладку Соусы")
    public void goToSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        driver.findElement(saucesTab).click();
    }

    @Step("Перейти во вкладку Начинки")
    public void goToFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        driver.findElement(fillingsTab).click();
    }

    // Для проверки входа
    @Step("Проверить успешный вход пользователя: {expectedName}")
    public boolean isLoginSuccessful(String expectedName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
        String actualName = driver.findElement(userNameField).getAttribute("value");
        return expectedName.equals(actualName);
    }
}
