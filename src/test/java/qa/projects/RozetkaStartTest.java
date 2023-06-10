package qa.projects;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qa.projects.pages.CatalogPage;
import qa.projects.pages.BasePage;


import static com.codeborne.selenide.Selenide.*;


public class RozetkaStartTest {

    @BeforeClass
    public void beforeMethod() throws InterruptedException {
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        open("https://rozetka.com.ua/");
        Configuration.timeout = 6000;
    }

    @Test
    public void CatalogTest2() {
        BasePage.clickOnCatalogButton();
        CatalogPage.catalogModel.shouldBe(Condition.visible);
        CatalogPage.subCategories.shouldHave(CollectionCondition.size(15));
    }

    @Test
    public void baseCatalogTest() {
        $$(".sidebar li.menu-categories__item").shouldHave(CollectionCondition.size(17));
    }

    @Test
    public void CatalogTest() {
        BasePage.clickOnCatalogButton();
        CatalogPage.catalogModel.shouldBe(Condition.visible);
        CatalogPage.categories.shouldHave(CollectionCondition.size(17));
    }

    @Test
    public void searchCategory() {
        $("[name='search']").setValue("Планшеты");
        $("button.search-form__submit").click();
        $("li.breadcrumbs__item--text").shouldHave(Condition.text("Планшеты"));
    }

    @AfterClass
    public void afterMethods() {
        closeWebDriver();
    }
}
