import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.page.PageConstruct;
import utils.BaseTest;

public class ConstructTest extends BaseTest {
    PageConstruct pageConstruct;

    @Test
    @DisplayName("Активация блока булок, после клика на начинку и скрол до 1х булок в списке")
    public void bunsVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToFillings();
        pageConstruct.goToBuns();
        assert pageConstruct.isFirstBunVisible();
    }

    @Test
    @DisplayName("Активация блока соус и скрол до 1го соуса в списке")
    public void saucesVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToSauces();
        assert pageConstruct.isFirstSauceVisible();
    }

    @Test
    @DisplayName("Активация блока начинок и скрол до 1ой начинке в списке")
    public void fillingsVisibilityTest() {
        pageConstruct = new PageConstruct(driver);
        pageConstruct.openPage();
        pageConstruct.goToFillings();
        assert pageConstruct.isFirstFillingVisible();
    }


}