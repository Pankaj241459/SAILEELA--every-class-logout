package base;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Utility.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SAILEELABaseclass {

	// ============================================================
	// DRIVER
	// ============================================================

	protected AndroidDriver driver;

	// ============================================================
	// EXTENT REPORT
	// ============================================================

	protected static ExtentReports extent;

	protected ExtentTest test;

	// ============================================================
	// COMMON LOGIN LOCATORS
	// ============================================================

	protected By token = By.xpath("//android.widget.EditText");

	protected By submitButton = By.xpath("//android.widget.Button[@content-desc='Submit']");

	By username = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText[1]");

	By password = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText[2]");

	By loginButton = By.xpath("//android.view.View[@content-desc=\"Login\"]");
	By allowpermission = By.id("com.android.permissioncontroller:id/permission_allow_button");
	By onlyclickonprofileicon = By.xpath(
			"//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View");

	By profileicon = AppiumBy.accessibilityId("Swapnil Thakur");

	By logoutbutton = AppiumBy.accessibilityId("Log Out");
	By Yes = AppiumBy.accessibilityId("Yes");

	// ============================================================
	// COMMON TOKEN
	// ============================================================

	protected String validToken = "RETAILUAE726";

	// ============================================================
	// START EXTENT REPORT
	// ============================================================

	@BeforeSuite
	public void startReport() {

		extent = ExtentReportManager.getReportObject();

		System.out.println("Extent Report initialized");
	}

	// ============================================================
	// START APPIUM
	// ============================================================

	@BeforeClass
	public void setup() throws Exception {

		UiAutomator2Options options = new UiAutomator2Options();

		options.setPlatformName("Android");

		options.setAutomationName("UiAutomator2");

		options.setDeviceName("0123456789ABCDEF");

		options.setAppPackage("com.adhik.retail_pos");

		options.setAppActivity("com.adhik.retail_pos.MainActivity");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

		System.out.println("Appium Driver started");
	}

	// ============================================================
	// COMMON LOGIN METHOD
	// ============================================================

	protected void login(String usernameValue, String passwordValue) throws Exception {

		try {

			// ----------------------------------------------------
			// ENTER TOKEN
			// ----------------------------------------------------

			test.info("Entering Token: " + validToken);

			driver.findElement(token).click();

			driver.findElement(token).sendKeys(validToken);

			// ----------------------------------------------------
			// CLICK SUBMIT
			// ----------------------------------------------------

			test.info("Clicking Submit Button");

			driver.findElement(submitButton).click();

			// ----------------------------------------------------
			// ENTER USERNAME
			// ----------------------------------------------------
			Thread.sleep(3000); // Wait for the username field to be visible
			test.info("Entering Username: " + usernameValue);

			driver.findElement(username).click();

			driver.findElement(username).sendKeys(usernameValue);

			// ----------------------------------------------------
			// ENTER PASSWORD
			// ----------------------------------------------------

			test.info("Entering Password");

			driver.findElement(password).click();

			driver.findElement(password).sendKeys(passwordValue);

			// ----------------------------------------------------
			// CLICK LOGIN
			// ----------------------------------------------------

			test.info("Clicking Login Button");

			driver.findElement(loginButton).click();

			test.info("Clicking Allow Permission Button");
			// driver.findElement(allowpermission).click();

			// ----------------------------------------------------
			// LOGIN SUCCESS
			// ----------------------------------------------------

			test.pass("Login completed successfully " + "for user: " + usernameValue);

		} catch (Exception e) {

			test.fail("Login failed for user: " + usernameValue + " | Error: " + e.getMessage());

			throw e;
		}
	}

	// ============================================================
	// AFTER EVERY TEST
	// ============================================================

	@AfterMethod
	public void afterTest(ITestResult result) {

		try {

			// ----------------------------------------------------
			// SCREENSHOT
			// ----------------------------------------------------

			if (driver != null && test != null) {

				String screenshotPath = captureScreenshot();

				attachScreenshotToReport(screenshotPath);
			}

			// ----------------------------------------------------
			// PASS
			// ----------------------------------------------------

			if (result.getStatus() == ITestResult.SUCCESS) {

				if (test != null) {

					test.log(Status.PASS, "Test Passed");
				}
			}

			// ----------------------------------------------------
			// FAIL
			// ----------------------------------------------------

			else if (result.getStatus() == ITestResult.FAILURE) {

				if (test != null) {

					test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
				}
			}

			// ----------------------------------------------------
			// SKIP
			// ----------------------------------------------------

			else if (result.getStatus() == ITestResult.SKIP) {

				if (test != null) {

					test.log(Status.SKIP, "Test Skipped");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================================================
	// CAPTURE SCREENSHOT
	// ============================================================

	public String captureScreenshot() {

		String folderPath = System.getProperty("user.dir") + File.separator + "screenshots";

		File folder = new File(folderPath);

		if (!folder.exists()) {

			folder.mkdirs();
		}

		String screenshotPath = folderPath + File.separator + "Screenshot_" + System.currentTimeMillis() + ".png";

		try {

			TakesScreenshot screenshot = (TakesScreenshot) driver;

			byte[] source = screenshot.getScreenshotAs(OutputType.BYTES);

			Path path = Paths.get(screenshotPath);

			Files.write(path, source);

			System.out.println("Screenshot saved: " + screenshotPath);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return screenshotPath;
	}

	// ============================================================
	// ATTACH SCREENSHOT TO EXTENT REPORT
	// ============================================================

	public void attachScreenshotToReport(String screenshotPath) {

		try {

			byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));

			String base64Screenshot = Base64.getEncoder().encodeToString(screenshotBytes);

			test.info("Execution Screenshot",

					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ============================================================
	// CLOSE APPIUM
	// ============================================================

	@AfterClass

	public void logoutAndCloseAppium() {

		try {

			// ----------------------------------------------------
			// LOGOUT
			// ----------------------------------------------------
			test = extent.createTest("Logout ");
			test.info("Logging out");

			driver.findElement(onlyclickonprofileicon).click();
			Thread.sleep(2000);
			driver.findElement(profileicon).click();

			Thread.sleep(2000); // Wait for the profile menu to open
			driver.findElement(logoutbutton).click();

			driver.findElement(Yes).click();

			test.pass("Logout completed successfully");

		} catch (Exception e) {

			test.fail("Logout failed: " + e.getMessage());

			e.printStackTrace();

		} finally {

			// ----------------------------------------------------
			// CLOSE APPIUM DRIVER
			// ----------------------------------------------------

			if (driver != null) {

				driver.quit();

				System.out.println("Appium Driver closed");
			}
		}
	}

	// ============================================================
	// FLUSH EXTENT REPORT
	// ============================================================

	@AfterSuite
	public void finishReport() {

		if (extent != null) {

			extent.flush();

			System.out.println("Extent Report generated successfully");
		}
	}
}