package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	//1. private by locator
	
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPWDLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2. page constructor
	 public LoginPage(WebDriver driver) {
		 this.driver = driver;
		 eleUtil= new ElementUtil(driver);
	 }
	 
	 //3.page actios/methods
	 
	 @Step("....getting the login page title...")
	 public String getLoginPageTitle() {
		
		String title= eleUtil.waitForTitleContainsAndFetch(AppConstants.Default_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		 System.out.println("Login page title:"+title);
		 return title;
	 }
	 
	 @Step("....getting the login page url...")
	 public String getLoginPageURL() {
		
		 String url=eleUtil.waitForURLContainsAndFetch(AppConstants.Default_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		 
		 System.out.println("Login page url:"+url);
		 return url;
	 }
	 
	 @Step("....getting the forgot pwd link...")
	 public boolean isForgotPwdLinkExist() {
		// return driver.findElement(forgotPWDLink).isDisplayed();
		 
		 return eleUtil.waitForElementVisible(forgotPWDLink, AppConstants.Default_MEDIUM_TIME_OUT).isDisplayed();
	 }
	 
	 //public AccountsPage doLogin(String un, String pwd) {
		// driver.findElement(emailID).sendKeys(un);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(loginBtn).click();  
		 //wen clicked on loginBtn a new page is opened 
		
		 // this is called tdd (test driven development approach) like page chaining model
		 
		 // }
	 @Step("login with username: {0} and password:{1}")
		 public AccountsPage doLogin(String un, String pwd) {
			
			 System.out.println("App credential is:"+un+":"+pwd );
			 eleUtil.waitForElementVisible(emailID, AppConstants.Default_MEDIUM_TIME_OUT).sendKeys(un);
			 eleUtil.doSendKeys(password, pwd);
			 eleUtil.doClick(loginBtn);
			 
			 return new AccountsPage(driver);
		 }
		 
	 @Step("Navigating to registration page") 
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
}
