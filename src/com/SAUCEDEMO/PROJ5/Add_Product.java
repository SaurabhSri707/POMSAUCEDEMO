package com.SAUCEDEMO.PROJ5;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.SAUCEDEMO.PROJ.COMMONTASK;

public class Add_Product extends COMMONTASK
{

	public Add_Product() throws IOException {
		super();
		//this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	public void additem(String prod) throws InterruptedException
	{
	click(prod);

	}
	public void cart_show(String citem)
	{
		click(citem);
		
	}
	/*public String checkdata(String quant)
	{
		return driver.findElement(By.xpath(prop.getProperty(quant))).getText().trim();
	}
	public void chkout(String chk)
	{
		click(chk);
	}*/
}
