package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_05_Parameters_multiBrowser {
	WebDriver driver;

	@Parameters({ "browser" })

	@BeforeClass
	public void initBrowser(String browserName) {
		System.out.println("Browser name: " + browserName);

		if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browserName.equals("headless")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
			driver = new ChromeDriver();
		} else {
			System.out.println("Please choose browser name!");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Parameters({ "username", "password" })
	@Test
	public void TC_01(String userName, String passWord) {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(passWord);
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title ='Log Out']")).click();
	}

	@DataProvider
	public Object[][] UsernameAndPassword() {
		return new Object[][] { new Object[] { "ngoctramtk94@gmail.com", "123456" }, new Object[] { "selenium_11_01@gmail.com", "111111" }, };
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
