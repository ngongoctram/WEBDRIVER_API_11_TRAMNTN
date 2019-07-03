
package selenium_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_05_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_LoginWithEmailAndPassWordEmpty() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		String emailErrorMsg = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");

		String passErrorMsg = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("1434324@456.555");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();

		String emailErrorMsg = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");

		String passErrorMsg = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");

	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Character() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("ngoctramtk94@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("1234");
		driver.findElement(By.id("send2")).click();

		String passErrorMsg = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("ngoctramtk94@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456789");
		driver.findElement(By.id("send2")).click();

		String errorMsg = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");

	}

	@Test
	public void TC_05_CreateAccount() throws Exception {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@class='button']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Tram");
		driver.findElement(By.id("middlename")).sendKeys("Ngoc");
		driver.findElement(By.id("lastname")).sendKeys("Ngo");
		driver.findElement(By.id("email_address")).sendKeys("seleniumonline" + randomNumber() + "@hotmail.com");
		driver.findElement(By.id("password")).sendKeys("12345678");
		driver.findElement(By.id("confirmation")).sendKeys("12345678");
		Thread.sleep(4000);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String Msg = driver.findElement(By.xpath("//span[contains(text(),'registering')]")).getText();
		Assert.assertEquals(Msg, "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        Thread.sleep(5000);
        
        String URL =driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://live.guru99.com/index.php/");
        
	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random number = " + number);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}