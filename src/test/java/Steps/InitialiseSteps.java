package Steps;

import cucumber.api.DataTable;
import engine.SeleniumWebDriverEngine;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.PropertyReader;

public class InitialiseSteps extends SeleniumWebDriverEngine{
    final static Logger logger = LoggerFactory.getLogger(InitialiseSteps.class);

    public SeleniumWebDriverEngine getWebEngine() {
        return webEngine;
    }

    public void setWebEngine(SeleniumWebDriverEngine webEngine) {
        this.webEngine = webEngine;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private SeleniumWebDriverEngine webEngine ;
    private WebDriver webDriver;


    public void InitialiseStepExecution() throws InterruptedException {
        if ( getWebDriver().equals(null ) )setWebDriver(createNewWebDriverInstance());
    }

    public DataTable createDataTableForVerification(){

        String testDataPath    = new PropertyReader("/application.properties").readProperty("TestDataPath");


        return null;

    }
}
