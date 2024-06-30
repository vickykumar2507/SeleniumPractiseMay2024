package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static String highlight;
	public static 	ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver> ();
	
	/**
	 * this method is initializing the driver
	 * @param browserName
	 * @return this returns driver
	 */
	public WebDriver initDriver(Properties prop) {
		
		optionsManager=new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		
		String browserName=prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser name is:"+browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));	
			}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else {
			System.out.println("Please enter the correct browser name..");
			throw new FrameworkException("no browser found exception");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
		}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * reads properties file
	 * @return
	 */
	public Properties initProp() {
		
		//mvn clean install -Denv="stage"
		//mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName=System.getProperty("env");
		System.out.println("Running testcase on the env: "+envName);
		try {
		if(envName ==null) {
			System.out.println("no env is passed..Running test in qa environment");
			ip= new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		
		else {
			switch(envName.toLowerCase().trim()) {
			case "qa":
				ip= new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip= new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stg":
				ip= new FileInputStream("./src/test/resources/config/stg.config.properties");
				break;
			case "prod":
			ip= new FileInputStream("./src/test/resources/config/config.properties");
			break;
			default:
				System.out.println("wrong env is passed...no need to run the testcase");
				throw new FrameworkException("wrong env is passed");
				//break;
			}
			
		}
		}
		catch(FileNotFoundException e) {
			
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot() {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
}
