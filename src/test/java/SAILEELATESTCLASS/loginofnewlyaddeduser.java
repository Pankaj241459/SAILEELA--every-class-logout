package SAILEELATESTCLASS;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class loginofnewlyaddeduser extends SAILEELABaseclass {
	
	By avdhutpawar=AppiumBy.accessibilityId("AvdhutPawar ");
	By logout=AppiumBy.accessibilityId("Log Out ");
	
	
	
	
	@Test(priority = 0)
	public void login() throws Exception {

		test = extent.createTest("Login");
		// Common Login Method
		login("avdhut", "avdhut@123");
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

//	@Test(priority = 1)
//	public void logout() throws Exception {
//
//		test = extent.createTest("logout of Avdhut pawar");
//		// Common Login Method
//		driver.findElement(avdhutpawar).click();
//		driver.findElement(logout).click();
//	}
//	

}
