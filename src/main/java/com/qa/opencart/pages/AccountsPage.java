package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By acctHeaders=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
	 public AccountsPage(WebDriver driver) {
		 this.driver = driver;
		 eleUtil= new ElementUtil(driver);
	 }
	 
	 public String getAcctPageTitle() {
		String title=eleUtil.waitForTitleIsAndFetch(AppConstants.Default_SHORT_TIME_OUT, AppConstants.Accounts_PAGE_TITLE_VALUE);
		 System.out.println("Account page title:"+title);
		 return title;
	 }
	 
	 public String getCurrentURL() {
		 //String pageURL=driver.getCurrentUrl();
		 String pageURL=eleUtil.waitForURLContainsAndFetch(AppConstants.Default_SHORT_TIME_OUT, AppConstants.Accounts_PAGE_URL_FRACTION_VALUE);
		 System.out.println("Page url is:"+pageURL);
		 return pageURL;
	 }
	 
	 public boolean isLogoutExist() {
		// return driver.findElement(logoutLink).isDisplayed();
		 return eleUtil.waitForElementVisible(logoutLink,AppConstants.Default_SHORT_TIME_OUT).isDisplayed();
	 }
	 
	 public boolean isSearchExist() {
		// return driver.findElement(search).isDisplayed();
		 
		 return eleUtil.waitForElementVisible(search, AppConstants.Default_SHORT_TIME_OUT).isDisplayed();
	 }
	 
	 public List<String> getAcctHeaderList() {
		 
		 // List<WebElement> list=driver.findElements(acctHeaders);
		 List<WebElement> list=	 eleUtil.waitForElementsVisible(acctHeaders, AppConstants.Default_SHORT_TIME_OUT);
		 List<String> accHeaderList= new ArrayList<String>();
		 
		 for(WebElement e : list ) {
			 String text = e.getText();
			 accHeaderList.add(text);
		 }
		 return accHeaderList;
	 }
	 
	 public SearchPage performSearch(String searchKey) {
		 
		 if(isSearchExist()) {
			 eleUtil.doSendKeys(search, searchKey);
			 eleUtil.doClick(searchIcon);
			 return new SearchPage(driver);
		 }else {
			 System.out.println("Search field is not present");
		 }
		 return null;
		 
		 
	 }

}
