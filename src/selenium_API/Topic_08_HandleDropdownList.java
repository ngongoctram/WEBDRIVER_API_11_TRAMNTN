
package selenium_API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_HandleDropdownList {
	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait waitExplicit; // cho phep cho 1 list element duoc load ra thanh cong
	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		waitExplicit = new WebDriverWait(driver, 30);
		javascriptExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_HandleDropdownListHTML() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// Handle dropdownlist Job Role 01
		WebElement JobDropdown = driver.findElement(By.id("job1"));
		select = new Select(JobDropdown);

		// check Job dropdown no support mutil-select
		boolean status = select.isMultiple();
		System.out.println("Dropdownlist status :" + status);
		Assert.assertFalse(status);

		// Select Automation Tester
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");

		// Select Manual Tester
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");

		// Select Mobile Tester
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");

		// Kiem tra du 5 gia trị
		Assert.assertEquals(select.getOptions().size(), 5);

	}

	// @Test
	public void TC_02_CustomDropdown_Jquery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		ClickToItemInDropdownlist("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text() ='19']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text() ='10']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text() ='15']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text() ='3']")).isDisplayed());
		Thread.sleep(2000);

	}

	// @Test
	public void TC_03_CustomDropdown_Angular() throws InterruptedException {
		driver.get("https://material.angular.io/components/select/examples");

		ClickToItemInDropdownlist("//label/mat-label[text() ='State']", "//mat-option/span", "Maine");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-option[@role='option']//span[text()='Maine']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//label/mat-label[text() ='State']", "//mat-option/span", "Alaska");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-option[@role='option']//span[text()='Alaska']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//label/mat-label[text() ='State']", "//mat-option/span", "Hawaii");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-option[@role='option']//span[text()='Hawaii']")).isDisplayed());
		Thread.sleep(2000);

	}

	// @Test
	public void TC_04_CustomDropdown_ReactJS() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		ClickToItemInDropdownlist("//h3[@id='selection']//ancestor::div[@class='equal width row']/following-sibling::div[@class='row']//div[@role='listbox']", "//div[@class='visible menu transition']//div[@role='option']/span[@class='text']", "Christian");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[text()='Christian']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//h3[@id='selection']//ancestor::div[@class='equal width row']/following-sibling::div[@class='row']//div[@role='listbox']", "//div[@class='visible menu transition']//div[@role='option']/span[@class='text']", "Elliot Fu");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[text()='Elliot Fu']")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//h3[@id='selection']//ancestor::div[@class='equal width row']/following-sibling::div[@class='row']//div[@role='listbox']", "//div[@class='visible menu transition']//div[@role='option']/span[@class='text']", "Matt");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']//div[text()='Matt']")).isDisplayed());
		Thread.sleep(2000);

	}

	// @Test
	public void TC_05_CustomDropdown_VueJS() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		ClickToItemInDropdownlist("//div[@class='btn-group']/li[@class='dropdown-toggle']", "//div[@class='btn-group']/ul[@class='dropdown-menu']/li/a", "First Option");

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'First Option')]")).isDisplayed());
		Thread.sleep(2000);

		ClickToItemInDropdownlist("//div[@class='btn-group']/li[@class='dropdown-toggle']", "//div[@class='btn-group']/ul[@class='dropdown-menu']/li/a", "Second Option");

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Second Option')]")).isDisplayed());
		Thread.sleep(2000);

	}

	@Test
	public void TC_06_CustomDropdown_Editable() throws InterruptedException {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Audi");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='es-visible selected'and text()='Audi']")).isDisplayed());		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Jaguar");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='es-visible selected'and text()='Jaguar']")).isDisplayed());		
		Thread.sleep(2000);
		
		
	}
	
//	public void CompareValueEditableDropDown(String ActualValue, String ExpectedValue) {
//		WebElement ActualHidenValue = driver.findElement(By.xpath(ActualValue));
//		String ActualText = (String) javascriptExecutor.executeScript("argument[0].text()", ActualHidenValue);
//		String ActualText = (String) javascriptExecutor.executeScript("return jQuery(argument[0].text())", ActualHidenValue);
//
//		System.out.print("Selected value :" + ActualText );
//		Assert.assertEquals(ActualText, ExpectedValue);
//		
//	}

	// Ham common
	public void ClickToItemInDropdownlist(String parentLoacator, String AllItem, String ExpectedItem) throws InterruptedException {

		// 1 - click vào thẻ cha để cho xổ ra tất cả các item bên trong
		WebElement parentDropdown = driver.findElement(By.xpath(parentLoacator));

		// Click by javaScript
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(2000);

		// 2 - chờ cho tất cả các item trong dropdown được load thành công
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(AllItem)));

		// 3 - duyệt qua từng item và get text
		// Tao ra 1 list WebElement de duyet qua tung item
		List<WebElement> allItemInDropDown = driver.findElements(By.xpath(AllItem));

		// Dung vong lap duyet qua tung item
		for (WebElement item : allItemInDropDown) {
			String actualtext = item.getText();
			if (actualtext.equals(ExpectedItem)) {
				// neu nhu bang thi scroll den dung item nay roi click vao, neu ko = thi chuyen
				// qua item khac so sanh tiep
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				System.out.println("Actual text =" + actualtext);
				Thread.sleep(1000);
				item.click();
				break;

			}
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}