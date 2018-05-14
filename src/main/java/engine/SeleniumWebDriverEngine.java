package engine;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.PropertyReader;


public class SeleniumWebDriverEngine {
    final static Logger logger = LoggerFactory.getLogger(SeleniumWebDriverEngine.class);
    private static WebDriver webDriver;

    public static WebDriver createNewWebDriverInstance() throws InterruptedException {

        String browserName    = new PropertyReader("/application.properties").readProperty("browserName");
        String driverName     = new PropertyReader("/application.properties").readProperty(browserName);
        String driverLocation = new PropertyReader("/application.properties").readProperty(driverName);
        WebDriver tempWebDriver = null;
        switch (browserName.toUpperCase()) {
            case "IE":
                System.setProperty(driverName, driverLocation);
                DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
                capabilitiesIE.setCapability("nativeEvents", false);
                capabilitiesIE.setCapability("unexpectedAlertBehaviour", "accept");
                capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
                capabilitiesIE.setCapability("disable-popup-blocking", true);
                capabilitiesIE.setCapability("enablePersistentHover", true);
                capabilitiesIE.setCapability("requireWindowFocus", true);
                capabilitiesIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
                tempWebDriver = new InternetExplorerDriver();

                break;

            case "EDGE":
                System.setProperty(driverName, driverLocation);
                EdgeOptions options = new EdgeOptions();
                options.setCapability("requireWindowFocus", true);
                options.setCapability("nativeEvents", false);
                options.setPageLoadStrategy("eager");
                tempWebDriver = new EdgeDriver(options);

                break;

            default:
                /**TODO - Handle return for Default WebDriver /HTMLDriver
                 * Vikas Bhatia
                 */

                break;
        }
        return (WebDriver) tempWebDriver;
    }
}


