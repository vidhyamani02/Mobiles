package mobiles.org;


import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class mobiles {
	
	static WebDriver driver;
	static long startTime;
	
	@BeforeClass
	public static void launch(){
	WebDriverManager.chromedriver().setup();
	driver =new ChromeDriver();
	driver.get("http://www.flipkart.com/");
	driver.manage().window().maximize();
	}
	
	@Before
	public void beforeMethod(){

	System.out.println("Before Method");
	startTime = System.currentTimeMillis();
	}

	
	
	@Test
	public void mobilePurchase(){
	try {
	WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
	button.isDisplayed();
	button.click();
	} 
	
	catch (Exception e){
	System.out.println("pop up is not displayed");
	}
	
	WebElement search = driver.findElement(By.name("q"));
	search.sendKeys("Mobiles",Keys.ENTER);
	WebElement mobileName = driver.findElement(By.xpath("//div[contains(text(),'realme')])[2]"));
	mobileName.click();
	}
	
	@Test 
	public void windowsHandling(){
	 
	String parentURL = driver.getWindowHandle();
	Set<String> childURL = driver.getWindowHandles();
	for (String child : childURL){

	if(!parentURL.equals(child)){
	driver.switchTo().window(child);
	    }
	   }
  }
	@Test 
	public void scrollDown(){
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}

	@After
	public void afterMethod(){

	long endTime = System.currentTimeMillis();

	long ty = startTime - endTime;

	System.out.println("After Method");
	System.out.println("Time taken is :"+ ty);
	}
	
	@AfterClass
	public static void closedBrowser() throws IOException{

	TakesScreenshot tk = (TakesScreenshot)driver;
	File source = tk.getScreenshotAs(OutputType.FILE);
	File dest = new File(".//target//report.png");
	FileUtils.copyFile(source,dest);

	driver.quit();
	}
}




