package qa.projects;


import org.junit.Test;
import qa.projects.basetest.BaseTestSelenium;
import java.io.IOException;

public class RozetkaPagesTests extends BaseTestSelenium {

    @Test
    public void searchCategory() throws IOException, InterruptedException {
        basePage.openPage();
        basePage.enterTextIntoElement(basePage.searchField,"Планшеты");
        Thread.sleep(1000);
        basePage.clickCatalogButton();
      //  Assert.assertEquals(driver.findElement(By.cssSelector("li.breadcrumbs__item--text")).getText(), "Планшеты");
    }

//    @Test
//    public void baseCatalogTest() {
//        catalogPage.openPage();
//        var categories = catalogPage.categories;
//        Assert.assertEquals(categories.size(), 17,
//                String.format("Actual size %s does not equal expected ", categories.size()));
//    }


}
