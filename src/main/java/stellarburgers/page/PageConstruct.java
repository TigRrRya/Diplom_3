package stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageConstruct {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By firstBunByHref = By.cssSelector("a[href='/ingredient/61c0c5a71d1f82001bdaaa6d']");
    private final By firstSauceByHref = By.cssSelector("a[href='/ingredient/61c0c5a71d1f82001bdaaa72']");
    private final By firstFillingByHref = By.cssSelector("a[href='/ingredient/61c0c5a71d1f82001bdaaa6f']");

    public PageConstruct(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Step("Перейти во вкладку Булки")
    public void goToBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        driver.findElement(bunsTab).click();
        pause(1);
    }

    @Step("Перейти во вкладку Соусы")
    public void goToSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        driver.findElement(saucesTab).click();
        pause(1);
    }

    @Step("Перейти во вкладку Начинки")
    public void goToFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        driver.findElement(fillingsTab).click();
        pause(1);
    }

    @Step("Проверка видимости первого элемента в разделе")
    public boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFirstBunVisible() {
        return isElementVisible(firstBunByHref);
    }

    public boolean isFirstSauceVisible() {
        return isElementVisible(firstSauceByHref);
    }

    public boolean isFirstFillingVisible() {
        return isElementVisible(firstFillingByHref);
    }

    @Step("Открыть главную страницу")
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        pause(1);
    }
}
