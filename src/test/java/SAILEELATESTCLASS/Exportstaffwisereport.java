package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Exportstaffwisereport extends SAILEELABaseclass  {
	
	
	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By staffwisereport = AppiumBy.accessibilityId("Staff Wise Report");
	By filter=AppiumBy.accessibilityId("Filter");
	By submitbutton=AppiumBy.accessibilityId("Submit");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By openpdf = By.xpath("//android.widget.EditText"); 

	@Test(priority = 0)
	public void login() throws Exception {

		test = extent.createTest("Login");
		// Common Login Method
		login("swap", "swap");
	}

	@Test(priority = 1)
	public void exportreport() throws Exception {
		test = extent.createTest("Export Purchase Reportt");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(POSreport).click();
		driver.findElement(staffwisereport).click();
		driver.findElement(filter).click();
		driver.findElement(submitButton).click();
		driver.findElement(exportbutton).click();
		driver.findElement(exportaspdf).click();
		
		
		test.pass("Staff Wise report downloaded successfully");

	}

}


