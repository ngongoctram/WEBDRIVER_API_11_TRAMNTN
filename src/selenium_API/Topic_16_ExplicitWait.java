
package selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_16_ExplicitWait {
	private static final Class<? extends Throwable> NoSuchElementException = null;
	WebDriver driver;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// waitExplicit = new WebDriverWait(driver, 15 );
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_ExplicitWait_Invisible() {
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Wait loading icon invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());

	}

	// @Test
	public void TC_02_ExplicitWait_Visible() {
		waitExplicit = new WebDriverWait(driver, 4);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Wait helloworld text visible
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	// @Test
	public void TC_03_ExplicitWait_TextToBe() {
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		waitExplicit.until(ExpectedConditions.textToBePresentInElement(By.xpath("//div[@id='finish']/h4"), "Hello World!"));

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());

	}

	// @Test
	public void TC_04_ExplicitWait() {
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")));

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']")).isDisplayed());

		String dateBefore = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println("Date before select date:" + dateBefore);

		driver.findElement(By.xpath("//table[@id='ctl00_ContentPlaceholder1_RadCalendar1_Top']//a[text()='22']")).click();

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='22']")));

		String dateAfter = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println("Date before select date:" + dateAfter);

		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(), "Thursday, August 22, 2019");
	}

	@Test
	public void TC_05_FluentWait() {
		waitExplicit = new WebDriverWait(driver, 15);
		driver.get("https://daominhdam.github.io/fluent-wait/");
		WebElement countDown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countDown));

		// Khoi tao Fluent Wait
		new FluentWait<WebElement>(countDown)
				// tong time wait la 15S
				.withTimeout(15, TimeUnit.SECONDS)
				// Tan so moi 1/10s check 1 lan
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				// Neu gap Exception la find khong thay element thi bo qua
				.ignoring(NoSuchFrameException.class)
				// Kiem tra dieu kien
				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						// kiem tra dieu kien countDown =01
						boolean flag = element.getText().endsWith("01");
						System.out.println("Time =" + element.getText());
						// return gia tri cho function apply
						return flag;
					}

				});

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}