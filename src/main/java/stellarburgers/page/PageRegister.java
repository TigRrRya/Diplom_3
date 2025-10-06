package stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageRegister {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы


    private final By nameInputRegister = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInputRegister = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputRegister = By.name("Пароль");
    private final By passwordErrorRegister = By.xpath("//p[text()='Некорректный пароль']");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");


    public PageRegister(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Шаги и методы
    @Step("Ввести имя: {name}")
    public void enterNameRegister(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputRegister));
        driver.findElement(nameInputRegister).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmailRegister(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputRegister));
        driver.findElement(emailInputRegister).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPasswordRegister(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputRegister));
        driver.findElement(passwordInputRegister).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        driver.findElement(registerButton).click();
    }


    @Step("Проверить сообщение ошибки для некорректного пароля")
    public boolean isPasswordErrorVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorRegister));
        return !driver.findElements(passwordErrorRegister).isEmpty();
    }


    @Step("Заполнить форму регистрации и зарегистрировать пользователя")
    public void register(String name, String email, String password) {
        enterNameRegister(name);
        enterEmailRegister(email);
        enterPasswordRegister(password);
        clickRegister();
    }


}
