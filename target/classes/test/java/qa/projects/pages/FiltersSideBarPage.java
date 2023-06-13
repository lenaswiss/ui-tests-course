package qa.projects.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FiltersSideBarPage {

    public static SelenideElement filersList = $(".side-filter__body");
    public static SelenideElement sellerFilterSideBar = $("div.sidebar-block.ng-star-inserted[data-filter-name=\"seller\"]");
    public static SelenideElement searchResultAll = $(".categories-filter__link.categories-filter__link_type_first .sidebar-block__quantity");

    public static SelenideElement checkboxFilterRozetka = $("a.checkbox-filter__link[data-id=\"Rozetka\"]");
    public static SelenideElement pageHeader = $(".catalog-heading.ng-star-inserted");

    public static void pageIsLoaded() throws InterruptedException {
        while (!pageHeader.isDisplayed()){
            Thread.sleep(1000);
        }
    }
}
