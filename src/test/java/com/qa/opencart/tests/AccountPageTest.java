package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;

public class AccountPageTest extends BaseTest{
	
	
	@BeforeClass
	public void accPageSetup() {
		 accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		}
	
	@Test
	public void accPageTitleTest() {
		String actTitle=accPage.getAcctPageTitle();
		Assert.assertEquals(actTitle, AppConstants.Accounts_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageUrlTest() {
		String actURL=accPage.getCurrentURL();
		Assert.assertTrue(actURL.contains(AppConstants.Accounts_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void isLogoutExistTest() {
		Assert.assertTrue(accPage.isLogoutExist());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		List<String> actPageHeaderList=accPage.getAcctHeaderList();
		System.out.println("Account page headers are:"+actPageHeaderList);
		Assert.assertEquals(actPageHeaderList.size(), AppConstants.ACCOUNT_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersValueTest() {
		List<String> actPageHeaderList=accPage.getAcctHeaderList();
		System.out.println("Account page headers are:"+actPageHeaderList);
		
		Assert.assertEquals(actPageHeaderList, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADER_LIST);
		}
	
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			
			{"Macbook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
			
		};
	}
	
	
	@Test(dataProvider ="getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage=accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getTotalSearchProductsCount()>0);
		
	}
	
	@DataProvider
	public Object[][] getProductTestData(){
		return new Object[][] {
			
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			
			{"iMac","iMac"},
			
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"},
			
		};
	}
	@Test(dataProvider ="getProductTestData")
	public void searchProductTest(String searchKey,String productName) {
		searchPage=accPage.performSearch(searchKey);
		if(searchPage.getTotalSearchProductsCount()>0) {
			productInfoPage=searchPage.selectProduct(productName);
			String actProductHeader=productInfoPage.getProductHeader();
			Assert.assertEquals(actProductHeader, productName);
		}
		
	}
	

}
