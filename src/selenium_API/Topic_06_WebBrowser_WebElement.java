
package selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser_WebElement {
	WebDriver driver;
	WebElement element;
	By emailTextbox = By.id("mail");
	By AgeUnder18Radio = By.id("under_18");
	By RadiobuttonDisable = By.id("radio-disabled");
	By Password = By.id("password");
	By EducationTextArea = By.id("edu");
	By JobRole1Dropdown = By.id("job1");
	By JobRole2Dropdown = By.id("job2");
	By DevelopmentCheckbox = By.id("development");
	By Slider01 = By.id("slider-1");
	By FirstEmage = By.xpath("//h5[text()='Name: User1']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Displayed() throws InterruptedException {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		if (isElementDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(EducationTextArea)) {
			driver.findElement(EducationTextArea).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(AgeUnder18Radio)) {
			driver.findElement(AgeUnder18Radio).click();
			Assert.assertTrue(driver.findElement(AgeUnder18Radio).isSelected());
		}

		Assert.assertFalse(isElementDisplayed(FirstEmage));
		Thread.sleep(1000);
	}

	@Test
	public void TC_02_Enabled_Disabled() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		isElementEnabled(emailTextbox);
		isElementEnabled(AgeUnder18Radio);
		isElementEnabled(RadiobuttonDisable);
		isElementEnabled(Password);
		isElementEnabled(EducationTextArea);
		isElementEnabled(JobRole1Dropdown);
		isElementEnabled(JobRole2Dropdown);
		isElementEnabled(DevelopmentCheckbox);
		isElementEnabled(Slider01);

	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		CheckCheckBox(AgeUnder18Radio);
		CheckCheckBox(DevelopmentCheckbox);

		Assert.assertTrue(isElementSelected(AgeUnder18Radio));
		Assert.assertTrue(isElementSelected(DevelopmentCheckbox));

		UnChecktoCheckBox(DevelopmentCheckbox);
		Assert.assertFalse(isElementSelected(DevelopmentCheckbox));

	}

	public void CheckCheckBox(By by) {
		WebElement element = driver.findElement(by);
		// nếu chưa được chọn thi click để chọn
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void UnChecktoCheckBox(By by) {
		WebElement element = driver.findElement(by);
		// nếu chọn roi thi click de bo chon
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("---------Element [" + by + " ] is displayed----------");
			return true;
		} else {
			System.out.println("---------Element [" + by + " ] is not displayed-----");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("--------Element [" + by + "] is enabed---------");
			return true;
		} else {
			System.out.println("--------Element [" + by + "] is disabled--------");
			return false;
		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("---------Element [" + by + " ] is selected----------");
			return true;
		} else {
			System.out.println("---------Element [" + by + " ] is not selected-----");
			return false;
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}