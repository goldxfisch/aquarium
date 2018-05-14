package Support;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UIHelper {
    final static Logger logger = LoggerFactory.getLogger(UIHelper.class);
    Actions clickActionBuilder;
    static String ScreenshotPath = "./out/screenshots/";
    WebDriver screenDriver;

    public WebDriver getScreenDriver() {
        return screenDriver;
    }

    public void setScreenDriver(WebDriver screenDriver) {
        this.screenDriver = screenDriver;
    }

    public Actions getClickActionBuilder() {
        return clickActionBuilder;
    }

    public void setClickActionBuilder(Actions clickActionBuilder) { this.clickActionBuilder = clickActionBuilder; }

    public void safeJavaScriptClick(WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                logger.debug("Clicking on element with using java script click  <" +element.getText()+">");
                logger.debug("Element Clicked     <"+element.isDisplayed()+ ">  <" +  element.isEnabled()+">");

                setClickActionBuilder( new Actions(getScreenDriver()));
                getClickActionBuilder().moveToElement(element).click(element).perform();

            } else {
                logger.debug("Unable to click on element because it is either disabled or not displayed");
            }
        } catch (StaleElementReferenceException e) {
            logger.error("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            logger.error("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            logger.error("Unable to click on element "+  e.getStackTrace());
        }
    }

    public WebDriverWait shortWait ( WebDriver pageDriver , long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(pageDriver, timeOutInSeconds);
        return wait;
    }

    public void takeScreenShot ( WebDriver pageDriver , String filename)
    {
        Shutterbug.shootPage(getScreenDriver(), ScrollStrategy.BOTH_DIRECTIONS).withName(filename).save();

    }


}
