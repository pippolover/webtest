package com.myhexin;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import static org.testng.Assert.assertEquals;
import static com.myhexin.common.Assert.*;

public class testUIlocation_Fengyunbang {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private  String openurl="/moreinfo/fengyun";
	private static WebDriver driver;
	
	
	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_main","testfengyunbang_main_top","testfengyunbang_main_left"})
	public void testfengyunbang_main(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("开始测试风云榜频道的case...");
		  System.out.println("testfengyunbang_main");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"风云榜的整体位置");
		  
	}
	   
	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_mtzzfyb","testfengyunbang_mtzzfyb_top","testfengyunbang_mtzzfyb_left"})
	public void testfengyunbang_mtzzfyb(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_mtzzfyb");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"媒体转载风去榜");
		  
	}	   
	   
	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_mrrmgjc","testfengyunbang_mrrmgjc_top","testfengyunbang_mrrmgjc_left"})
	public void testfengyunbang_mrrmgjc(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_mrrmgjc");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"每日热门关键词");
		  
	}		   

	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_byplfyb","testfengyunbang_byplfyb_top","testfengyunbang_byplfyb_left"})
	public void testfengyunbang_byplfyb(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_byplfyb");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"博友评论风云榜");
		  
	}	

	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_yzrdgjz","testfengyunbang_yzrdgjz_top","testfengyunbang_yzrdgjz_left"})
	public void testfengyunbang_yzrdgjz(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_yzrdgjz");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"一周热点关键字");
		  
	}
	
	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_wmyxfyb","testfengyunbang_wmyxfyb_top","testfengyunbang_wmyxfyb_left"})
	public void testfengyunbang_wmyxfyb(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_wmyxfyb");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"网民影响风云榜");
		  
	}		   

	@UIMessage(description = "")@Test(groups={"fenyunbang"})
	@Parameters({"testfengyunbang_sszkgjz","testfengyunbang_sszkgjz_top","testfengyunbang_sszkgjz_left"})
	public void testfengyunbang_sszkgjz(String location,String toplocation,String leftlocation) throws InterruptedException
	{	
		  System.out.println("testfengyunbang_sszkgjz");
		  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
		  AssertEquals(driver,flag,true,"上升最快关键字");
		  
	}
	   
	    @BeforeTest
		// 测试开始之前要执行这个
		@Parameters({ "selenium.host", "selenium.port", "selenium.browser",
				"selenium.url"})
		public void beforeTest(String host, int port, String browser, String url) throws MalformedURLException {
		System.out.println("Before test");
		System.out.println(host);
//		Integer Dport = Integer.parseInt(port);
//		sel = new DefaultSelenium(host, Dport, browser, url);
		// sel = new DefaultSelenium(host, Dport,
		// "C:\\Program Files\\Internet Explorer\\iexplore.exe", url);
		// sel = new DefaultSelenium(host, Dport, "*iexplore", url);
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://172.20.23.95:4444/wd/hub"), capability);
	    sel = new WebDriverBackedSeleniumWrapper(driver,url);
	    ut=new util();
	    sel.open(openurl);
	    driver.manage().window().maximize();
		//sel.start();
		sel.useXpathLibrary("javascript-xpath");
		sel.setTimeout(TIMEOUT);
		
		}

		@AfterTest
		// 每个测试结束后运行这个
		public void afterTest() {
			System.out.println("After test");
			sel.stop();
		}
	}
	
	

