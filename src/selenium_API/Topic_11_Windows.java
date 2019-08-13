
package selenium_API;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Windows {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Windows_Tab() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// get ra ID cua page hien tai (parent page)
		String parentID = driver.getWindowHandle();
		System.out.println("WebDriver form ID :" + parentID);

		// Click vao Googe link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);

		// Mo ra 2 tab
		switchToChildWindowByID(parentID);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("Automation testing");
		System.out.println("Google Tab ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "Google");
		Thread.sleep(2000);

		// ve lại parent Window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(3000);
		System.out.println("WebDriver form ID :" + parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

		// click facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		System.out.println("Google Tab ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		Thread.sleep(2000);

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click TIKI link
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);

		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		System.out.println("Google Tab ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Thread.sleep(2000);

		// dong tat ca cac tab ngoai tru parent window
		closeAllWindowWithoutParent(parentID);

		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_02_Window_Frame() throws Exception {
		driver.get("http://www.hdfcbank.com/");
		WebElement popupBanner = driver.findElement(By.xpath("//img[@class='popupCloseButton']//preceding-sibling::a/img"));
		if (popupBanner.isDisplayed()) {
			Assert.assertTrue(popupBanner.isDisplayed());
			driver.findElement(By.xpath("//div[@id='parentdiv']/img")).click();
			Thread.sleep(3000);
		}
		Assert.assertFalse(popupBanner.isDisplayed());

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToChildWindowByID(parentID);
		System.out.println("Agri Tab ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "HDFC Bank Kisan Dhan Vikas e-Kendra");

		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(2000);
		System.out.println("Account Detail Tab ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "Welcome to HDFC Bank NetBanking");

		WebElement policyFramne = driver.findElement(By.name("footer"));
		driver.switchTo().frame(policyFramne);

		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		System.out.println("Policy Tab ID: " + driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(), "HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		switchToWindowByTitle("HDFC BANK - CSR - Homepage");
		Assert.assertEquals(driver.getTitle(), "HDFC BANK - CSR - Homepage");

		// close all tab trừ parent tab
		closeAllWindowWithoutParent(parentID);
		switchToWindowByTitle("HDFC Bank:Personal Banking Services");
		Assert.assertEquals(driver.getTitle(), "HDFC Bank:Personal Banking Services");

	}

	@Test
	public void TC_03_Windows() throws InterruptedException {
		driver.get("http://live.guru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		System.out.println("Guru99 ID :" + parentID);

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		switchToWindowByTitle("Mobile");
		System.out.println("Mobile ID :" + driver.getWindowHandle());
		Assert.assertEquals(driver.getTitle(), "Mobile");

		WebElement SonyXperia = driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//li/a[text()='Add to Compare']"));
		SonyXperia.click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());

		WebElement SamsungGalaxy = driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//li/a[text()='Add to Compare']"));
		SamsungGalaxy.click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());

		driver.findElement(By.xpath("//div[@class='block block-list block-compare']//button")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		Thread.sleep(3000);
		closeAllWindowWithoutParent(parentID);

		switchToChildWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Mobile");

	}

	// Chi 2 Window hoac 2 tab moi dung ham nay, nhieu hon 2 tab no se khong work
	public void switchToChildWindowByID(String parent) {
		// get ra tat ca cac ID
		Set<String> allWindows = driver.getWindowHandles();
		// Dung vong lap de duyet qua tung ID
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}

	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}