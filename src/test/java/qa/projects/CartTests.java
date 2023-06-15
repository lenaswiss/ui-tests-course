package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.pages.AppleSearchPage;
import qa.projects.pages.CatalogPage;
import qa.projects.pages.FiltersSideBarPage;

import static com.codeborne.selenide.Selenide.*;

public class CartTests {
    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        WebDriverRunner.setWebDriver(new FirefoxDriver());
        open("https://rozetka.com.ua/");
        Configuration.timeout = 6000;
    }

    /**
     * Task 1: created automated test, that will open URL and execute next steps:
     * Check, that cart is empty.
     * Type to search ‘iphone’.
     * Press Enter.
     * Add first product to cart.
     * Check that one product is in the cart.
     * Open the cart modal.
     * Check that one product is displaying in cart.
     * Remove product from cart.
     * Check, that product is removed.
     */
    @Test
    public void addingToCartTest() throws InterruptedException {
        CatalogPage.clickCartIcon();
        Assert.assertTrue(CatalogPage.cartModal.isDisplayed(), "Modal is displayed");
        Assert.assertEquals(CatalogPage.emptyCart.text(), "Корзина пуста");
        CatalogPage.closeCartModal();
        CatalogPage.searchItemAndAddAnyToCart("iphone");
        Assert.assertTrue(webdriver().driver().getCurrentFrameUrl().contains("search_text=iphone"),
                "Url does not contains search_text=iphone");
        Assert.assertEquals(CatalogPage.getCartQuantity(), "1",
                "quantity of items on curt does not equal expected");
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
    // number of result equal to 7 for iPhone 13 and remains the same after seller Rozetka is applied.
    @Test
    public void searchOfItemsUsingEnterTest() throws InterruptedException {
        CatalogPage.searchItemUsingEnter("sapiens");
        FiltersSideBarPage.pageIsLoaded();
        Assert.assertTrue(webdriver().driver().getCurrentFrameUrl().contains("text=sapiens"),
                "Filter ‘sapiens’ was not added");
        var foundFirst = FiltersSideBarPage.searchResultAll.getText().trim().
                replaceAll("^\\(|\\)$", "");
        FiltersSideBarPage.sellerFilterSideBar.click();
        var foundSecond = FiltersSideBarPage.searchResultAll.getText().trim().
                replaceAll("^\\(|\\)$", "");
        Assert.assertTrue(Integer.parseInt(foundSecond) <= Integer.parseInt(foundFirst),
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
        Assert.assertEquals(AppleSearchPage.gridImageSize.getSize().width, 160,
                "Grid width does not match expected size");
        Assert.assertEquals(AppleSearchPage.gridImageSize.getSize().height, 211
                , "Grid height does not match expected size");
        open(AppleSearchPage.bigTileImageSize.getAttribute("src"));
        var bigTileHeight = $("img").getSize().height;
        var bigTileWeight = $("img").getSize().width;
        Assert.assertEquals(bigTileWeight, 257,
                "Big tile width does not match expected size");
        Assert.assertEquals(bigTileHeight, 350,
                "Big tile height does not match expected size");
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
        AppleSearchPage.sortChip.click();
        Thread.sleep(1000);
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
