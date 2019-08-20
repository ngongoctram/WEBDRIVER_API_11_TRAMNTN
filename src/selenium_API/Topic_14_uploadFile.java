
package selenium_API;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_uploadFile {
	WebDriver driver;
	Select select;
	JavascriptExecutor javascript;
	String relativePath, filename_01, filename_02, filename_03, filename_04;
	String filePath_01, filePath_02, filePath_03, filePath_04, firefoxPath, chromePath, IEPath;
	String randomFolder;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;

		// lay ra duong dan tuong doi cua thu muc du an
		relativePath = System.getProperty("user.dir");

		filename_01 = "test01.jpg";
		filename_02 = "test02.jpg";
		filename_03 = "test03.jpg";
		filename_04 = "anhnen.jpg";

		filePath_01 = relativePath + "\\upload\\" + filename_01;
		filePath_02 = relativePath + "\\upload\\" + filename_02;
		filePath_03 = relativePath + "\\upload\\" + filename_03;
		filePath_04 = relativePath + "\\upload\\" + filename_04;

		firefoxPath = relativePath + "\\upload\\" + "firefox.exe";
		chromePath = relativePath + "\\upload\\" + "chrome.exe";
		IEPath = relativePath + "\\upload\\" + "ie.exe";

		System.out.println(relativePath);
		System.out.println(filePath_01);
		System.out.println(filePath_02);
		System.out.println(filePath_03);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_UploadBySendkeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// upload tung file mot
//		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePath_01);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePath_02);
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@name='files[]']")).sendKeys(filePath_03);
//		Thread.sleep(2000);

		// upload nhieu files 1 luc
		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
		uploadFile.sendKeys(filePath_01 + "\n" + filePath_02 + "\n" + filePath_03);
		Thread.sleep(2000);

		// verify files load len thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + filename_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + filename_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + filename_03 + "']")).isDisplayed());

		// click vao button start cua tung file
		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(2000);
		}

		// verify 3 files upload lên thanh cong

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_03 + "']")).isDisplayed());

		Thread.sleep(2000);

	}

	@Test
	public void TC_02_uploadFileByAutoIT() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));

		if (driver.toString().contains("chrome")) {
			System.out.println("Go to Chrome:");
			uploadFile.click();
			Thread.sleep(1000);
			Runtime.getRuntime().exec(new String[] { chromePath, filePath_01 });

		} else if (driver.toString().contains("firefox")) {
			System.out.println("Go to Firefox");
			uploadFile.click();
			Thread.sleep(1000);
			Runtime.getRuntime().exec(new String[] { firefoxPath, filePath_01 });
			Thread.sleep(3000);
		} else {
			System.out.println("Go to IE:");
			clickToElementByjavascript("//input[@name='files[]']");
			Thread.sleep(1000);
			Runtime.getRuntime().exec(new String[] { IEPath, filePath_01 });

		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + filename_01 + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_01 + "']")).isDisplayed());

	}

	@Test
	public void TC_03_UploadFileByRobot_Class() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Specify the file location with extension
		StringSelection select = new StringSelection(filePath_01);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		if (driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
			WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
			uploadFile.click();
			Thread.sleep(1000);
		} else {
			System.out.println("Go to IE:");
			clickToElementByjavascript("//input[@name='files[]']");
			Thread.sleep(1000);
		}

		Robot robot = new Robot();
		Thread.sleep(1000);

		// Press Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Nhan xuong Ctrl-V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha phim Ctrl-V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(3000);

		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + filename_01 + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement start : startButtons) {
			start.click();
			Thread.sleep(2000);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_01 + "']")).isDisplayed());

	}

	public Object clickToElementByjavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return javascript.executeScript("arguments[0].click();", element);
	}

	@Test
	public void TC_04_UploadFile() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");
		driver.findElement(By.xpath("//input[@id='uploadname1']")).sendKeys(filePath_04);

		WebElement UploadTo = driver.findElement(By.xpath("//select[@name='subdir1']"));
		select = new Select(UploadTo);

		boolean status = select.isMultiple();
		System.out.println("dropdownlist stutus:" + status);
		Assert.assertFalse(status);

		select.selectByValue("/");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "/uploaddemo/files/");
		Thread.sleep(2000);

		randomFolder = "selenium" + randomNumber();
		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(randomFolder);

		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys("tram@gmail.com");
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys("TRAM NGO");

		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();

		Thread.sleep(9000);
		// verify ket qua sau khi upload thanh cong
		Assert.assertEquals(driver.findElement(By.xpath("//dd[text()='Email Address: tram@gmail.com']")).getText(), "Email Address: tram@gmail.com");
		Assert.assertEquals(driver.findElement(By.xpath("//dd[text()='First Name: TRAM NGO']")).getText(), "First Name: TRAM NGO");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='" + filename_04 + "']")).getText(), filename_04);

		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();

		// click vao randomfolder
		driver.findElement(By.xpath("//a[text()='" + randomFolder + "']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//a[@id='fclink-anhnenjpg']")).getText(), filename_04);

		driver.findElement(By.xpath("//img[@class='fcthumb']")).click();
		Thread.sleep(5000);

		String url = driver.getCurrentUrl();
		Assert.assertEquals(url.contains(filename_04), true);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename_04 + "']")).isDisplayed());

	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}