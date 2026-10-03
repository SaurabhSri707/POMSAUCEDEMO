package com.SAUCEDEMO.PROJ2;

import java.io.IOException;

import org.testng.Assert.ThrowingRunnable;

import com.SAUCEDEMO.PROJ.COMMONTASK;

public class LOGIN extends COMMONTASK{
	public LOGIN() throws IOException {
		super();
	}

	public boolean doLogin(String id, String password) throws InterruptedException {
        sendkeys("id",id);
        Thread.sleep(2000);
        sendkeys("password", password);
        Thread.sleep(2000);
        click("login_btn");
        Thread.sleep(2000);
        return islinkPresent("next");
    }
}
