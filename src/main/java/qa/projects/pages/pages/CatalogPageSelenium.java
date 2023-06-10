package qa.projects.pages.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CatalogPageSelenium extends ParentPageSelenium {

    @FindBy(css = ".header-catalog li.menu-categories__item")
    public WebElement catalogModal;

    @FindBy(css = ".sidebar li.menu-categories__item")
    public List<WebElement> categories;

    @FindBy(css = "p.menu__hidden-title")
    public List<WebElement>  subCategories;

    public CatalogPageSelenium(WebDriver driver) {
        super(driver);
    }

    public CatalogPageSelenium openPage(){
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
