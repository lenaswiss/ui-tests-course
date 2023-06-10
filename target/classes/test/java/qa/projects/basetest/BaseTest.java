package qa.projects.basetest;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import qa.projects.pages.BasePage;
import qa.projects.pages.CatalogPage;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static qa.projects.pages.ParentPage.configProperties;

public class BaseTest {

    public WebDriver driver;
    public BasePage basePage;
    public CatalogPage catalogPage;
    protected Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws Exception {
        driver = initDriver();
        logger.info("Browser was opened");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        basePage = new BasePage(driver);
        catalogPage = new CatalogPage(driver);
    }

    private WebDriver initDriver() throws Exception {
        File file = new File("/Users/lena/Documents/Java/ui-test/drivers/geckodriver");
        System.setProperty("webdriver.geckodriver.driver", file.getAbsolutePath());
        driver = new FirefoxDriver();
        return driver;
    }

    @After
    public void tireDown() {
        driver.quit();
        logger.info("Browser was closed");
    }

    protected void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertTrue(message, actualResult);
    }

//    @Rule
//    public TestWatcher watchman = new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            screenshot();
//        }
//        @Attachment(value = "Page screenshot", type = "image/png")
//        public byte[] saveScreenshot(byte[] screenShot) {
//            return screenShot;
//        }
//        public void screenshot() {
//            if (driver == null) {
//                logger.info("Driver for screenshot not found");
//                return;
//            }
//            saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
//        }
//        @Override
//        protected void finished(Description description) {
//            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
//            try {
//                driver.quit();
//                logger.info("Browser was closed");
//            } catch (Exception e) {
//                logger.error(e);
//            }
//        }
//    };
//
//    protected void checkExpectedResult(String message, boolean actualResult){
//        Assert.assertTrue(message, actualResult);
//    }


}
