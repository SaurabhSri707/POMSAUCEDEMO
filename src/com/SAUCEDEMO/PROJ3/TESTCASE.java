package com.SAUCEDEMO.PROJ3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.SAUCEDEMO.PROJ2.LOGIN;
import com.SAUCEDEMO.PROJ5.Add_Product;
import com.SAUCEDEMO.PROJ6.CheckOut;
import com.SAUCEDEMO.PROJ7.Userdetails;
import com.SAUCEDEMO.PROJ8.OrdPayment;
import com.SAUCEDEMO.proj4.ReaddatafromExcel;

public class TESTCASE {
    protected LOGIN login;
    private Add_Product addprod;
    private Add_Product cart_itm;
    private CheckOut item_quant;
    private CheckOut item_name;
    private CheckOut itm_Unit_Price;
    private CheckOut removebtn;
    private CheckOut item_chkout;
    private Userdetails record;
    private Userdetails submitudetails;
    private OrdPayment Orddone;
    
    @BeforeMethod
    public void setup() throws IOException {
        login = new LOGIN();
        login.precondition();          // 1. Opens the browser window
        
        addprod = new Add_Product();
        cart_itm=new Add_Product();
        item_quant=new CheckOut();
        item_name=new CheckOut();
        itm_Unit_Price=new CheckOut();
        item_chkout=new CheckOut();
        record=new  Userdetails();
        removebtn=new CheckOut();
        submitudetails=new Userdetails();
        Orddone=new OrdPayment();
        cart_itm.driver=login.driver;
        addprod.driver = login.driver;
        item_quant.driver=login.driver;
        item_name.driver=login.driver;
        itm_Unit_Price.driver=login.driver;
        removebtn.driver=login.driver;
        item_chkout.driver=login.driver;
        record.driver=login.driver;
        submitudetails.driver=login.driver;
        Orddone.driver=login.driver;// 2. Shares the same browser window with addprod
    }

    // ONLY ONE TEST METHOD HERE NOW
    @Test(dataProvider = "provideData")
    public void loginAndPurchaseWorkflow(String username, String password) throws InterruptedException, IOException {
        
        // Step 1: Log in using the current row's username and password
        boolean loginResult = login.doLogin(username, password);
        Thread.sleep(3000);
        login.pressEnterKey();
        //Thread.sleep(3000);
        // CRITICAL FIX: Tell TestNG to FAIL the test if loginResult is false
        Assert.assertTrue(loginResult, "Login failed for user: " + username);
        
        // This code only runs if the Assert above passes successfully
        System.out.println("Login worked for: " + username + ". Now adding product...");
        addprod.additem("product"); 
        Thread.sleep(5000);
        System.out.println("Product successfully added for: " + username);
        cart_itm.click("cart_item");
        System.out.println(cart_itm.driver.getCurrentUrl());
        Thread.sleep(3000);
        String value=item_quant.checkdata("quantity");
        System.out.println("The live product quantity inside the cart is: " + value);
       // System.out.println(item_quant.checkdata("quantity")); 
       // String Pname=item_name.checkItmName("Chck_itm_name");
       // System.out.println("Item name :"+Pname);
        //Thread.sleep(3000);
        String ActualIname=item_name.checkItmName("Chck_itm_name");
        String ExpectedIname="Sauce Labs Onesie";
        Assert.assertEquals(ActualIname,ExpectedIname,"Item name not mactched in the chekout page");
        System.out.println("Expected :" + ExpectedIname + "Actual :" + ActualIname);
        Thread.sleep(3000);
		String ActualPrice=itm_Unit_Price.checkPrice("Chk_Unit_Price");
		String ExpectedPrice="$7.99";
		try
		{
		Assert.assertEquals(ActualPrice,ExpectedPrice,"Unit Price not matched");
		System.out.println("Expected :"+ ExpectedPrice + "Actual:"+ ActualPrice);
		}
		catch(AssertionError e)
		{
			removebtn.click("removebtn");
			throw e;
			
		}
		
		Thread.sleep(2000);
        item_chkout.click("checkout");
        Thread.sleep(2000);
        System.out.println(item_chkout.driver.getCurrentUrl());
        
       File srcFile = ((TakesScreenshot) login.driver).getScreenshotAs(OutputType.FILE);

        // 5. Use native Java NIO to save the file (No Apache Commons needed)
       File destFile = new File("Final_Order.png");
       Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Screenshot saved to project root directory: " + destFile.getAbsolutePath());

        record.PutuserDetails();
        Thread.sleep(2000);
        submitudetails.click("submit1");
        Thread.sleep(3000);
        System.out.println(submitudetails.driver.getCurrentUrl());
        Orddone.finalorder("OrderSubmit");
        Thread.sleep(2000);
        

    }
    	
    

    @AfterMethod
    public void tearDown() {
        if (login != null && login.driver != null) {
            login.driver.quit(); // Closes the browser cleanly after each individual row
        }
    }
    
    @DataProvider
    public Object[][] provideData() throws IOException {
        return ReaddatafromExcel.readexceldata("Sheet2");
    }
}
