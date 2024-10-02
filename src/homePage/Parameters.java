package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	String AlmosaferURL = "https://www.almosafer.com/en";
	String ExpectedDefaultLanguage = "en";
	String ExpectedCurrancy = "SAR";
	String ExpextedContactNumber = "+966554400000";
	boolean ExpectedResultForTheLogo = true;
	String EpectedValue = "false";
	LocalDate TodayDate = LocalDate.now();

	int Today = TodayDate.getDayOfMonth();
	int Tomorrow = TodayDate.plusDays(1).getDayOfMonth();
	int TheDayAfterTomorrow = TodayDate.plusDays(2).getDayOfMonth();
	String[] EnglishCites = { "Dubai", "Jaddah", "Riyad" };
	int RandomEnglishCites = rand.nextInt(EnglishCites.length);

	String[] ArabicCites = { "دبي", "جدة" };
	int RandomArabicCites = rand.nextInt(ArabicCites.length);
	int SelctorRandom = rand.nextInt(2);
	boolean ExpectedResult = true;
	boolean ExpectedResults = true;

	public void RandomlySelectTheLanguage() {

		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomURL = rand.nextInt(URLs.length);

		driver.get(URLs[RandomURL]);
	}

	public void GeneralSetup() {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(AlmosaferURL);

	}

}
