package TestNG;

import org.testng.annotations.Test;

public class TestNG_03_Priority_Skip {
	@Test(priority = 2)
	public void TC_01() {
	}

	@Test(priority = 3)
	public void TC_02() {
	}

	@Test
	public void TC_03() {
	}

	@Test(priority = 5, enabled = false)
	public void TC_04() {
	}

	@Test(priority = 4)
	public void TC_05() {
	}

}
