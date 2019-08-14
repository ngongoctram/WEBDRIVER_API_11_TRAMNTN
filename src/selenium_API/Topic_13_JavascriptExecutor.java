
package selenium_API;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor javascript;

	String name, dateofbirth, address, city, state, pin, phone, email, password;

	By NameTextbox = By.name("name");
	By DateOfBirthTextbox = By.name("dob");
	By AddressTextArea = By.name("addr");
	By CityTextbox = By.name("city");
	By StateTextbox = By.name("state");
	By PinTextbox = By.name("pinno");
	By PhoneTextbox = By.name("telephoneno");
	By EmailTextbox = By.name("emailid");
	By PasswordTextbox = By.name("password");
	By Submitbutton = By.name("sub");

	By CustomerNameRow = By.xpath("//td[text() ='Customer Name']//following-sibling::td");
	By BirthdayRow = By.xpath("//td[text() ='Birthdate']//following-sibling::td");
	By AddressRow = By.xpath("//td[text() ='Address']//following-sibling::td");
	By CityRow = By.xpath("//td[text() ='City']//following-sibling::td");
	By StateRow = By.xpath("//td[text() ='State']//following-sibling::td");
	By PinRow = By.xpath("//td[text() ='Pin']//following-sibling::td");
	By MobileRow = By.xpath("//td[text() ='Mobile No.']//following-sibling::td");
	By EmailRow = By.xpath("//td[text() ='Email']//following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// data input
		name = "Eryn Overland";
		dateofbirth = "2018-01-12";
		address = "012 Larry Park";
		city = "Colorado Springs";
		state = "Colorado";
		pin = "263194";
		phone = "7193801618";
		email = "eoverland0@sohugg.com";
		password = "123456";

	}

	// @Test
	public void TC_01_JavascriptExecutor() {
		navigateToUrlByjavascript("http://live.guru99.com/");
		String domain = (String) executeForBrowser("return document.domain");
		System.out.println(domain);
		Assert.assertEquals(domain, "live.guru99.com");
		String URL = (String) executeForBrowser("return document.URL");
		System.out.println(URL);
		Assert.assertEquals(URL, "http://live.guru99.com/");
		clickToElementByjavascript("//a[text()='Mobile']");
		clickToElementByjavascript("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button[@class='button btn-cart']");
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		clickToElementByjavascript("//a[text()='Privacy Policy']");
		String title = (String) executeForBrowser("return document.title");
		System.out.println(title);
		Assert.assertEquals(title, "Privacy Policy");
		scrollToBottomPage();
		navigateToUrlByjavascript("http://demo.guru99.com/v4/");
		Assert.assertEquals((String) executeForBrowser("return document.domain"), "demo.guru99.com");

	}

	// @Test
	public void TC_02_RemoveAtribute() throws Exception {
		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.name("uid")).sendKeys("mngr209708");
		driver.findElement(By.name("password")).sendKeys("arasYsY");
		driver.findElement(By.name("btnLogin")).click();

		// verify HomePage displayed
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text() =\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.linkText("New Customer")).click();

		driver.findElement(NameTextbox).sendKeys(name);

		// remove attribute
		removeAttributeInDOM(driver.findElement(DateOfBirthTextbox), "type");
		Thread.sleep(3000);

		driver.findElement(DateOfBirthTextbox).sendKeys(dateofbirth);
		driver.findElement(AddressTextArea).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(PhoneTextbox).sendKeys(phone);
		driver.findElement(EmailTextbox).sendKeys(email);
		driver.findElement(PasswordTextbox).sendKeys(password);

		driver.findElement(Submitbutton).click();
		Thread.sleep(4000);

		// Verify new customer create success

		Assert.assertTrue(driver.findElement(By.xpath("//table[@id ='customer']//p[@class='heading3' and text() ='Customer Registered Successfully!!!']")).isDisplayed());
		// output new customer displayed
		Assert.assertEquals(driver.findElement(CustomerNameRow).getText(), name);
		Assert.assertEquals(driver.findElement(BirthdayRow).getText(), dateofbirth);
		Assert.assertEquals(driver.findElement(AddressRow).getText(), address);
		Assert.assertEquals(driver.findElement(CityRow).getText(), city);
		Assert.assertEquals(driver.findElement(StateRow).getText(), state);
		Assert.assertEquals(driver.findElement(PinRow).getText(), pin);
		Assert.assertEquals(driver.findElement(MobileRow).getText(), phone);
		Assert.assertEquals(driver.findElement(EmailRow).getText(), email);

	}

	@Test
	public void TC_03_CreateAnAccount() throws Exception {
		navigateToUrlByjavascript("http://live.guru99.com/");
		clickToElementByjavascript("//div[@id='header-account']//a[@title='My Account']");
		clickToElementByjavascript("//span[text()='Create an Account']");

		sendkeyToElementByjavascript(driver.findElement(By.id("firstname")), "Tram");
		sendkeyToElementByjavascript(driver.findElement(By.id("middlename")), "Ngoc");
		sendkeyToElementByjavascript(driver.findElement(By.id("lastname")), "Ngo");
		sendkeyToElementByjavascript(driver.findElement(By.id("email_address")), "seleniumonline" + randomNumber() + "@hotmail.com");
		sendkeyToElementByjavascript(driver.findElement(By.id("password")), "12345678");
		sendkeyToElementByjavascript(driver.findElement(By.id("confirmation")), "12345678");
		Thread.sleep(4000);

		clickToElementByjavascript("//button[@title ='Register']");

		verifyTextInInnerText("Thank you for registering with Main Website Store.");
		clickToElementByjavascript("//div[@class='account-cart-wrapper']//span[text()='Account']");
		clickToElementByjavascript("//a[@title='Log Out']");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img[contains(@src,'logo.png')]")).isDisplayed());
		Assert.assertEquals((String) executeForBrowser("return document.title"), "Home page");

	}

	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("number" + number);
		return number;

	}

	public void highlightElement(WebElement element) {
		String originalStyle = element.getAttribute("style");
		javascript.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		javascript.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void highlightElement1(WebElement element) {
		String originalStyle = element.getAttribute("style");
		javascript.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		javascript.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public Object executeForBrowser(String javaSript) {
		return javascript.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) javascript.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public Object clickToElementByjavascript(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return javascript.executeScript("arguments[0].click();", element);
	}

	public Object scrollToElement(WebElement element) {
		return javascript.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public Object sendkeyToElementByjavascript(WebElement element, String value) {
		return javascript.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object scrollToBottomPage() {
		return javascript.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object removeAttributeInDOM(WebElement element, String attributeRemove) {
		return javascript.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public Object navigateToUrlByjavascript(String url) {
		return javascript.executeScript("window.location = '" + url + "'");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}