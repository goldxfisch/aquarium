package Runner;

import Support.CreateVehicleRegistrationFromDVLAFeature;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunMe {
    final static Logger logger = LoggerFactory.getLogger(runFeatures.class);
    public static void main(String... cmdArgs)
    {
        CreateVehicleRegistrationFromDVLAFeature createVehicleRegistrationFromDVLAFeature = new CreateVehicleRegistrationFromDVLAFeature();
        try {
            createVehicleRegistrationFromDVLAFeature.generateVehicleRegistrationFeature();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JUnitCore junit = new JUnitCore();
        Result result = junit.run(runFeatures.class);

        logger.info("=============TEST SUMMARY ============");
        logger.info("RESULT = "+Boolean.toString(result.wasSuccessful()));
        logger.info("RUNTIME = "+Long.toString(result.getRunTime()));
        logger.info("RUN COUNT = "+Integer.toString(result.getRunCount()));
       /// logger.info(result.getFailures());
        logger.info("FAILURES = "+Integer.toString(result.getFailureCount()));
        logger.info("IGNORED = "+result.getIgnoreCount());

        if ( result.getFailureCount() > 0)
        {
            for (Failure failure : result.getFailures())
            {
                logger.info ( " TEST HEADER "+ failure.getTestHeader());
                logger.info ( " DESCRIPTION "+ failure.getDescription());
                logger.info ( " MESSAGE     "+ failure.getMessage());
            }
        }
        logger.info("FAILURE "+result.getFailures());

        logger.info("=============TEST SUMMARY ============");

    }
}
