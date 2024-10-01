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
import org.openqa.selenium.support.ui.Select;
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

	@Test(priority = 8)
	public void FillHotelTab() {

		String[] EnglishCites = { "Dubai", "Jaddah", "Riyad" };
		int RandomEnglishCites = rand.nextInt(EnglishCites.length);

		String[] ArabicCites = { "دبي", "جدة" };
		int RandomArabicCites = rand.nextInt(ArabicCites.length);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		String WebsiteURL = driver.getCurrentUrl();

		if (WebsiteURL.contains("ar")) {

			SearchHotelInputField.sendKeys(ArabicCites[RandomArabicCites]);
		}

		else {

			SearchHotelInputField.sendKeys(EnglishCites[RandomEnglishCites]);
		}

		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement FirstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		FirstResult.click();
	}

	@Test(priority = 9)
	public void RandomlySelectTheNumberOFVistor() {

		WebElement SelctorOfTheVistor = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select select = new Select(SelctorOfTheVistor);

		int SelctorRandom = rand.nextInt(2);
		select.selectByIndex(SelctorRandom);

		WebElement SerchHotelButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SerchHotelButton.click();

	}

	@Test(priority = 10)
	public void CheckThePageFullyLoded() throws InterruptedException {

		boolean ExpectedResult = true;
		Thread.sleep(13000);

		String Result = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();
		boolean Finished = Result.contains("found") || Result.contains("وجدنا");

		Assert.assertEquals(Finished, ExpectedResult);

	}

	@Test(priority = 11)
	public void SortItemsLowestToHighestPrice() {

		boolean ExpectedResults = true;
		WebElement LowestPriceSortButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceSortButton.click();

		WebElement pricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> AllPrices = pricesContainer.findElements(By.className("Price__Value"));

		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		System.out.println(LowestPrice);
		System.out.println(HighestPrice);

		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;

		Assert.assertEquals(ActualResults, ExpectedResults);
	}

}
