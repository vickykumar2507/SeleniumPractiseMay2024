package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic :100 design login for open cart app")
@Story("User story login 101: design login page feature for open cart app")
public class LoginPageTest extends BaseTest{

	@Severity(SeverityLevel.TRIVIAL)
	@Description("Getting the title of the page..tester:Vicky")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Getting the url of the page..tester:Vicky")
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actURL=loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Checking forget pwd link..tester:Vicky")
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description("Checking user is able to login to app with correct un & pwd..tester:Vicky")
	@Test(priority = 4)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutExist());
	}
}
