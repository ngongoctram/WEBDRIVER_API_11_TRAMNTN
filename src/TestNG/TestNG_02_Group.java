package TestNG;

import org.testng.annotations.Test;

public class TestNG_02_Group {
	@Test(groups = "user")
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}

	@Test(groups = "user")
	public void TC_03() {
	}

	@Test
	public void TC_04() {
	}

	@Test(groups = "user")
	public void TC_05() {
	}

}
