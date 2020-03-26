package Demo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import ObjectRepo.SearchPageLocators;
import SampleNormalTests.BaseTest;
import VodafoneFramework.utilities.actions.ElementActions;
import ObjectRepo.IncidentLocators;;


public class FunctionalTest extends BaseTest{

	 @Test
	    public void searchTest(){
		 
		
		 ElementActions.RadioButtonIndex(IncidentLocators.requestLocator,1);
		 
		 ElementActions.typeInTextField(IncidentLocators.commentLocator,"New Comment");
		 
         ElementActions.uploadFile(IncidentLocators.uploadButtonLocator,"C:\\German\\Service.xlsx");
	
		 ElementActions.clickOnElementByLocator(IncidentLocators.sendRequestButtonLocator);
	 }
}
