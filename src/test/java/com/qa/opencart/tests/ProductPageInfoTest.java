package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{

	
	@BeforeClass
	public void prductInfoPageSetup() {
		 accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		}
	
	
	@DataProvider
	public Object[][] getProductImagesTestData(){
		return new Object[][] {
			
			{"Macbook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},	
		};
	}
	
	
	@Test(dataProvider ="getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
	
		searchPage=accPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		int actImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
		
	}
	@Test
	public void productInformationTest() {
		searchPage=accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProdInfo=productInfoPage.getProductInfo();
		System.out.println(actProdInfo);
		softAssert.assertEquals(actProdInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProdInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProdInfo.get("product price"), "$2,000.00");
		softAssert.assertEquals(actProdInfo.get("ProductName"), "MacBook Pro");
		softAssert.assertAll();
	}
	
	@Test void addToCartTest() {
		searchPage=accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(3);
		String actSuccessMsg=productInfoPage.clickAddToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actSuccessMsg.contains("Success"));
		softAssert.assertTrue(actSuccessMsg.contains("MacBook"));
		softAssert.assertAll();
	}
	
}
