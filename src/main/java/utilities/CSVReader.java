package utilities;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.VehicleUnderTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CSVReader {
    final static Logger logger = LoggerFactory.getLogger(CSVReader.class);
    @Test
    public List<VehicleUnderTest> extractDataFromCSVFile(String folderName) throws IOException {
        logger.info("CSV FILENAME "+folderName);
        List<VehicleUnderTest> listOfVehicleRegNum = new ArrayList<VehicleUnderTest>();
        ///  String folderName = "C:\\Users\\goldfish\\IdeaProjects\\showcase\\src\\main\\resources\\input2_csv.csv";
        File file = new File(folderName);
        List<String> csvLineList = new ArrayList();
        InputStream inputFS = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputFS));
        csvLineList = bufferedReader.lines().skip(1).collect(Collectors.toList()); /// Skipping Header
        bufferedReader.close();

        for (String csvLine : csvLineList) {
            String vehicleReg = "";
            String vehicleMake = "";
            String vehicleColour = "";
            StringTokenizer multiTokenizer = new StringTokenizer(csvLine, ",");
            String[] tokens = csvLine.split(",");
            int skipCsvColumns[] = {1};
            int skipper = 1;
            for (String token : tokens) {
                if (skipper == 2) {
                    vehicleReg = token;
                    logger.info("====>" + vehicleReg);
                }
                if (skipper == 3) {
                    vehicleMake = token;
                    logger.info("====>" + vehicleMake);
                }
                if (skipper == 4) {
                    vehicleColour = token;
                    logger.info("====>" + vehicleColour);
                }

            skipper++;
        }
            if (! vehicleReg.equalsIgnoreCase("")) {
                VehicleUnderTest VUT = new VehicleUnderTest(vehicleReg, vehicleMake, vehicleColour);
                listOfVehicleRegNum.add(VUT);
            }
        }
        return listOfVehicleRegNum;
    }
}