package engine;


import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.PropertyReader;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WebDriverEngine {
    final static Logger logger = LoggerFactory.getLogger(WebDriverEngine.class);

    public WebDriver getTempWebDriver() {
        return tempWebDriver;
    }

    public void setTempWebDriver(WebDriver tempWebDriver) {
        this.tempWebDriver = tempWebDriver;
    }

    public Actions getClickActionBuilder() {
        return clickActionBuilder;
    }

    public void setClickActionBuilder(Actions clickActionBuilder) {
        this.clickActionBuilder = clickActionBuilder;
    }

    WebDriver tempWebDriver;
    Actions clickActionBuilder;


    ///  public static WebDriver createNEwWebDriverInstance() throws URIException , NullPointerException
    ///public static void main(String...cmdArgs) throws Exception {

    @Test
    public void testClickJS() throws Exception {
        String browserName = new PropertyReader("/application.properties").readProperty("browserName");
        String driverName = new PropertyReader("/application.properties").readProperty(browserName);
        String driverLocation = new PropertyReader("/application.properties").readProperty(driverName);
        /// WebDriver tempWebDriver =null;
        switch ( browserName.toUpperCase())
        {
            case "IE" :
                System.setProperty(driverName,driverLocation);
                DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
                capabilitiesIE.setCapability("nativeEvents", false);
                capabilitiesIE.setCapability("unexpectedAlertBehaviour", "accept");
                capabilitiesIE.setCapability("ignoreProtectedModeSettings", true);
                capabilitiesIE.setCapability("disable-popup-blocking", true);
                capabilitiesIE.setCapability("enablePersistentHover", true);
                capabilitiesIE.setCapability("requireWindowFocus",true);
                capabilitiesIE.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,false);
                setTempWebDriver( new InternetExplorerDriver());
                getTempWebDriver().manage().window().maximize();

                getTempWebDriver().get(new PropertyReader("/application.properties").readProperty("TargetWEBURL"));

                getTempWebDriver().manage().timeouts().implicitlyWait(2,SECONDS);

                if (tempWebDriver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) tempWebDriver)
                            .executeScript("alert('hello world');");
                }
                tempWebDriver.manage().timeouts().implicitlyWait(10,SECONDS);
                ((JavascriptExecutor) tempWebDriver).executeScript("document.getElementsByClassName('pub-c-button pub-c-button--start')[0].click();");
                tempWebDriver.manage().timeouts().implicitlyWait(30,SECONDS);
                tempWebDriver.close();
                break;

            case "EDGE":
                System.setProperty(driverName,driverLocation);
                EdgeOptions options = new EdgeOptions();
                options.setCapability("requireWindowFocus",true);
                options.setCapability("nativeEvents", false);
                options.setPageLoadStrategy("eager");
                setTempWebDriver(new EdgeDriver(options));


                getTempWebDriver().manage().window().maximize();
                getTempWebDriver().manage().timeouts().pageLoadTimeout(5, SECONDS);
                String url = new PropertyReader("/application.properties").readProperty("TargetWEBURL");
                getTempWebDriver().navigate().to(url);

                WebElement goButton = ((EdgeDriver) getTempWebDriver()).findElementByLinkText("Start now");
                safeJavaScriptClick(goButton);


                Thread.sleep(5000);
                getTempWebDriver().manage().timeouts().implicitlyWait(10,SECONDS);

                WebElement txtBox = ((EdgeDriver) getTempWebDriver()).findElementById("Vrm");

                txtBox.sendKeys("D0");

                WebElement searchButton = ((EdgeDriver) getTempWebDriver()).findElementByName("Continue");
                safeJavaScriptClick(searchButton);

                Thread.sleep(500);
                getTempWebDriver().manage().timeouts().implicitlyWait(10,SECONDS);

                WebElement LargeHeading = ((EdgeDriver) getTempWebDriver()).findElementByClassName("heading-large");

                if ( LargeHeading.getText().contains("Is this the vehicle you are looking for?"))
                {
                    WebElement Summary = ((EdgeDriver) getTempWebDriver()).findElementByClassName("list-summary margin-bottom-2");
                    List<WebElement> SummaryElements = Summary.findElements(By.tagName("li"));
                    for (WebElement summaryElement : SummaryElements) {
                        System.out.println(summaryElement.getText());
                    }
                }

                if ( LargeHeading.getText().contains("Enter the registration number of the vehicle"))
                {
                    System.out.println("Inside Error 1");
                    List <WebElement> ErrorSummaryTable= ((EdgeDriver) getTempWebDriver()).findElementsByClassName("error-summary");
                    for (  WebElement ErrorSummaryElement :  ErrorSummaryTable)
                    {
                        System.out.println("Inside Error 2"+ErrorSummaryElement.getText() +    ErrorSummaryElement.getTagName());
                        if (ErrorSummaryElement.getTagName().contains("error-summary validation-summary-errors")){

                            System.out.println("Inside Error 3");
                            List<WebElement> ErrorElements = ErrorSummaryElement.findElements(By.tagName("ul"));
                            for (WebElement errorElement : ErrorElements) {
                                System.out.println(errorElement.getText());
                            }
                        }
                    }
                }

                if ( LargeHeading.getText().contains("Vehicle details could not be found"))
                {
                    List<WebElement> AdvisorySection = ((EdgeDriver) getTempWebDriver()).findElementsByClassName("column-two-thirds");

                    for ( WebElement AdvisoryElements : AdvisorySection)
                    {
                        System.out.println(AdvisoryElements.getText());
                    }

                }

                getTempWebDriver().close();
                break ;
            default :
                break;
        }
    }

    public void safeJavaScriptClick(WebElement element) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click  <" +element.getText()+">");
                System.out.println("Element Clicked     <"+element.isDisplayed()+ ">  <" +  element.isEnabled()+">");

                setClickActionBuilder( new Actions(getTempWebDriver()));
                getClickActionBuilder().moveToElement(element).click(element).perform();

            } else {
                System.out.println("Unable to click on element because it is either disabled or not displayed");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on elementssss "+e.getMessage() + e.getCause() + e.getStackTrace());
        }
    }


    public By locatorValue(String locatorType , String screenElementName)
    {
        By domElement ;
        switch (locatorType)
        {
            case "id":
                domElement= By.id(screenElementName);
                break;
            case "name":
                domElement = By.name(screenElementName);
                break;

            case "xpath":
                domElement= By.xpath(screenElementName);
                break;
            case "css":
                domElement = By.cssSelector(screenElementName);
                break;
            case "linkText":
                domElement = By.linkText(screenElementName);
                break;

            case "partialLinkText":
                domElement = By.partialLinkText(screenElementName);
                break;

            default:
                domElement = null;
                break;

        }
        return domElement;
    }
}

