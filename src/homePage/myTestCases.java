package homePage;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://www.almosafer.com/en";
	String ExpectedDefaultLanguage="en";
	
	
	@BeforeTest
	public void mySetup () {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']")).click();;
		
	}
	
	@Test  (priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {
		
	String ActualLanguage= driver.findElement(By.tagName("html")).getAttribute("lang");
    
	Assert.assertEquals(ActualLanguage, ExpectedDefaultLanguage);
		
		
		
		
		
	}
	
	@Test (priority = 2)
	public void DefultCurrencySARTest() {
	String ExpectedCurrancy	= "SAR";
	WebElement Currancy = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
	String ActualCurrancy= Currancy.getText();
	
	Assert.assertEquals(ActualCurrancy, ExpectedCurrancy);
		
	}
	
	@Test(priority = 3)
	public void CheckContactNumber() {
	String ExpextedContactNumber = "+966554400000";
String ActualContactNumber=	driver.findElement(By.tagName("strong")).getText();		
		
Assert.assertEquals(ActualContactNumber, ExpextedContactNumber);
		
		
		
		
	}
	
	@Test(priority = 4)
	public void CheckQitafLogo() {
		boolean ExpectedResultForTheLogo =true;
		WebElement TheFooter=driver.findElement(By.tagName("footer"));
	boolean ActualResultForTheLogo=	TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF")).isDisplayed();
		
		
		
	}
	
	
	
	
}
