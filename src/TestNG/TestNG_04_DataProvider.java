package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_04_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void initBrowser() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test(dataProvider = "UsernameAndPassword")
	public void f(String username, String password) {

		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
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
