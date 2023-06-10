package qa.projects.pages.pages;

import dev.failsafe.internal.util.Assert;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.projects.libs.ConfigProperties;

import java.time.Duration;

public abstract class ParentPageSelenium {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.BASE_URL();
    Logger logger = Logger.getLogger(getClass());

    public ParentPageSelenium (WebDriver driver) {
        this.driver = driver;

        webDriverWait10 = new WebDriverWait(driver,Duration.ofMillis(10000));
        webDriverWait15 = new WebDriverWait(driver,Duration.ofMillis(15000));
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof WebElement) {
            elementName = (" '" + ( webElement.getTagName() + "' "));
        }
        return elementName;
    }

    public void enterTextIntoElement(WebElement webElement, String text) {
        try {
            ;
            webElement.clear();
            webElement.sendKeys(text);
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }

    }

    public void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    private void printErrorMessageAndStopTest(Exception e) {
        System.out.printf("Can not work with element " + e);
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            return state;
        } catch (Exception e) {
            return false;
        }
    }


    protected void isElementVisible(WebElement webElement) {
        Assert.isTrue(isElementDisplayed(webElement),
                getElementName(webElement) + " is not visible" );
    }



//    protected void selectTextInDropdown(WebElement webElement, String text) {
//        try {
//            Select select = new Select(webElement);
//            select.selectByVisibleText(text);
//        } catch (Exception e) {
//            printErrorMessageAndStopTest(e);
//        }
//    }
}
