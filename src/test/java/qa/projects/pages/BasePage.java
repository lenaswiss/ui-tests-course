package qa.projects.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public static String baseUrl = "https://rozetka.com.us";
    public static SelenideElement catalogButton = $("#fat-menu");

    public static void clickOnCatalogButton() {
        catalogButton.click();
    }
}
