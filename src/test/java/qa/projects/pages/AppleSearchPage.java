package qa.projects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AppleSearchPage {

    public static ElementsCollection appleCategories = $$("a.tile-cats__heading");
    public static SelenideElement pageTitle = $("h1.portal__heading");
    public static SelenideElement sortChip = $(".select-css [value=\"1: cheap\"]");
    public static ElementsCollection selectResultPriceList = $$(".catalog-grid.ng-star-inserted span.goods-tile__price-value");
    public static SelenideElement gridImageSize = $(".goods-tile__picture.ng-star-inserted");
    public static SelenideElement bigTileImageSize = $("a.goods-tile__picture  .lazy_img_hover");
    public static double priceFormatToDouble(String string){
        String newString = String.format(string).replace((" "), "");
        return Double.parseDouble(String.format(newString).replace(("₴"), ""));
    }

}
