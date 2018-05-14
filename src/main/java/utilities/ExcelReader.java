package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ServiceLayer.ScanDirectory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.VehicleUnderTest;

public class ExcelReader {
    final static Logger logger = LoggerFactory.getLogger(ExcelReader.class);
    private static int CurrentRow = -1;
    private static XSSFWorkbook excelWorkBook;
    private static XSSFSheet excelWorkSheet;
    private static XSSFCell excelCell;
    private static XSSFRow excelRow ;

    private static  String folderName = "C:\\Users\\goldfish\\IdeaProjects\\aquarium\\src\\main\\resources\\test2.xlsx";

    /// public void readExcelFile(String folderName) throws IOException{
    @Test
    public List<VehicleUnderTest>  readExcelFile(String folderName) throws IOException{

        List<VehicleUnderTest> listOfVehicleRegNum = new ArrayList<VehicleUnderTest>();

        FileInputStream excelFile = new FileInputStream(folderName);
        excelWorkBook = new XSSFWorkbook( excelFile);
        logger.info("FILE NAME"+folderName);
        for ( int i =0 ; i < excelWorkBook.getNumberOfSheets(); i++) {
            excelWorkSheet = excelWorkBook.getSheetAt(i);
            logger.info("NAME OF WORKSHEET "+excelWorkSheet.getSheetName());
            ArrayList<String> listofColumnHeader = new ArrayList<>();

            for (Row row : excelWorkSheet) {
                VehicleUnderTest VUT;
                int iC = 0;
                String vehicleRegNum="";
                String vehicleMake="";
                String vehicleColour="";
                for (iC = row.getFirstCellNum(); iC < row.getLastCellNum(); iC++) {

                    Cell tempCell = row.getCell(iC, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (tempCell == null) {
                        logger.info("");
                    } else {
                        CellType cellType = tempCell.getCellTypeEnum();
                        switch (cellType) {
                            case STRING:

                                logger.info(tempCell.getStringCellValue() + "\t");
                                if ( ( iC==1) && (row.getRowNum() != 0))
                                    vehicleRegNum =tempCell.getStringCellValue();
                                if ( ( iC==2) && (row.getRowNum() != 0))
                                    vehicleMake =tempCell.getStringCellValue();
                                if ( ( iC==3) && (row.getRowNum() != 0))
                                    vehicleColour =tempCell.getStringCellValue();



                                ////  listOfVehicleRegNum.add(new VehicleUnderTest(tempCell.getStringCellValue(),"",""));
                                break;
                            case NUMERIC:
                                /// Cover for Date -- Pass info if this is actual date or just other numeric
                                boolean checkDate = false;
                                if (checkDate == true) {
                                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    logger.info(df.format(tempCell.getDateCellValue()) + "\t");
                                    break;
                                } else {
                                    logger.info((int) tempCell.getNumericCellValue() + "\t");
                                    break;
                                }

                            default:
                                break;
                        }
                    }

                }
                if (! vehicleRegNum.equalsIgnoreCase("") )
                    listOfVehicleRegNum.add(new VehicleUnderTest(vehicleRegNum,vehicleMake,vehicleColour));
                logger.info("");
            }
        }
        excelFile.close();
        return listOfVehicleRegNum;
    }

    public static void main ( String...cmdArgs)  {}

}
