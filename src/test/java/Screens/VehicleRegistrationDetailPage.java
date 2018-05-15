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
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class VehicleRegistrationDetailPage extends UIHelper {
    final static Logger logger = LoggerFactory.getLogger(VehicleRegistrationDetailPage.class);

    @FindBy(linkText = "Start now")
    WebElement linkStartNow;

    @FindBy(className = "heading-large")
    WebElement LargeHeading;

    @FindBy(className = "error-summary")
    List<WebElement> ErrorSummaryTable;

    @FindBy(className = "column-two-thirds")
    List<WebElement> AdvisorySection;

    final static String strPageHeader = "Enter the registration number of the vehicle";
    final static String strConfirmationPageHeader = "Is this the vehicle you are looking for?";
    final static String strErrorMessage = "You must enter your registration number in a valid format";
    ///final static String str
    WebDriverWait waitForElement;
    ///final static String strPageHeader ="Get vehicle information from DVLA ";

    public VehicleRegistrationDetailPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        setScreenDriver(driver);
    }

    ////@Then("^the user is displayed vehicle detail$")
    public void the_user_is_displayed_vehicle_detail(String regNum, String make , String colour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ///throw new PendingException();
        logger.info( " Inside VehicleRegistrationDetailPage : the_user_is_displayed_vehicle_details");

        waitForElement = shortWait(getScreenDriver(),15);
        waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.className("heading-large")));



  ////      waitForElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("heading-large")));

///        waitForElement.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(LargeHeading)));
        logger.info("LARGE HEADING <"+LargeHeading.getText()+">");

        String testCaseResult = "PASS";
        boolean flagIssue1 = false;
        boolean flagIssue2 = false;
        boolean flagIssue3 = false;
        if ( LargeHeading.getText().contains(strConfirmationPageHeader))
        {
            logger.info("Inside Error --- Is this the vehicle you are looking for?");
            WebElement Summary = (getScreenDriver()).findElement(By.className("list-summary margin-bottom-2"));
            List<WebElement> SummaryElements = Summary.findElements(By.tagName("li"));

            String actualRegNum="";
            String actualMake = "";
            String actualColour="";


            if ( SummaryElements.get(1).getText().contains(make))
                logger.info("FOUND MAKE "+make);
            else
            {   logger.info("FOUND MAKE MISMATCHED "+make); flagIssue2=true;}
            if ( SummaryElements.get(2).getText().contains(colour))
                logger.info("FOUND COLOUR "+colour);
            else
            {  logger.info("FOUND COLOUR MISMATCHED "+make); flagIssue3=true;}

            if ( flagIssue2 )
            logger.info("VEHICLE REGISTRATION NUMBER <"+regNum
                    + "> COLOUR MISMATCHED  FOUND <"
                    + ">   EXPECTED <"+colour+">");

            if ( flagIssue3)
                logger.info("VEHICLE REGISTRATION NUMBER <"+regNum
                        + "> MAKE MISMATCHED  FOUND <"
                        + ">   EXPECTED <"+make+">", flagIssue2 );
            assertThat( SummaryElements.get(2).getText(),containsString(colour) );
            assertThat( SummaryElements.get(1).getText(),containsString(make) );


        }
        waitForElement = shortWait(getScreenDriver(),15);
        //////waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading-large")));



        waitForElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("heading-large")));
        if ( LargeHeading.getText().contains(strPageHeader))
        {
            logger.info("Inside Error Scenario--- Enter the registration number of the vehicle");
            waitForElement = shortWait(getScreenDriver(),15);
            ///waitForElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LargeHeading));
           /// waitForElement.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(ErrorSummaryTable)));
            for (  WebElement ErrorSummaryElement :  ErrorSummaryTable)
            {
                logger.info("Inside Error Scenario 2 :<"+ErrorSummaryElement.getText() +">");
                if (ErrorSummaryElement.getTagName().contains("error-summary validation-summary-errors")){

                    List<WebElement> ErrorElements = ErrorSummaryElement.findElements(By.tagName("ul"));
                    for (WebElement errorElement : ErrorElements)
                    { logger.info(errorElement.getText());
                    }
                 }
            }

        }

        waitForElement = shortWait(getScreenDriver(),15);
        waitForElement.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("heading-large")));


        if ( LargeHeading.getText().contains("Vehicle details could not be found"))
        {
            logger.info("Inside Error Scenario 3--- Vehicle details could not be found");

            waitForElement = shortWait(getScreenDriver(),5);
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.className("column-two-thirds")));


            for ( WebElement AdvisoryElements : AdvisorySection)
            {
                logger.info(AdvisoryElements.getText());
            }
        }


        StringBuilder tempScreenShotName= new StringBuilder(regNum).append("_").append(LocalDateTime.now());
        String screenName = tempScreenShotName.toString()
                .replace(" ","")
                .replace(":","")
                .replace(".","");

        takeScreenShot(getScreenDriver(),screenName);

        logger.info( " Exiting VehicleRegistrationDetailPage : the_user_is_displayed_vehicle_details");

    }
}