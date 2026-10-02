package com.SAUCEDEMO.PROJ;

import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class COMMONTASK {
	public WebDriver driver;
	public Properties prop;
	
	 public COMMONTASK() throws IOException {
	        // Load xpaths properties file safely
	        FileInputStream ip = new FileInputStream("C:\\Users\\user\\workspace\\POMSAUCEDEMO\\test-output\\xpaths.properties");
	        prop = new Properties();
	        prop.load(ip);
	    }
	public void precondition()
	{
		System.setProperty("webdriver.edge.driver", "C:\\Users\\user\\Documents\\driver\\edgedriver_win64 (6)\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
	}
	 public void sendkeys(String xpkey, String value) {
	        driver.findElement(By.xpath(prop.getProperty(xpkey))).sendKeys(value);
	    }

	    public void click(String xpth) {
	        driver.findElement(By.xpath(prop.getProperty(xpth))).click();
	    }
	 // Add this method inside your COMMONTASK class body
	    public void pressEnterKey() {
	        driver.findElement(By.xpath("//body")).sendKeys(Keys.ENTER);
	    }
	    
	    public boolean islinkPresent(String xpathKey) {
	        // 1. Get the raw xpath expression from your properties file
	        String xpathValue = prop.getProperty(xpathKey);
	        
	        // 2. Loop for 3 seconds to give the website time to load the dashboard
	        for (int i = 0; i < 6; i++) {
	            try {
	                // If the element is found and displayed on screen, return true immediately
	                if (driver.findElement(By.xpath(xpathValue)).isDisplayed()) {
	                	System.out.println(driver.getCurrentUrl());
	                    return true;
	                }
	            } catch (Exception e) {
	                // If not found yet, wait 500 milliseconds (0.5 seconds) and try again
	                try { Thread.sleep(500); } catch (InterruptedException ie) { }
	            }
	        }
	        // If it's still not found after 3 seconds, it's definitely a failed login row
	        return false;
	    }

	}


	


