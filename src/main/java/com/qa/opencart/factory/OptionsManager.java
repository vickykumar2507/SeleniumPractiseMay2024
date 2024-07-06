package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	//creating constructor of the class to get properties reference. otherwose it will throw null
	public OptionsManager(Properties prop) {
		this.prop=prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origin=*");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
		co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--incognito");
			}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
		fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--incognito");
			}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
		eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			eo.addArguments("--incognito");
			}
		return eo;
	}
}
