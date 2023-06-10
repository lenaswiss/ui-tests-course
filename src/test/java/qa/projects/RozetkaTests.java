package qa.projects;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;



public class RozetkaTests {

    public WebDriver driver;
    Logger logger = Logger.getLogger(getClass());

    @BeforeClass
    public void beforeMethod() {
        File file = new File("/Users/lena/Documents/Java/ui-test/drivers/");
        System.setProperty("webdriver.geckodriver.driver", file.getAbsolutePath());
        driver = new FirefoxDriver();
        logger.info("Browser was opened");
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void baseCatalogTest() {
        var listEl = driver.findElements(By.cssSelector(".sidebar li.menu-categories__item"));
        int actualSize = driver.findElements(By.cssSelector(".sidebar li.menu-categories__item")).size();
        Assert.assertEquals(actualSize, 17,
                String.format("Actual size %s does not equal expected ", actualSize));
    }

    @Test
    public void CatalogTest() {
        driver.findElement(By.id("fat-menu")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector(".header-catalog div.menu-wrapper")).isDisplayed(),
                "Menu is not displayed");
        int fatMenuSize = driver.findElements(By.cssSelector(".header-catalog li.menu-categories__item")).size();
        Assert.assertEquals(fatMenuSize, 17,
                String.format("Actual size %s does not equal to the expected %s", fatMenuSize, 17));
    }

    @Test
    public void CatalogTest2() {
        driver.findElement(By.id("fat-menu")).click();
        int hiddenMenuSize = driver.findElements(By.cssSelector("p.menu__hidden-title")).size();
        Assert.assertEquals(hiddenMenuSize, 15,
                String.format("Actual size %s does not equal to the expected %s", hiddenMenuSize, 15));
    }

    @Test
    public void searchCategory() {
        driver.findElement(By.cssSelector("[name='search']")).sendKeys("Планшеты");
        driver.findElement(By.cssSelector("button.search-form__submit")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(driver -> driver.findElement(By.cssSelector("li.breadcrumbs__item--text")));
        Assert.assertEquals(driver.findElement(By.cssSelector("li.breadcrumbs__item--text")).getText(), "Планшеты");
    }

    @AfterClass
    public void afterMethod(){
        driver.quit();
    }
}