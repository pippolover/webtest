package com.myhexin;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import static com.myhexin.common.Assert.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.Selenium;

public class testUIlocation_Weibo {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private  String openurl="/channel/weibo/?tid=weibo";
	private static WebDriver driver;



   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_jiepan","testweiboIndex_jiepan_top","testweiboIndex_jiepan_left"})
   public void testweiboIndex_jiepan(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("开始测试微博频道的case...");
	  System.out.println("testweiboIndex_jiepan");
	  System.out.println(sel.getLocation());
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'解盘'位置不对");
	  
}


   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_jigou","testweiboIndex_jigou_top","testweiboIndex_jigou_left"})
   public void testweiboIndex_jigou(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("testweiboIndex_jigou");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'机构'位置不对");
	  
}

   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_minjiangaoshou","testweiboIndex_minjiangaoshou_top","testweiboIndex_minjiangaoshou_left"})
   public void testweiboIndex_minjiangaoshou(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("testweiboIndex_minjiangaoshou");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'民间高手'位置不对");
	  
}

 
   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_meitihui","testweiboIndex_meitihui_top","testweiboIndex_meitihui_left"})
   public void testweiboIndex_meitihui(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("testweiboIndex_meitihui");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'媒体汇'位置不对");

}
   
   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_mingrentang","testweiboIndex_mingrentang_top","testweiboIndex_mingrentang_left"})
   public void testweiboIndex_mingrentang(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("testweiboIndex_mingrentang");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'名人堂'位置不对");

}   
   
   @UIMessage(description = "")@Test(groups={"weibo"})
   @Parameters({"testweiboIndex_fenxishi","testweiboIndex_fenxishi_top","testweiboIndex_fenxishi_left"})
   public void testweiboIndex_fenxishi(String location,String toplocation,String leftlocation) throws InterruptedException
{	
	  System.out.println("testweiboIndex_fenxishi");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"微博频道首页'分析师'位置不对");

}     
   

    @BeforeTest
	// 测试开始之前要执行这个
	@Parameters({ "selenium.host", "selenium.port", "selenium.browser",
			"selenium.url"})
	public void beforeTest(String host, int port, String browser, String url) throws MalformedURLException {
	System.out.println("Before test");
	System.out.println(host);
//	Integer Dport = Integer.parseInt(port);
//	sel = new DefaultSelenium(host, Dport, browser, url);
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