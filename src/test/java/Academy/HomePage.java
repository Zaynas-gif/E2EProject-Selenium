package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base {
	
	public WebDriver driver;
	
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
		driver =initializeDriver();
		
	
	}

	

	@Test(dataProvider="GetData")
	
	public void basePageNavigation(String Username, String Password, String text) throws IOException 
	{
		//one is inheritance
		//creating object to thet class and invoke methods of it
		driver.get(prop.getProperty("url"));
		LandingPage l=new LandingPage(driver);
		 LoginPage lp=l.getLogin();
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);
		System.out.print(text);
	
		log.info("User Connected");
		
		
		lp.getSubmit().click();
		ForgotPassword fp = lp.forgotPassword();
		fp.getEmail().sendKeys("xxx");
		fp.sendMeInstructions().click();
	
	}

	@AfterTest
	
	public void teardown()
	{
		driver.quit();
	}
	
	

	
	@DataProvider
	public Object[][] GetData( ) 
	{
		//row stands for how many different data types test should run
		//coulumn stand for how many per each test
		Object[][] data=new Object[2][3];
		//0th row 
		data[0][0]="nonrestricteduserbaskj.com";
		data[0][1]="123123";
		data[0][2]="Restricted User";
		//1st row
		data[1][0]="restricteduser@laksd.com";
		data[1][1]="4345345";
		data[1][2]="Non restricted user"; 
		
		return data;
 	}
	
	


	
	
		
	
}
