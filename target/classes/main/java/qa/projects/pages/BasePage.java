package qa.projects.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends ParentPage{

    @FindBy(css = "fat-menu")
    public WebElement catalogButton;

    @FindBy(css = "[name='search']")
    public WebElement searchField;

    @FindBy(css = "button.search-form__submit")
    public WebElement submit;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public BasePage clickCatalogButton (){
        clickOnElement(catalogButton);
        return this;
    }

    public BasePage enterCategory(String name){
        enterTextIntoElement(searchField, name);
        return this;
    }

    public BasePage openBasePage(){
        driver.get(configProperties.BASE_URL());
        return this;
    }

    public BasePage openPage(){
        try {
            driver.get("https://rozetka.com.ua");
            logger.info("Login Page was opened");
        } catch (Exception e){
            logger.error("Can not open Login page");
            Assert.fail("Can not open Login page");
            Assert.assertEquals("Login page is not loaded","https://my.airslate.com/login",driver.getCurrentUrl());
        }
        return this;
    }
}
