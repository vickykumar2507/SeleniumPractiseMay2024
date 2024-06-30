package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeTest
	public void regPageSetup() {
		registeroPage=loginPage.navigateToRegisterPage();
		
	}
	
	@DataProvider
	public Object[][] regPageTest() {
	Object[][] regData=	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	return regData;
	}
	
	
	@Test(dataProvider="regPageTest")
	public void userRegTest(String FN, String LN, 
			String EM, String TEL, String PWD, String CPWD,String subscribe) {
		
		Assert.assertTrue(registeroPage.registerUser( FN,  LN, 
				EM,  TEL,  PWD, CPWD, subscribe));
	}
}
