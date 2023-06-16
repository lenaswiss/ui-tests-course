package qa.projects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.projects.pages.CatalogPage;


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

    @AfterMethod
    public void afterMethods() {
        closeWebDriver();
    }
}
