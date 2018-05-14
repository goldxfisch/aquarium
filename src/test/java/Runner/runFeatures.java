package Runner;

import Screens.DVLALandingPage;
import Support.CreateVehicleRegistrationFromDVLAFeature;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Cucumber.class)


@CucumberOptions(
        features = "./src/test/java/features/",
        strict = false ,
        glue= "Steps",
        format = {
                "pretty",
                "html:target/report/report.html",
                "json:target/report/json/report.json"
        },
        tags = {"@SmokeTests"}
)
public class runFeatures {
        final static Logger logger = LoggerFactory.getLogger(runFeatures.class);
        @BeforeClass
        public static void runOnceBeforeClass() {
                logger.info("@BeforeClass - runOnceBeforeClass");

        }
        @AfterClass
        public static void runOnceAfterClass() {
                logger.info("@AfterClass - runOnceAfterClass");
        }

}
