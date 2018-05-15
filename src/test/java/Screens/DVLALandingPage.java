package Screens;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Support.UIHelper;

public class DVLALandingPage extends UIHelper {

    final static Logger logger = LoggerFactory.getLogger(DVLALandingPage.class);

    @FindBy(className = "pub-c-title__text")
    private WebElement PageHeader;

    @FindBy(linkText ="Start now")
    private WebElement btnStartNow;

    @FindBy (className = "heading-large")
    WebElement LargeHeading;

    //Get vehicle information from DVLA
    WebDriverWait waitForElement;
    final static String strPageHeader ="Get vehicle information from DVLA ";

    public DVLALandingPage(WebDriver driver) {
        setScreenDriver( driver);
        PageFactory.initElements(getScreenDriver(), this);
    }

    public void the_user_is_on_landing_page() throws Throwable {

        logger.info( " Inside DVLALandingPage : the_user_is_on_landing_page");
        waitForElement = shortWait(getScreenDriver(),5);
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.className("pub-c-title__text")));
///        Assert.assertTrue("LANDED AT CORRECT LANDING PAGE",PageHeader.getText().contains(strPageHeader));
        safeJavaScriptClick(btnStartNow);
        logger.info( " Exiting DVLALandingPage : the_user_is_on_landing_page");
    }

}
