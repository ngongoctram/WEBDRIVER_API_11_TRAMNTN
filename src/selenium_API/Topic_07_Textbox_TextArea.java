
package selenium_API;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	String name, dateofbirth, address, city, state, pin, phone, email, password ,customerID;
	String newAdress, newCity, newState, newPin, newPhone, newEmail;
	
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
	
	By CustomerNameRow =By.xpath("//td[text() ='Customer Name']//following-sibling::td");
	By BirthdayRow =By.xpath("//td[text() ='Birthdate']//following-sibling::td");
	By AddressRow =By.xpath("//td[text() ='Address']//following-sibling::td");
	By CityRow =By.xpath("//td[text() ='City']//following-sibling::td");
	By StateRow =By.xpath("//td[text() ='State']//following-sibling::td");
	By PinRow =By.xpath("//td[text() ='Pin']//following-sibling::td");
	By MobileRow =By.xpath("//td[text() ='Mobile No.']//following-sibling::td");
	By EmailRow =By.xpath("//td[text() ='Email']//following-sibling::td");
	
	

	@BeforeClass
	public void beforeClass() {
		//data input
		name ="Eryn Overland";
		dateofbirth ="2018-01-12";
		address ="012 Larry Park";
		city ="Colorado Springs";
		state ="Colorado";
		pin ="263194";
		phone ="7193801618";
		email ="eoverland0@sohugg.com";
		password ="123456";
		
		//data edit
		newAdress ="78721 Saint Paul Place";
		newCity ="Winston Salem";
		newState ="North Carolina";
		newPin ="263195";
		newPhone ="3364003897";
		newEmail ="kweatherup5@ca.gov";
	
				
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.findElement(By.name("uid")).sendKeys("mngr209708");
		driver.findElement(By.name("password")).sendKeys("arasYsY");
		driver.findElement(By.name("btnLogin")).click();

		// verify HomePage displayed
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text() =\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	}

	@Test
	public void TC_01_NewCustomer() {
		// Open New Customer Page
		driver.findElement(By.linkText("New Customer")).click();

		driver.findElement(NameTextbox).sendKeys(name);
		driver.findElement(DateOfBirthTextbox).sendKeys(dateofbirth);
		driver.findElement(AddressTextArea).sendKeys(address);
		driver.findElement(CityTextbox).sendKeys(city);
		driver.findElement(StateTextbox).sendKeys(state);
		driver.findElement(PinTextbox).sendKeys(pin);
		driver.findElement(PhoneTextbox).sendKeys(phone);
		driver.findElement(EmailTextbox).sendKeys(email);
		driver.findElement(PasswordTextbox).sendKeys(password);

		driver.findElement(Submitbutton).click();
		
		
		// Verify new customer create success
		
		Assert.assertTrue(driver.findElement(By.xpath("//table[@id ='customer']//p[@class='heading3' and text() ='Customer Registered Successfully!!!']")).isDisplayed());
		// output new customer displayed
		Assert.assertEquals(driver.findElement(CustomerNameRow).getText(),name);
		Assert.assertEquals(driver.findElement(BirthdayRow).getText(),dateofbirth);
		Assert.assertEquals(driver.findElement(AddressRow).getText(),address);
		Assert.assertEquals(driver.findElement(CityRow).getText(),city);
		Assert.assertEquals(driver.findElement(StateRow).getText(),state);
		Assert.assertEquals(driver.findElement(PinRow).getText(),pin);
		Assert.assertEquals(driver.findElement(MobileRow).getText(),phone);
		Assert.assertEquals(driver.findElement(EmailRow).getText(),email);
		
		// get Dynamic Customer ID 
		
		customerID = driver.findElement(By.xpath("//td[text() ='Customer ID']//following-sibling::td")).getText();
	
	}
	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.linkText("Edit Customer")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//check new customer show correct
		
		Assert.assertEquals(driver.findElement(NameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(DateOfBirthTextbox).getAttribute("value"),dateofbirth);
		Assert.assertEquals(driver.findElement(AddressTextArea).getText(), address);
		Assert.assertEquals(driver.findElement(CityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(StateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(PinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(PhoneTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(EmailTextbox).getAttribute("value"), email);
		
		
		//edit customer
		driver.findElement(AddressTextArea).clear();
		driver.findElement(AddressTextArea).sendKeys(newAdress);
		driver.findElement(CityTextbox).clear();
		driver.findElement(CityTextbox).sendKeys(newCity);
		driver.findElement(StateTextbox).clear();
		driver.findElement(StateTextbox).sendKeys(newState);
		driver.findElement(PinTextbox).clear();
		driver.findElement(PinTextbox).sendKeys(newPin);
		driver.findElement(PhoneTextbox).clear();
		driver.findElement(PhoneTextbox).sendKeys(newPhone);
		driver.findElement(EmailTextbox).clear();
		driver.findElement(EmailTextbox).sendKeys(newEmail);

		driver.findElement(Submitbutton).click();
		
		// check edited data successfully
		Assert.assertEquals(driver.findElement(AddressRow).getText(),newAdress);
		Assert.assertEquals(driver.findElement(CityRow).getText(),newCity);
		Assert.assertEquals(driver.findElement(StateRow).getText(),newState);
		Assert.assertEquals(driver.findElement(PinRow).getText(),newPin);
		Assert.assertEquals(driver.findElement(MobileRow).getText(),newPhone);
		Assert.assertEquals(driver.findElement(EmailRow).getText(),newEmail);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}