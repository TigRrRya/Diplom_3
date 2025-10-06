import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.page.PageConstruct;
import utils.BaseTest;

import static org.junit.Assert.assertTrue;

public class ConstructTest extends BaseTest {
    PageConstruct pageConstruct;

    @Test
    @DisplayName("Переключение с Начинок обратно на Булки")
    @Description("Проверяет, что при клике на вкладку 'Булки' после переключения на 'Начинки', вкладка 'Булки' становится активной и происходит скролл к началу списка булок.")
    public void bunsVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToFillings();
        pageConstruct.goToBuns();


        assertTrue("Вкладка 'Булки' не активна после клика.",
                pageConstruct.isBunsTabActive());
        assertTrue("Первая булка не видна (скролл не сработал).",
                pageConstruct.isFirstBunVisible());
    }


    @Test
    @DisplayName("Переход на Соусы")
    @Description("Проверяет, что при клике на вкладку 'Соусы' она становится активной, и страница автоматически прокручивается к первой позиции в списке соусов.")
    public void saucesVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToSauces();
        assertTrue("Вкладка 'Соусы' не активна после клика.",
                pageConstruct.isSaucesTabActive());
        assertTrue("Первый соус не виден (скролл не сработал).",
                pageConstruct.isFirstSauceVisible());
    }


    @Test
    @DisplayName("Переход на Начинки")
    @Description("Проверяет, что при клике на вкладку 'Начинки' она становится активной, и происходит скролл к первой позиции в списке начинок.")
    public void fillingsVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToFillings();
        assertTrue("Вкладка 'Начинки' не активна после клика.",
                pageConstruct.isFillingsTabActive());
        assertTrue("Первая начинка не видна (скролл не сработал).",
                pageConstruct.isFirstFillingVisible());
    }
}