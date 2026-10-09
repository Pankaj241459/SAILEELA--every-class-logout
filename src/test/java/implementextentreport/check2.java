package implementextentreport;

import org.testng.annotations.Test;

import base.SAILEELABaseclass;

import base.sepratetestbase;

public class check2 extends SAILEELABaseclass {

    // ============================================================
    // LOGIN TEST
    // ============================================================

    @Test(priority=1)
    public void loginTestmainbranch() throws Exception {

        test = extent.createTest(
                "Login Test - mainbranch"
        );

        // Common Login Method
        login(
                "swap",
                "swap"
        );
    }
    
    
    
}