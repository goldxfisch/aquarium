<b>Aquarium</b> is divided into two parts

1. A <b>SERVICE LAYER</b> that performs following function
--------------------------------
  - Scan a configured directory in file system which will return following information
     . File Name
     . File Mime Type
     . File Size 
     . File Extension
   - It also recursively traverses into sub directories and list out of the files.
   
2. A <b>Selenium / Cucumber framework</b> that performs following function
--------------------------------------
  - Using the Service Layer(mentioned above) extracts the information from the CSV and EXCEL files of a specified directory
  - Read Vehicle Registration and other details
  - Navigates to DVLA Website 
  - Asserts the Vehicle Details(Make / Colour) with the expected output mentioned in above files
  
  Following are the core libraries used
  ----------------------------------------
  - Apache POI - For Reading XLSX / XLS files
  - Apache Freemarker - For 
  - BDD Behaviour using Cucumber 
  - Selenium WebDriver
  - JUnit and Hamcrest 
  - SL4J
  - Java 8
  
  How to Use It
  -----------
  application.properties -
    - TestDataPath : holds information pertaining to Source Folder that holds CSV / EXCEL files
    - browserName : browser name used for testing
    - IE/EDGE : Location to Selenium WebDrivers for IE and EDGE
    - Ftlpath : Location to FreeMarker Templates to maintain Cucumber Feature
    - FeaturePath : Location to Features that are to be maintained by FreeMarker
  
  ScanDirectory : Is a Unit test pertaining to FIle Details: FIle Name , Size , MimeType , EXtension 
  
  <u>CreateVehicleRegistrationFromDVLAFeature</u>
    - Code to filter  and read the CSV/EXCEL MimeType files from TestDataPath( application.properties)into FileDetails(POJO)
    - Extract Vehicle Registration , Make , Colour into a VehicleUnderTest(POJO)
    - USe the FreeMarker Template to update markers with the Test Data into Cucumber Feature File
    - Log the process either on console or file
    
    
  VehicleRegistrationCheckFeatureBuilder.ftl - Freemarker Template that provides Examples to Scenario Outline
  
  VehicleRegCheck.feature
  It starts with only one tag @SmokeTests , but once CreateVehicleRegistrationFromDVLAFeature is trigger it will fill in the feature files with required FEature , sSCenario Outline and Examples for execution
  
  RunMe
  Is a JUnit program that first triggers CreateVehicleRegistrationFromDVLAFeature and then triggers JUnit Cucumber Runner class to process feature 
  
  Sample Screenshots of the Run are parked at 
  \screenshots
  
