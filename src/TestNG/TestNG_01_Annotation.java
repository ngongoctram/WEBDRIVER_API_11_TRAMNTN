package TestNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_01_Annotation {
	@Test()
	public void TC_01() {
		System.out.println("Test case 01: ");
	}

	@Test()
	public void TC_02() {
		System.out.println("Test case 02: ");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("beforeMethod: ");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod: ");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass: ");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass: ");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest: ");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("afterTest: ");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("beforeSuite: ");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite: ");
	}

}
