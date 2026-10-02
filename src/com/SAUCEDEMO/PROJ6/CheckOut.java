package com.SAUCEDEMO.PROJ6;

import java.io.IOException;

import org.openqa.selenium.By;

import com.SAUCEDEMO.PROJ.COMMONTASK;

public class CheckOut extends COMMONTASK {

	public CheckOut() throws IOException {
		super();
	}
	public String checkdata(String quant)
	{
		return driver.findElement(By.xpath(prop.getProperty(quant))).getText().trim();
	}
	public void chkout(String chk)
	{
		click(chk);
	}
	public String checkItmName(String iname)
	{
		return driver.findElement(By.xpath(prop.getProperty(iname))).getText().trim();
	}
	public String checkPrice(String Price)
	{
		return driver.findElement(By.xpath(prop.getProperty(Price))).getText();
	}
	
	
	
	

}
