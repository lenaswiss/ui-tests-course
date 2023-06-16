package qa.projects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Dimension;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AppleSearchPage {

    public static ElementsCollection appleCategories = $$("a.tile-cats__heading");
    public static SelenideElement pageTitle = $("h1.portal__heading");
    public static SelenideElement sortChip = $(".select-css [value=\"1: cheap\"]");
    public static ElementsCollection selectResultPriceList = $$(".catalog-grid.ng-star-inserted span.goods-tile__price-value");
    public static SelenideElement gridImageSize = $(".goods-tile__picture.ng-star-inserted");
    public static SelenideElement bigTileImageSize = $("a.goods-tile__picture  .lazy_img_hover");
    public static SelenideElement filtersList = $(".side-filter__body");
    public static SelenideElement sellerFilterSideBar = $("div.sidebar-block.ng-star-inserted[data-filter-name=\"seller\"]");
    public static SelenideElement searchResultAll = $(".catalog-selection__label.ng-star-inserted");
    public static SelenideElement searchResultAllMultiplePages = $(".categories-filter__link.categories-filter__link_type_first .sidebar-block__quantity");

    public static SelenideElement checkboxFilterRozetka = $("a.checkbox-filter__link[data-id=\"Rozetka\"]");
    public static SelenideElement pageHeader = $(".catalog-heading.ng-star-inserted");
    public static final Dimension GRID_IMAGE_EXPECTED = new Dimension(160, 211);
    public static final Dimension BIG_IMAGE_EXPECTED = new Dimension(257, 350);

    public static void pageIsLoaded() throws InterruptedException {
        while (!pageHeader.isDisplayed()) {
            Thread.sleep(1000);
        }
    }

    public static double priceFormatToDouble(String string) {
        String newString = String.format(string).replace((" "), "");
        return Double.parseDouble(String.format(newString).replace(("₴"), ""));
    }

    public static int getSearchResultAllOld() {
        int allItems = 0;
        if (searchResultAll.isDisplayed()) {
            allItems = Integer.parseInt(AppleSearchPage.searchResultAll.getText().trim().
                    replaceAll("^\\(|\\)$", ""));
        }
        if (searchResultAll.isDisplayed()) {
            List<String> lt = List.of(AppleSearchPage.searchResultAll.getText().trim().split(" "));
            allItems = Integer.parseInt(lt.get(1));
        }
        return allItems;
    }

    public static int getSearchResultAll() {
        int allItems = 0;
        if (searchResultAll.isDisplayed()) {
            List<String> lt = List.of(AppleSearchPage.searchResultAll.getText().trim().split(" "));
            allItems = Integer.parseInt(lt.get(1));
        } else if (searchResultAllMultiplePages.isDisplayed()) {
            allItems = Integer.parseInt(AppleSearchPage.searchResultAllMultiplePages.getText().trim().
                    replaceAll("^\\(|\\)$", ""));
        }
        return allItems;
    }
}
