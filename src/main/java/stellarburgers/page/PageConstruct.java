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


    @Step("Открыть главную страницу")
    public void openPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }


    @Step("Перейти во вкладку Булки")
    public void goToBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Перейти во вкладку Соусы")
    public void goToSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Перейти во вкладку Начинки")
    public void goToFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }


    @Step("Проверка видимости элемента")
    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean isTabActive(By tabLocator) {
        try {

            WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));

            String classAttribute = tabElement.findElement(By.xpath("./..")).getAttribute("class");

            return classAttribute.contains("tab_tab_type_current");

        } catch (TimeoutException | NullPointerException e) {
            return false;
        }
    }

    @Step("Проверить, что вкладка Булки активна")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что вкладка Соусы активна")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что вкладка Начинки активна")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
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
}