package com.SAUCEDEMO.PROJ7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;

import com.SAUCEDEMO.PROJ.COMMONTASK;

public class Userdetails extends COMMONTASK {

	public Userdetails() throws IOException {
		super();
		
	}
	public void PutuserDetails() throws InterruptedException
	{
		try {
	        // Updated path for non-Maven, plain Eclipse projects
	        String filePath = "src/User_for_payment.txt";
	        List<String> lines = Files.readAllLines(Paths.get(filePath));

	        if (!lines.isEmpty()) {
	            String[] data = lines.get(0).split(",");
	            
	            // Clean up any extra spaces
	            String username =data[0].trim();
	            String password =data[1].trim();
	            String zipcode=data[2].trim();

	            // Type into your fields
	            driver.findElement(By.xpath(prop.getProperty("Usrdetail1"))).sendKeys(username);
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(prop.getProperty("Usrdetail2"))).sendKeys(password);
	            Thread.sleep(2000);
	            driver.findElement(By.xpath(prop.getProperty("Usrdetail3"))).sendKeys(zipcode);
	            
	            System.out.println("Successfully submitted data for user: " + username);
	            
	        }
	       
	    } catch (IOException ioException) {
	        System.out.println("Failed to read the file from Eclipse: " + ioException.getMessage());
	    }
	}
	 public void submituserdetails(String submit)
 	{
 		click(submit);
 	}
	}

