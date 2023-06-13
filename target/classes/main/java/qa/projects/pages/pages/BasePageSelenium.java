package qa.projects.pages.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePageSelenium extends ParentPageSelenium {

    @FindBy(css = "fat-menu")
    public WebElement catalogButton;

    @FindBy(css = "[name='search']")
    public WebElement searchField;

    @FindBy(css = "button.search-form__submit")
    public WebElement submit;

    public BasePageSelenium(WebDriver driver) {
        super(driver);
    }

    public BasePageSelenium clickCatalogButton (){
        clickOnElement(catalogButton);
        return this;
    }

    public BasePageSelenium enterCategory(String name){
        enterTextIntoElement(searchField, name);
        return this;
    }

    public BasePageSelenium openBasePage(){
        driver.get(configProperties.BASE_URL());
        return this;
    }

    public BasePageSelenium openPage(){
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
