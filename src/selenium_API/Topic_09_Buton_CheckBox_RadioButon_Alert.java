
package selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Buton_CheckBox_RadioButon_Alert {
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Button_BuiltIn_Click() {
		driver.get("http://live.guru99.com/");
		clickBySelenium("//div[@class='footer']//a[@title='My Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		clickBySelenium("//div[@class='buttons-set']//span[text()='Create an Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

	}

	// @Test
	public void TC_02_Button_Javascript_Click() {
		driver.get("http://live.guru99.com/");
		clickByjavascript("//div[@class='footer']//a[@title='My Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		clickByjavascript("//div[@class='buttons-set']//span[text()='Create an Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	public void clickBySelenium(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();

	}

	public void clickByjavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascriptExecutor.executeScript("arguments[0].click();", element);

	}

	// @Test
	public void TC_03_CheckBox_BuiltIn() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		clickBySelenium("//label[text()='Dual-zone air conditioning']");
		Assert.assertTrue(isCheckBoxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
		clickBySelenium("//label[text()='Dual-zone air conditioning']");
		Assert.assertFalse(isCheckBoxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
	}

	// @Test
	public void TC_04_CheckBox_Custom() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		clickByjavascript("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
		Assert.assertTrue(isCheckBoxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
		clickByjavascript("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
		Assert.assertFalse(isCheckBoxOrRadioButtonSelected("//label[text()='Dual-zone air conditioning']//preceding-sibling::input"));
	}

	// @Test
	public void TC_05_Radio_button_custom() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		clickByjavascript("//label[text()='1.6 Diesel, 77kW']//preceding-sibling::input");
		Assert.assertTrue(isCheckBoxOrRadioButtonSelected("//label[text()='1.6 Diesel, 77kW']//preceding-sibling::input"));
		clickByjavascript("//label[text()='2.0 Diesel, 103kW']//preceding-sibling::input");
		Assert.assertTrue(isCheckBoxOrRadioButtonSelected("//label[text()='2.0 Diesel, 103kW']//preceding-sibling::input"));
		Assert.assertFalse(isCheckBoxOrRadioButtonSelected("//label[text()='1.6 Diesel, 77kW']//preceding-sibling::input"));
	}

	public boolean isCheckBoxOrRadioButtonSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		boolean statusElement = element.isSelected();
		System.out.println("Status Element: " + statusElement);
		if (statusElement) {
			return true;
		} else {
			return false;
		}

	}

	// @Test
	public void TC_06_Accept_Alert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		clickBySelenium("//button[text()='Click for JS Alert']");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(getTextElement("//p[@id='result']"), "You clicked an alert successfully");
	}

	public String getTextElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		System.out.println("element.getText()");
		return element.getText();
	}

	// @Test
	public void TC_07_Alert_Comfirm() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		clickBySelenium("//button[text()='Click for JS Confirm']");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(getTextElement("//p[@id='result']"), "You clicked: Cancel");
	}

	// @Test
	public void TC_08_Alert_Prompt() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		clickBySelenium("//button[text()='Click for JS Prompt']");
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String valueAlert = "Automation testing";
		alert.sendKeys(valueAlert);
		alert.accept();
		Assert.assertEquals(getTextElement("//p[@id='result']"), "You entered: " + valueAlert);
	}

	@Test
	public void TC_09_Alert_Authentication() {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		driver.get(byPassAuthenticationAlert(url, "admin", "admin"));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	public String byPassAuthenticationAlert(String url, String username, String password) {
		String[] splitUrl = url.split("//");
		String ExpectedUrl = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		return ExpectedUrl;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}