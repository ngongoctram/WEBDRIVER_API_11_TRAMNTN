
package selenium_API;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor javascriptExecutor;

	String workingDirectory = System.getProperty("user.dir");
	String jsFilePath = workingDirectory + "\\lib\\drag_and_drop_helper.js";
	String jsQueryFilePath = workingDirectory + "\\lib\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_HoverMouse() throws Exception {
		driver.get("http://www.myntra.com/");

		WebElement profile = driver.findElement(By.xpath("//div[@class='desktop-user']//div[@class='desktop-userIconsContainer']"));
		WebElement login = driver.findElement(By.xpath("//div[@data-reactid='760']//a[@class='desktop-linkButton' and @data-reactid='763']"));

		Assert.assertFalse(login.isDisplayed());

		action.moveToElement(profile).perform();
		Thread.sleep(2000);
		Assert.assertTrue(login.isDisplayed());
		login.click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']//p[text()='Login to Myntra']")).isDisplayed());

	}

	@Test
	public void TC_02_ClickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release();
		action.perform();

		List<WebElement> numberSeleted = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(numberSeleted.size(), 4);

		for (WebElement numbers1 : numberSeleted) {
			System.out.println(numbers1.getText());
		}

	}

	@Test
	public void TC_03_SelectRandomItem() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform(); // nhan phim control
		action.click(numbers.get(0)).perform();
		action.click(numbers.get(4)).perform();
		action.click(numbers.get(6)).perform();
		action.keyUp(Keys.CONTROL).perform(); // nha phim control

		List<WebElement> numberSeleted = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(numberSeleted.size(), 3);

	}

	@Test
	public void TC_04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClickbtn = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));

		action.doubleClick(doubleClickbtn).perform();

		Assert.assertEquals(driver.switchTo().alert().getText(), "The Button was double-clicked.");
	}

	@Test
	public void TC_05_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClick).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover')) and not(contains(@class,'context-menu-visible'))]")).isDisplayed());

		WebElement quit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quit).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]")).isDisplayed());

		action.click(quit).perform();

		driver.switchTo().alert().accept();

		Assert.assertFalse(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());

	}

	@Test
	public void TC_06_DragAndrop() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Assert.assertEquals(target.getText(), "Drag the small circle here.");

		WebElement acceptButton = driver.findElement(By.xpath("//button[text()='Accept Cookies']"));
		javascriptExecutor.executeScript("arguments[0].click();", acceptButton);
		Thread.sleep(2000);

		action.dragAndDrop(source, target).perform();
		Assert.assertEquals(target.getText(), "You did great!");

	}

	@Test
	public void TC_07_DragAndrop_HTML5() throws Exception {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "div[id='column-a']";
		String targetCss = "div[id='column-b']";

		String java_script = readFile(jsFilePath);
		String jQueryLoader = readFile(jsQueryFilePath);

		// Thuc thi 1 doan lenh javascript -> inject Jquery vao browser
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript(jQueryLoader);

		// Thuc thi doan lenh javascript de keo tha element
		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({dropTarget:\"" + targetCss + "\"});";
		je.executeScript(java_script);
		Thread.sleep(4000);

		// B to A
		je.executeScript(java_script);
		Thread.sleep(4000);

	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}