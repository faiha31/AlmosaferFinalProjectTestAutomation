package homePage;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String AlmosaferURL = "https://www.almosafer.com/en";
	String ExpectedDefaultLanguage = "en";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(AlmosaferURL);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"))
				.click();
		;

	}

	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanguage);

	}

	@Test(priority = 2)
	public void DefultCurrencySARTest() {

		String ExpectedCurrancy = "SAR";
		WebElement Currancy = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualCurrancy = Currancy.getText();

		Assert.assertEquals(ActualCurrancy, ExpectedCurrancy);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {

		String ExpextedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactNumber, ExpextedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitafLogo() {

		boolean ExpectedResultForTheLogo = true;
		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		boolean ActualResultForTheLogo = TheFooter.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"))
				.isDisplayed();

		Assert.assertEquals(ActualResultForTheLogo, ExpectedResultForTheLogo);
	}

	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {

		String EpectedValue = "false";
		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"))
				.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, EpectedValue);

	}

	@Test(priority = 6)
	public void CheckDepatureDate() {

		LocalDate TodayDate = LocalDate.now();

		int Today = TodayDate.getDayOfMonth();
		int Tomorrow = TodayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTomorrow = TodayDate.plusDays(2).getDayOfMonth();

		System.out.println(Today);
		System.out.println(Tomorrow);
		System.out.println(TheDayAfterTomorrow);

		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("LiroG"));

		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualReturnDate = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualReturnDateAsInt = Integer.parseInt(ActualReturnDate);

		Assert.assertEquals(ActualDepatureDateAsInt, Tomorrow);
		Assert.assertEquals(ActualReturnDateAsInt, TheDayAfterTomorrow);

	}

	@Test(priority = 7)
	public void RandomlyChangeTheLanguage() {

		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomURL = rand.nextInt(URLs.length);

		driver.get(URLs[RandomURL]);

	}

}
