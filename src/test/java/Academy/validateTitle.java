package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;

import resources.base;

public class validateTitle extends base {

	
	public WebDriver driver;
	public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		log.info("Driver is initialized");
		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to Homepage");
	}

	@Test
	
	public void validateAppTitle() throws IOException 
	{
	
		//one is inheritance
		//creating object to thet class and invoke methods of it
		LandingPage l=new LandingPage(driver);
		//compare the text from the browser with actual text - Error
		Assert.assertEquals(l.getTitle().getText(), "FEATURED COURSES");
		//Assert.assertTrue(false);
		log.info("Successfully validated Text message");
	
	}
	
	
	
	@AfterTest
	
	public void teardown()
	{
		driver.quit();
	}
	
	

	
}
