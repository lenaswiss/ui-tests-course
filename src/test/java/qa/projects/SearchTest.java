package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.pages.AppleSearchPage;
import qa.projects.pages.CatalogPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SearchTest {

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        open("https://rozetka.com.ua/");
        Configuration.timeout = 6000;
    }

    /**
     * Task 2:  created automated test, that will open URL and execute next steps:
     * Type ‘Apple’ to the search field.
     * Click on the button ‘Search’.
     * Check that number of categories equals to 20.
     * Open any category.
     * Check, that title contains ‘Apple’ word.
     */
    @Test
    public void searchResultsValidationTest() throws InterruptedException {
        CatalogPage.searchItem("Apple");
        AppleSearchPage.appleCategories.shouldHave(CollectionCondition.size(20));
        Assert.assertEquals(AppleSearchPage.pageTitle.text().trim(), "Apple",
                "The page title does not equal Apple");
    }

    /**
     * Task 3:   created automated test, that will open URL and execute next steps:
     * Type ‘iphone 13’ to the search field.
     * Press Enter.
     * Check that filter ‘iphone 13’ is automatically added.
     * Click on the filter seller Rozetka.
     * Check, that number of result on the page reduced.
     */
    @Test
    public void searchOfItemsUsingEnterTest() throws InterruptedException {
        CatalogPage.searchItemUsingEnter("iphone 13");
        Assert.assertTrue(webdriver().driver().getCurrentFrameUrl().contains("text=iphone+13"),
                "Filter ‘sapiens’ was not added");
        var foundFirst = AppleSearchPage.getSearchResultAll();
        AppleSearchPage.sellerFilterSideBar.click();
        var foundSecond = AppleSearchPage.getSearchResultAll();
        Assert.assertTrue(foundSecond <= foundFirst,
                "Expected less or equal items found after filtering");
    }

    /**
     * Task 4:   created automated test, that will open URL and execute next steps:
     * Type ‘iphone 13’ to the search field.
     * Press Enter.
     * Assert size(width and height) of any product in search result.
     * Switch  grid view to big tile.
     * Assert size(width and height) of any product in search result.
     */
    @Test
    public static void productImagesSizeTest() throws InterruptedException {
        CatalogPage.searchItemUsingEnter("iphone");
        var t = AppleSearchPage.gridImageSize.getSize();
        Assert.assertEquals(AppleSearchPage.gridImageSize.getSize(), AppleSearchPage.GRID_IMAGE_EXPECTED,
                "Grid image size does not match expected size");
        open(AppleSearchPage.bigTileImageSize.getAttribute("src"));
        var bigTileWeight = $("img").getSize();
        Assert.assertEquals(bigTileWeight, AppleSearchPage.BIG_IMAGE_EXPECTED,
                "Big tile image size does not match expected size");
    }


    /**
     * Task 5:  created automated test, that will open URL and execute next steps:
     * Type ‘iphone’ to the search field.
     * Press Enter.
     * Choose sorting ‘from higher prices to lower’.
     * Check that the first product price is bigger, that second.
     * Some additional products can be checked.
     */
    @Test
    public static void compareItemsPriceTest() throws InterruptedException {
        CatalogPage.searchItemUsingEnter("iphone");
        AppleSearchPage.pageIsLoaded();
        var selectedPriseList = AppleSearchPage.selectResultPriceList;
        Assert.assertTrue(AppleSearchPage.priceFormatToDouble(selectedPriseList.get(1).getText()) >=
                        AppleSearchPage.priceFormatToDouble(selectedPriseList.get(0).getText()),
                "Wrong page sorting, expected first item will be chipper then second.");
    }

    @AfterMethod
    public void afterMethods() {
        closeWebDriver();
    }
}
