# Vodafone Automation Framework  

**This automation framework have the below functionality and integrations:**  
-------------------------------

. Wrapper functions for all elements actions handling all kind of waits and simplify
use of selenium  
. Functions for all java script actions  
. Functions for all browser actions  
. Excel and properties readers  
. WebDriver Singleton includes integration with WebDriver Manager and handling different browsers  
. CI/CD Integration Chrome Configurations (Handle headless execution by changing value of 
"Headless" in the config file)  
. Randomizer for generating different random patterns  
. ScreenshotUtil for taking screenshots, Added in the base test to automatically take screenshots
in case of failure  
**. Image Validator to make image comparison (Handled visual test for web automation)**  
. Integration with Extent report for useful visual reports with screenshots for every failure  
. Integrated with maven so the scripts can be run using mvn commands (A must for the CI/CD)  

-------------------------------------------------------------------------------
**Framework Architecture**
-------------------

Under main VodafoneFramework Folder  

**Packages:**  

**Base package:**  
. **BrowserType** class: Enum for all browser types  
. **Randomizer** class: Randomizer  that can generate different random patterns  
. **WebDriverSingleton** class: Web driver singleton class that integrates with WebDriver Manager 
handling different browsers and insure only one instance is made from WebDriver Object  

**Common package:**  
. **Paths** class: have for different paths that shall be used (TestData, Screenshots, VisualTest results,
Expected screens, etc...)  
.**Strings** class: have all static strings (static project under test messages, VisualTest images names
,etc...)  

**Utilities package:**  
.**actions** folder  
.**BrowserActions** class: have all browser actions related functions including JS  
.**ElementActions** class: have all element actions related functions including JS  

.**readers** folder  
.**ExcelReader** class: have different functions for reading from excel sheet  
.**PropertiesReader** class: have different functions for reading from config file  

.**ConfigUtil** class: Functions to load script configurations  
.**ImageValidator** class: Functions for image validations and comparison (Visual Test functions)  
.**ScreenshotUtil** class: Functions for taking screenshots for failures and visual test  

---------------------------------------------

**Folders**  

**Resources Folder**
.**config.properties** file: adding different configurations for the project under test and the script itself  

**test-actual-screenshots Folder**  
Have the actual captured screenshots during a visual test  

**test-expected-screenshots Folder**  
Add here your expected screens for the visual test  


**test-result-image-comparison Folder**  
Have the results of the compared images during a visual test  

**test-reports Folder**  
Have the generated extent report after test run  

**test-data Folder**
Add here all the test data needed for the project as excel sheets (Under test-resources folder)  
 
----------------------------------------------------------------------------
**Test Architecture For Project Under Test**  
-------------------------

The test folders should be added as follows: (Added a sample test for illustration)  
Under test folder you should create the following packages:  
**ObjectRepo Package**  
Create different static classes for project under test page locators(class per page)  

**PageObjects Package**  
Create page objects for the project under test according to POM design pattern  

**SampleNormalTests Package**  
.**BaseTest** class: use base test class as created but modify according to project needs.
It have browser setup, loading project configurations and extent report setup. It should be 
extended by every test class.  
.Create your test classes according to the project under test  

--------------------------------------------
**General**  
**RegressionSuite.xml file**
this file is the testng file to refer to your test classes in it (It's the file that mvn should
run)  

**POM.xml file**  
File that have mvn build configurations and all the dependencies used in the framework   

-------------------------------------------------------------------------------------
**Visual Test Instructions**
--------------------------

In order to use the visual test functionality, you have to do the below: 
. Add the expected screenshots in **test-expected-screenshots** folder  
. Call the **ScreenShotUtil** function for visual test to take the actual screenshot 
(The actual screenshot is saved after calling the function in **test-actual-screenshots** folder)  
. Use **ImageValidator** function to retrieve image comparison results (Result image will be saved in **test-results-image-comparison** folder)   
 
Note: You have to add screen names in Strings class to pass it as parameters for ScreenShotUtil and Image Validator functions 



