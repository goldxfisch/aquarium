package Screens;

import Support.UIHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class InputAndSearchVehicleRegistrationPage extends UIHelper {
    final static Logger logger = LoggerFactory.getLogger(InputAndSearchVehicleRegistrationPage.class);

    @FindBy(linkText = "Start now")
    WebElement linkStartNow;

    @FindBy(className = "heading-large")
    WebElement LargeHeading;

    @FindBy(id = "Vrm")
    WebElement txtRegistrationNumber;

    @FindBy(name = "Continue")
    WebElement btnContinue;

    final static String strPageHeader = "Enter the registration number of the vehicle";
    WebDriverWait waitForElement;


    public InputAndSearchVehicleRegistrationPage(WebDriver driver) {
        setScreenDriver(driver);
        PageFactory.initElements(getScreenDriver(), this);
    }

    /////@When("^input registration number \"([^\"]*)\"$")
    public void input_registration_number(String regNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ///throw new PendingException();

        logger.info( " Inside DVLALandingPage : the_user_provides_vehicle_registration_number");
        waitForElement = shortWait(getScreenDriver(),15);
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading-large")));
        logger.info("LARGE HEADING <"+LargeHeading.getText()+">");
        if ( LargeHeading.getText().contains(strPageHeader))
        {
            txtRegistrationNumber.sendKeys(regNum);
            safeJavaScriptClick(btnContinue);
            waitForElement = shortWait(getScreenDriver(),10);
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading-large")));
            
            StringBuilder tempScreenShotName = new StringBuilder(regNum).append("_");
            tempScreenShotName.append(LocalDateTime.now());

            String screenName = tempScreenShotName.toString()
                    .replace(" ","")
                    .replace(":","")
                    .replace(".","");


            takeScreenShot(getScreenDriver(),screenName);


        }
        else
        {
            Assert.assertFalse( "INCORRECT PAGE",LargeHeading.getText().contains(strPageHeader));
        }
    }
}
