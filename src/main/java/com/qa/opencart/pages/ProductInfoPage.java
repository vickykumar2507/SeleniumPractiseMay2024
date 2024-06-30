package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.appConstants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By addCartMsg=By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public ProductInfoPage(WebDriver driver) {
		 this.driver = driver;
		 eleUtil= new ElementUtil(driver);
	 }
	
	public String getProductHeader() {
		String productHeaderVal=eleUtil.doElementGetText(productHeader);
		System.out.println("product header:"+productHeaderVal);
		return productHeaderVal;
	}
	
	public int getProductImagesCount() {
		int imagesCount=eleUtil.waitForElementsVisible(productImages, AppConstants.Default_MEDIUM_TIME_OUT).size();
		System.out.println("product image count:"+imagesCount);
		return imagesCount;
	}
	
	public void enterQuantity(int qty) {
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
		}
	
	public String clickAddToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMsg=eleUtil.waitForElementVisible(addCartMsg, AppConstants.Default_MEDIUM_TIME_OUT).getText();
		System.out.println("message is:"+successMsg);
		return successMsg;
	}
	public Map<String, String> getProductInfo() {
	
		
		//1. Header
		// productInfoMap= new HashMap<String,String>(); does not maintain order
		 //productInfoMap= new LinkedHashMap<String,String>(); //maintain order but not sorted
		 
		productInfoMap= new TreeMap<String,String>(); // maintain order & sorted
		productInfoMap.put("ProductName", getProductHeader()); 
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}
	
	private void getProductMetaData() {
		/*
		 Brand: Apple
	Product Code: Product 18
	Reward Points: 800
	Availability: In Stock
		 */
				List<WebElement> metaList=eleUtil.getElements(productMetaData);
				for(WebElement e :metaList) {
					String meta=e.getText();
					String[] metaInfo=meta.split(":");
					String key=metaInfo[0].trim();
					String value=metaInfo[1].trim();
					productInfoMap.put(key, value);
				}
	}
	
	private void getProductPriceData() {
		/*
		 $2,000.00
Ex Tax: $2,000.00
		 */
		
		//3.price
		List<WebElement> priceList=eleUtil.getElements(productPriceData);
		
		String price=priceList.get(0).getText();
		String exTax=priceList.get(1).getText();
		String exTaxVal=exTax.split(":")[1].trim();
		productInfoMap.put("product price", price);
		productInfoMap.put("exTax", exTaxVal);
	}
	
	
	
}
