
package implementextentreport;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.sepratetestbase;

public class dataprovider extends sepratetestbase {

    // ==============================
    // Locators
    // ==============================

    By token = By.xpath("//android.widget.EditText");

    By submitButton = By.xpath(
            "//android.widget.Button[@content-desc='Submit']"
    );

    By username = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText[1]"
           
    );

    By password = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText[2]");
           
  

    By loginButton = By.xpath(
            "//android.view.View[@content-desc=\"Login\"]"
    );

    // ==============================
    // Test Data
    // ==============================

    String validtoken = "MEATYNS0626";
    String validUsername = "mbazar";
    String validPassword = "Pankaj@123";

    // ==============================
    // Data Provider
    // ==============================

    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][] {
            { validtoken, validUsername, validPassword }
        };
    }

    // ==============================
    // Login Test
    // ==============================

    @Test(dataProvider = "loginData")
    public void loginTest(
            String validtoken,
            String validUsername,
            String validPassword) {

        test = extent.createTest("Login Test with DataProvider");

        // Enter Token
        test.info("Entering Token: " + validtoken);
        driver.findElement(token).click();
        driver.findElement(token).sendKeys(validtoken);

        // Click Submit
        test.info("Clicking Submit Button");
        driver.findElement(submitButton).click();

        // Enter Username
        test.info("Entering Username: " + validUsername);
        driver.findElement(username).click();
        driver.findElement(username).sendKeys(validUsername);

        // Enter Password
        test.info("Entering Password");
        driver.findElement(password).click();
        driver.findElement(password).sendKeys(validPassword);

        // Click Login
        test.info("Clicking Login Button");
        driver.findElement(loginButton).click();

        test.pass("Login test completed successfully");
    }
}