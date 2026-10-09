package implementextentreport;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.SAILEELABaseclass;

import base.sepratetestbase;

public class changepassword extends SAILEELABaseclass {

    // ============================================================
    // LOGIN TEST
    // ============================================================
	
	By oldpasswordField = By.xpath("//android.widget.EditText[@content-desc=\"Old Password\"]");
	By newpasswordField = By.xpath("//android.widget.EditText[@content-desc=\"New Password\"]");
	By changePasswordButton = By.xpath("//android.view.View[@content-desc=\"Change Password\"]");
	
	
	String oldpassword = "Pankaj@123";
    String newpassword = "Pankaj@1234";
   
    @Test(priority=1)
    public void loginTest() throws Exception {

        test = extent.createTest(
                "Login Test - mbazar"
        );

        // Common Login Method
        login(
                "mbazar",
                "Pankaj@123"
        );
    }
    
    @DataProvider(name = "changePasswordData")
    public Object[][] loginData() {

        return new Object[][] {
            { oldpassword, newpassword }
        };
        }
    
        @Test(dataProvider = "changePasswordData", priority=2)
    
    public void changePasswordTest(
				String oldpassword,
				String newpassword) {

			test = extent.createTest("Change Password Test with DataProvider");

			// Change Password Method
			driver.findElement(oldpasswordField).sendKeys(oldpassword);
			driver.findElement(newpasswordField).sendKeys(newpassword);
			driver.findElement(changePasswordButton).click();
			
			test.pass("Password changed successfully with old password: " + oldpassword + " and new password: " + newpassword);
	
    
    
        }} 
       