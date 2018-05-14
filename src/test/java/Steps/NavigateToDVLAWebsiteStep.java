package Steps;

import Screens.DVLALandingPage;
import Screens.InputAndSearchVehicleRegistrationPage;
import Screens.VehicleRegistrationDetailPage;
import ServiceLayer.PrepareTestDataForVehicleRegistrationCheck;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.VehicleUnderTest;
import Support.UIHelper;
import utilities.PropertyReader;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class NavigateToDVLAWebsiteStep extends UIHelper {

    final static Logger logger = LoggerFactory.getLogger(NavigateToDVLAWebsiteStep.class);
    InitialiseSteps initialiseStepsForCurrentPage;
    WebDriver screenDriver;
    Actions clickActionBuilder;
    StringBuilder statusMessages;
    List<VehicleUnderTest> testData;
    DataTable testDataForCucumber;

@cucumber.api.java.After("@SmokeTests")
public void tearDown() throws InterruptedException {
    logger.info(" ==========================================>INSIDE TEARDOWN @SmokeTests");
    getScreenDriver().close();
    try {
        Runtime.getRuntime().exec("taskkill /F /IM MicrosoftWebDriver.exe");
        Thread.sleep(10000);
    } catch (IOException e) {
        e.printStackTrace();
    }

}
    public NavigateToDVLAWebsiteStep(InitialiseSteps initialiseSteps) throws IOException, InterruptedException {
        System.out.println("Inside NavigateToDVLAWebsiteStep");
        this.initialiseStepsForCurrentPage = initialiseSteps;
        setScreenDriver(InitialiseSteps.createNewWebDriverInstance());
       /// PrepareTestDataForVehicleRegistrationCheck prep = new PrepareTestDataForVehicleRegistrationCheck();
       /// this.testData = prep.extractAndCompileVehicleRegistrationData();
        ///for (VehicleUnderTest VUT : testData) {
         ///   logger.info(VUT.toString());
        ///}
        ///this.testDataForCucumber = DataTable.create(testData);

    }
    @Given("^the user navigates to DVLA landing page$")
    public void the_user_navigates_to_DVLA_landing_page() throws Throwable {

        logger.info("Inside NavigateToDVLAWebsiteStep Step");
        getScreenDriver().manage().window().maximize();
        getScreenDriver().get(new PropertyReader("/application.properties").readProperty("TargetWEBURL"));
        ( new DVLALandingPage(getScreenDriver())).the_user_is_on_landing_page();
        logger.info("Exiting NavigateToDVLAWebsiteStep Step");
    }

    @When("^input registration number \"([^\"]*)\"$")
    public void input_registration_number(String regNum) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ///throw new PendingException();
        logger.info("Inside  STEP : input_registration_number(regNum)" );
        (new InputAndSearchVehicleRegistrationPage(getScreenDriver())).input_registration_number(regNum);
        logger.info("Exiting STEP : input_registration_number(regNum)" );
    }

   //// @Then("^the user is displayed vehicle detail \"([^\"]*)\"$")
    @Then("^the user is displayed vehicle detail \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"$")
    public void the_user_is_displayed_vehicle_detail(String regNum, String make , String colour) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
      ///  throw new PendingException();
        logger.info("Inside  STEP : the_user_is_displayed_vehicle_detail(regNum)" );
        (new VehicleRegistrationDetailPage(getScreenDriver())).the_user_is_displayed_vehicle_detail(regNum,make,colour);
        logger.info("Exiting STEP : the_user_is_displayed_vehicle_detail(regNum)" );

    }
}