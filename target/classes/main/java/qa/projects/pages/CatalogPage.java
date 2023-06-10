package qa.projects.pages;

import com.codeborne.selenide.impl.CollectionElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

import java.util.List;


public class CatalogPage extends ParentPage{

    @FindBy(css = ".header-catalog li.menu-categories__item")
    public WebElement catalogModal;

    @FindBy(css = ".sidebar li.menu-categories__item")
    public List<WebElement> categories;

    @FindBy(css = "p.menu__hidden-title")
    public List<WebElement>  subCategories;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public CatalogPage openPage(){
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
