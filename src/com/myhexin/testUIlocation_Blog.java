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


public class testUIlocation_Blog {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private  String openurl="/channel/blog?tid=blog";
	private static WebDriver driver;
  
  //新闻频道
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Blogfulltext","BlogIndex_Blogfulltext_top","BlogIndex_Blogfulltext_left"})
  public void testBlogIndex_Blogfulltext(String location,String toplocation,String leftlocation) throws InterruptedException
  {	
	  System.out.println("开始测试博客频道的case...");
	  System.out.println("testBlogIndex_Blogfulltext");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'博客全文'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Title","BlogIndex_Title_top","BlogIndex_Title_left"})
  public void testBlogIndex_Title(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Title");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'标题'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Blogger","BlogIndex_Blogger_top","BlogIndex_Blogger_left"})
  public void testBlogIndex_Blogger(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Blogger");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'博主'位置不对");
	  
  }

  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Label","BlogIndex_Label_top","BlogIndex_Label_left"})
  public void testBlogIndex_Label(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Label");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'标签'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Jctj","BlogIndex_Jctj_top","BlogIndex_Jctj_left"})
  public void testBlogIndex_Jctj(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Jctj");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'精彩推荐'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Mrmb","BlogIndex_Mrmb_top","BlogIndex_Mrmb_left"})
  public void testBlogIndex_Mrmb(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Mrmb");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'名人名博'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Jjzt","BlogIndex_Jjzt_top","BlogIndex_Jjzt_left"})
  public void testBlogIndex_Jjzt(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Jjzt");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'经济杂谈'位置不对");
	  
  }
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Scxx","BlogIndex_Scxx_top","BlogIndex_Scxx_left"})
  public void testBlogIndex_Scxx(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Scxx");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'市场信息'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Dpzs","BlogIndex_Dpzs_top","BlogIndex_Dpzs_left"})
  public void testBlogIndex_Dpzs(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Dpzs");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'大盘走势'位置不对");
	  
  }
  
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Jsfx","BlogIndex_Jsfx_top","BlogIndex_Jsfx_left"})
  public void testBlogIndex_Jsfx(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Jsfx");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'技术分析'位置不对");
	  
  }
  @UIMessage(description = "")@Test(groups={"blog"})
  @Parameters({"BlogIndex_Jzfx","BlogIndex_Jzfx_top","BlogIndex_Jzfx_left"})
  public void testBlogIndex_Jzfx(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testBlogIndex_Jzfx");
	  boolean flag=ut.getELementposition(sel, location,toplocation, leftlocation);
	  AssertEquals(driver,flag,true,"博客频道首页'价值分析'位置不对");
	  
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
