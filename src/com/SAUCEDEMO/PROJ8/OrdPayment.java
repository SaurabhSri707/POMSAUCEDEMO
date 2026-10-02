package com.SAUCEDEMO.PROJ8;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.SAUCEDEMO.PROJ.COMMONTASK;

public class OrdPayment extends COMMONTASK{
	public OrdPayment() throws IOException {
		super();
		
	}
	public void finalorder(String order)
	{
		click(order);
	}



}

