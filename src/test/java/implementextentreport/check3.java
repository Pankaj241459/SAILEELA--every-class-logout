package implementextentreport;

import org.testng.annotations.Test;

import base.SAILEELABaseclass;

import base.sepratetestbase;

public class check3 extends SAILEELABaseclass {

    // ============================================================
    // LOGIN TEST
    // ============================================================

    @Test(priority=1)
    public void loginTestsubbranch() throws Exception {

        test = extent.createTest(
                "Login Test - subbranch"
        );

        // Common Login Method
        login(
                "mbazar1",
                "123"
        );
    }
    
    
    
}