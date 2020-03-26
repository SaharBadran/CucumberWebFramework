package ObjectRepo;

import org.openqa.selenium.By;

public class IncidentLocators {

	public static By requestLocator = By.xpath("//input[@name='feedback']");
	
	public static By commentLocator = By.xpath("//div/textarea");
	
	public static By uploadButtonLocator = By.xpath("//feedback-form/div/div/vf-file-uploader/div/div/button");
	public static By fileUploadButtonLocator= By.id("fileUpload");
	
	public static By sendRequestButtonLocator = By.xpath("//feedback-form/div/div/div[3]/button");
	
	public static By successMessage = By.className("title");
	
	
	
}
