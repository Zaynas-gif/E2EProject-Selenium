package Academy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

import resources.base;

public class Cart extends base {
	
	public static Logger log =LogManager.getLogger(base.class.getName());
	@Test
	public void addToCart() throws IOException, InterruptedException
	{
		
		driver =initializeDriver();
		
		
		 driver.get(prop.getProperty("url2"));
		// TODO Auto-generated method stub
			
			//Implicitly wait can wait max 5 seconds before showing the error because we just  declared for max 5 seconds but we can change time when ever we want
			// if object display in more then 5 seconds error will be displayed in console but if it less it will pass
		
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebDriverWait w =new WebDriverWait(driver,5);

			//expected
			//giving variable name and assign value to it, don't forget what a write type Like String
			//Arrey of strings
			String[] itemsNeeded= {"Cucumber","Tomato", "Carrot", "Pumpkin"};
			//adding sleep time to make sure every products are loaded into website before looping them
			log.info("Items has been selected");
			//String name= "Cucumber";
		

			Thread.sleep(3000);
			addItems(driver, itemsNeeded);
			driver.findElement(By.cssSelector("img[alt='Cart']")).click();
			//below whole code is called WebElement but By.xpath and the rest called locator
			driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
			driver.findElement(By.cssSelector("input.promoCode")).sendKeys("addcupon");
			driver.findElement(By.cssSelector("button[class='promoBtn']")).click();
			//explicit wait 
			
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));
			System.out.println(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText());
			
			driver.findElement(By.xpath("//button[normalize-space()='Place Order']")).click();
			log.info("Order has been placed");
			
			
			
			WebElement staticDropdown = driver.findElement(By.xpath("//div[@class='wrapperTwo']//div//select"));
			Select dropdown =new Select(staticDropdown);
			dropdown.selectByVisibleText("Sweden");
			
			
			WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
			if(!checkbox.isSelected())
				checkbox.click();
			
			driver.findElement(By.xpath("//button[normalize-space()='Proceed']")).click();
			
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='products']")));
			System.out.println("Order has completed and redirected to main page");
			
			
			driver.quit();			
			
		
	}
	
	
  
	
	
	//creating the method outside main method
	public static void addItems(WebDriver driver, String[] itemsNeeded)
	{
		int j=0;
		List <WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
		//loop starts from zero and loops until size of these products
		//so if size 30 its loops 30 times one by one
		//basicly we want every time get one index out of 30 and then get text
		//Pulling out each value present in index
		 for(int i=0; i<products.size();i++)
		 {
			 //when loops runs for the first time i=0 so we extracting zero items and next item will become 1 and increases by1
			 // so this loop will run 30 times and everytime it will upgrade index and every product you will get
			 //gettext willl help you get inforamtion about current product
			 //Brocolli - 1 Kg and it splits Like Brocolli and 1 Kg into 2 indexes 
			 //extrated 0 items of the 30 items when we puling text value got stored in the "name"
		String[] name=products.get(i).getText().split("-");
		String formattedName= name[0].trim();

		//as soon as i get name i have to foramt it to get actual vegetable name and pass it to
		//loop will repeat until it finds a product name by Cucumber and executes code below
		//check whether name you expected is present in arrayList or not
		//convert array into array list for easy search
		List<String> itemsNeededList = Arrays.asList(itemsNeeded);
		
		
		if(itemsNeededList.contains(formattedName)) {
			
			j++;
			//click add to cart
			driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
			//adding time out for 5  seconds to make sure when items is added to wait until script
			//can read indexes from 0 again but if we try out without sleep time if we add first item
			//loop starts from 0 again and do not read first one because name has changed from "ADD TO CART" to "ADDED"
			//Thread.sleep(5000);
			//but the right way is instead of get text we get a parent class static xpath that never changes
			//3
			if(j==itemsNeeded.length)
				// we use lenght to get size of array 
				//and we use size() to get arraylist
			{
				break;
			}
			//you can't use break if you have multiple values because loops breaks and stops without checking others products
			//break;
			
			
		}
		 
		}
	}
}