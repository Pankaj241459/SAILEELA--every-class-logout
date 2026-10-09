package SAILEELATESTCLASS;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;
import io.appium.java_client.AppiumBy;

public class Exportexpensesreport extends SAILEELABaseclass {

	By menubar = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
	By POSreport = AppiumBy.accessibilityId("POS Report");
	By Expensereport = AppiumBy.accessibilityId("Expense Report");
	By exportbutton = AppiumBy.accessibilityId("Export");
	By exportaspdf = AppiumBy.accessibilityId("Export as PDF");
	By openpdf = AppiumBy.accessibilityId("Expense Reports");

	@Test(priority = 0)
	public void login() throws Exception {

		test = extent.createTest("Login");
		// Common Login Method
		login("swap", "swap");
	}

	@Test(priority = 1)
	public void exportreport() throws Exception {
		test = extent.createTest("Export expense Reportt");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(menubar).click();
		driver.findElement(POSreport).click();
		driver.findElement(Expensereport).click();

		driver.findElement(exportbutton).click();
		driver.findElement(exportaspdf).click();
		driver.findElement(openpdf).click();

		Thread.sleep(10000);

		driver.navigate().back();

	}

}
