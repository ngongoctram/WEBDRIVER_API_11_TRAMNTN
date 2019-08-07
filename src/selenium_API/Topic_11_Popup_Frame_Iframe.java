
package selenium_API;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_PopUp_frame_Iframe() throws InterruptedException {
		driver.get("http://www.hdfcbank.com/");

		// neu popup display thi check + close pop up di
		WebElement popupBaner = driver.findElement(By.xpath("//img[@class='popupCloseButton']//preceding-sibling::a/img"));
		if (popupBaner.isDisplayed()) {
			Assert.assertTrue(popupBaner.isDisplayed());
			driver.findElement(By.xpath("//div[@id='parentdiv']/img")).click();
			Thread.sleep(3000);
		}

		// sau khi close thi kiem tra no khong con displayed
		Assert.assertFalse(popupBaner.isDisplayed());

		// verify right banner co 9 images
		List<WebElement> rightBannerImages = driver.findElements(By.xpath("//div[@id='rightbanner']//img"));
		int rightBannerImageNumber = rightBannerImages.size();

		System.out.println("Right banner image:" + rightBannerImageNumber);
		Assert.assertEquals(rightBannerImageNumber, 9);

		// verify flipbanner hien thi va co 8 images
		List<WebElement> flipBannerImages = driver.findElements(By.xpath("//div[@class ='flipBanner']//img[@class='front icon']"));
		int FlipBannerImageNumber = flipBannerImages.size();
		System.out.println("Flipper Banner image:" + FlipBannerImageNumber);
		Assert.assertEquals(FlipBannerImageNumber, 8);

		// 8 images trong flipbanner displayed
		// cách 1
//		Assert.assertTrue(flipBannerImages.get(0).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(1).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(2).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(3).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(4).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(5).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(6).isDisplayed());
//		Assert.assertTrue(flipBannerImages.get(7).isDisplayed());

//		// cach 2
//		for (int i = 0; i < rightBannerImageNumber; i++) {
//			Assert.assertTrue(flipBannerImages.get(i).isDisplayed());
//		}

		// cach 3
		for (WebElement image : flipBannerImages) {
			Assert.assertTrue(image.isDisplayed());
		}

		driver.get("https://netbanking.hdfcbank.com/netbanking");
		// khai bao element cho frame
		WebElement loginFrame = driver.findElement(By.name("login_page"));
		// switch vào frame/iframe
		driver.switchTo().frame(loginFrame);
		driver.findElement(By.name("fldLoginUserId")).sendKeys("selenium_class");
		driver.findElement(By.xpath("//table[@class='lForm']//img[@alt='continue']")).click();

		Assert.assertTrue(driver.findElement(By.className("input_password")).isDisplayed());

		// Buoc trung gian switch về main page
		driver.switchTo().defaultContent();
		WebElement footer = driver.findElement(By.name("footer"));
		driver.switchTo().frame(footer);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Privacy Policy']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}