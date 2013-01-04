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

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.Selenium;
import static com.myhexin.common.Assert.*;

public class testUIlocation_Gonggao {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private  String openurl="/?tid=pubnote&amp;ts=1&amp;qs=1";
	private static WebDriver driver;
  
  //新闻频道
  
	@UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"common_zhongyaoxinwen_title","info_zhongyaoxinwen_title_top","info_zhongyaoxinwen_title_left"})
	  public void testGonggao_zhongyaoxinwen_title(String location,String toplocation,String leftlocation) throws Exception
	  {	
		System.out.println("开始测试公告频道.....");
		  System.out.println("testGonggao_zhongyaoxinwen_title");
		  sel.windowMaximize();
		  ut.QueryResult(sel,"浦发银行");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道里的'重要新闻标题'位置不对");	  
	  }
	  
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"common_zhongyaoxinwen_flash","info_zhongyaoxinwen_flash_top","info_zhongyaoxinwen_flash_left"})
	  public void testGonggao_zhongyaoxinwen_flash(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	

		  System.out.println("testGonggao_zhongyaoxinwen_flash");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道里的'重要新闻flash'位置不对");	  
	  }
	  
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"common_zhongyaoxinwen_list","info_zhongyaoxinwen_list_top","info_zhongyaoxinwen_list_left"})
	  public void testGonggao_zhongyaoxinwen_list(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	

		  System.out.println("testGonggao_zhongyaoxinwen_list");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道里的'重要新闻列表'位置不对");	
		  
	  }
	  
	  
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"common_searchbtn","common_searchbtn_top","common_searchbtn_left"})
	  public void test_common_serachbtn(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	
		
		  System.out.println("test_common_serachbtn");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道搜索框位置不对");	  
	  }
	  
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"gonggao_L_gupiaogonggao","gonggao_L_gupiaogonggao_top","gonggao_L_gupiaogonggao_left"})
	  public void testGonggao_L_gupiaogongao(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	
		
		  System.out.println("testGonggao_L_gupiaogongao");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道左侧“股票频道”位置不对");	  
	  }
 
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"gonggao_L_jijingonggao","gonggao_L_jijingonggao_top","gonggao_L_jijingonggao_left"})
	  public void testGonggao_L_jijingongao(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	
		
		  System.out.println("testGonggao_L_jijingongao");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道左侧“基金频道”位置不对");	  
	  }
	  
	  @UIMessage(description = "")@Test(groups={"Gonggao"})
	  @Parameters({"gonggao_L_ganggugonggao","gonggao_L_ganggugonggao_top","gonggao_L_ganggugonggao_left"})
	  public void testGonggao_L_ganggugongao(String location,String toplocation,String leftlocation) throws InterruptedException
	  {	
		
		  System.out.println("testGonggao_L_ganggugongao");
		  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
		  AssertEquals(driver,flag,true,"公告频道左侧“港股频道”位置不对");	  
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
