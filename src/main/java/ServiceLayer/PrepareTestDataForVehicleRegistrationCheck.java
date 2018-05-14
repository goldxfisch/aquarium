package ServiceLayer;

import cucumber.api.DataTable;
import engine.WebDriverEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.FileDetails;
import pojo.VehicleUnderTest;
import utilities.CSVReader;
import utilities.ExcelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrepareTestDataForVehicleRegistrationCheck {
    final static Logger logger = LoggerFactory.getLogger(WebDriverEngine.class);

    public List<VehicleUnderTest> extractAndCompileVehicleRegistrationData() throws IOException {
        ScanDirectory scanDirectory = new ScanDirectory();
        List<VehicleUnderTest> compiledListOfVehicles = new ArrayList<VehicleUnderTest>();
        List<FileDetails> listOfFiles = scanDirectory.crawlDirectory();
        List<VehicleUnderTest> VUTE =new ArrayList<VehicleUnderTest>();
        List<VehicleUnderTest> VUTC= new ArrayList<VehicleUnderTest>();
        for (FileDetails fileDetails : listOfFiles)
        {
            switch  (fileDetails.getFileMimeType()) {
                case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                    ExcelReader excelReader = new ExcelReader();
                    VUTE = excelReader.readExcelFile( new StringBuilder(fileDetails.getDirectoryName()).toString());
                    logger.info("@@@@@@@@@@@@@@@@@@@@"+VUTE.toString());
                    compiledListOfVehicles.addAll(VUTE);
                    break;

                case "application/vnd.ms-excel":
                    CSVReader csvReader = new CSVReader();
                    VUTC = csvReader.extractDataFromCSVFile( new StringBuilder(fileDetails.getDirectoryName()).toString());
                    logger.info("^^^^^^^^^^^^^^^^^^^66"+VUTC.toString());
                    compiledListOfVehicles.addAll(VUTC);
                    break;

                default:
                    break;
            }
        }
        return compiledListOfVehicles;

    }
    public static void main (String ...cmdArgs) throws IOException {
        PrepareTestDataForVehicleRegistrationCheck prep = new PrepareTestDataForVehicleRegistrationCheck();
        List<VehicleUnderTest> testData = prep.extractAndCompileVehicleRegistrationData();
        for ( VehicleUnderTest VUT : testData)
        {
            logger.info(VUT.toString());
        }
        DataTable testDataForCucumber=DataTable.create(testData);


    }
}
