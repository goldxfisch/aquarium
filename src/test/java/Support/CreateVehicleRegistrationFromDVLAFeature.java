package Support;

import ServiceLayer.PrepareTestDataForVehicleRegistrationCheck;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.VehicleUnderTest;
import utilities.PropertyReader;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CreateVehicleRegistrationFromDVLAFeature {
    final static Logger logger = LoggerFactory.getLogger(CreateVehicleRegistrationFromDVLAFeature.class);

    List<VehicleUnderTest> testData;

    public CreateVehicleRegistrationFromDVLAFeature() {
    }
    @Test
    public void generateVehicleRegistrationFeature() throws Exception {

        //1 Configure FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

        FileTemplateLoader vehicleFeature = new FileTemplateLoader(new File(new PropertyReader("/application.properties").readProperty("FtlPath")));

        MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[]{vehicleFeature});

        cfg.setTemplateLoader(mtl);

        cfg.setDefaultEncoding("UTF-8");

        cfg.setLocale(Locale.ENGLISH);

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashedMap<>();
        StringBuilder messageForBlock1 = new StringBuilder();
        PrepareTestDataForVehicleRegistrationCheck prep = new PrepareTestDataForVehicleRegistrationCheck();
        this.testData = prep.extractAndCompileVehicleRegistrationData();
        for (VehicleUnderTest VUT : testData) {
            logger.info(VUT.toString());

            messageForBlock1.append("| ")
                    .append(VUT.getVehicleRegistrationNumber());
            messageForBlock1.append("| ")
                    .append(VUT.getVehicleMake());
            messageForBlock1.append("| ")
                    .append(VUT.getVehicleColour())
                    .append(" |").append( System.getProperty("line.separator"));
        }


        input.put("block1", messageForBlock1);
        Template template = cfg.getTemplate("VehicleRegistrationCheckFeatureBuilder.ftl");

        Writer consoleWriter = new OutputStreamWriter(System.out);

        template.process(input, consoleWriter);
        String tempFileGenName= new StringBuilder()
                .append(new PropertyReader("/application.properties").readProperty("FeaturePath"))
                .append(System.getProperty("file.separator"))
                .append("VehicleRegCheck.feature").toString();

        Writer fileWriter = new FileWriter(new File(tempFileGenName));

        try
        {
            template.process(input,fileWriter);
        }
        finally {
            fileWriter.close();
        }
    }
}

