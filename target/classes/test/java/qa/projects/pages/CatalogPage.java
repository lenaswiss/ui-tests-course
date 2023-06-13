package qa.projects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {

    public static SelenideElement catalogModel = $(".header-catalog div.menu-wrapper");
    public static ElementsCollection categories = $$(".header-catalog li.menu-categories__item");
    public static ElementsCollection subCategories = $$("p.menu__hidden-title");
    public static SelenideElement cartIcon = $("rz-cart.header-actions__component");
    public static SelenideElement cartModal = $("div.cart-dummy");
    public static SelenideElement emptyCart = $("h4.cart-dummy__heading");
    public static SelenideElement cartBadge = $("span.badge.badge--green");
    public static SelenideElement cartActionMenu = $("li.header-actions__item--cart");
    public static SelenideElement addToCart = $(".toOrder>button.buy-button");
    public static SelenideElement closeCartModal = $("button.modal__close");
    public static SelenideElement searchInput = $("[name='search']");
    public static SelenideElement searchButton = $("button.search-form__submit");
    public static SelenideElement searchPageHeader = $("h1.catalog-heading");
    public static SelenideElement filters = $("button.catalog-settings__filter-button");

    public static void clickCartIcon() {
        cartIcon.click();
    }

    public static void closeCartModal() {
        closeCartModal.click();
    }

    public static void searchItem(String string) throws InterruptedException {
        while (!searchInput.isEnabled()){
            Thread.sleep(1000);
        }
        searchInput.clear();
        searchInput.sendKeys(string);
        searchButton.click();
        Thread.sleep(2000);
    }

    public static void searchItemAndAddAnyToCart(String string) throws InterruptedException {
        while (!searchInput.isEnabled()){
            Thread.sleep(1000);
        }
        searchInput.clear();
        searchInput.sendKeys(string);
        searchButton.click();
        searchPageIsLoaded();
        Thread.sleep(4000);
        addToCart.click();
        Thread.sleep(4000);
    }

    public static void searchItemUsingEnter(String string) throws InterruptedException {
        while (!searchInput.isEnabled()){
            Thread.sleep(1000);
        }
        searchInput.clear();
        searchInput.sendKeys(string);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(6000);
    }

    public static void searchPageIsLoaded() {
        Selenide.Wait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.catalog-heading")));
    }

    public static int getCartQuantity1() {
        return Integer.getInteger(cartBadge.getText());
    }

    public static String getCartQuantity() {
        return cartBadge.text().trim();
    }
}
