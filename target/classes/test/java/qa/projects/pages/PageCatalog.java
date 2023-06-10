package qa.projects.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageCatalog {

    public static SelenideElement catalogModel = $(".header-catalog div.menu-wrapper");
    public static ElementsCollection categories = $$(".header-catalog li.menu-categories__item");
    public static ElementsCollection subCategories =  $$("p.menu__hidden-title");
}
