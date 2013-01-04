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

public class testUIlocation_Yanbao {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private  String openurl="/?tid=report&amp;ts=1&amp;qs=1#";
	private static WebDriver driver;
  
  
  //研报频道
  @UIMessage(description = "")@Test(groups={"Info"})
  @Parameters({"info_zoushitu","yanbao_zoushitu_top","yanbao_zoushitu_left","zhankaizoushitu"})
  public void testYanbao_zoushitu(String location,String toplocation,String leftlocation,String Flash) throws Exception
  {	  
	  System.out.println("开始测试研报频道的UI元素位置！");
	  
	  System.out.println("testYanbao_zoushitu");
	  ut.QueryResult(sel,"浦发银行");
	  sel.click(Flash);
	  Thread.sleep(3000);
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道里的'走势图'位置不对");	  
  }
  
  
  
  @UIMessage(description = "")@Test(groups={"Info"})
  @Parameters({"common_zhongyaoxinwen_title","info_zhongyaoxinwen_title_top","info_zhongyaoxinwen_title_left"})
  public void testYanbao_zhongyaoxinwen_title(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testYanbao_zhongyaoxinwen_title");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道里的'重要新闻标题'位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"Info"})
  @Parameters({"common_zhongyaoxinwen_flash","info_zhongyaoxinwen_flash_top","info_zhongyaoxinwen_flash_left"})
  public void testYanbao_zhongyaoxinwen_flash(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testYanbao_zhongyaoxinwen_flash");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道里的'重要新闻flash'位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"Info"})
  @Parameters({"common_zhongyaoxinwen_list","info_zhongyaoxinwen_list_top","info_zhongyaoxinwen_list_left"})
  public void testYanbao_zhongyaoxinwen_list(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("testYanbao_zhongyaoxinwen_list");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道里的'重要新闻列表'位置不对");	
	  
  }
  
  
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_searchbtn","common_searchbtn_top","common_searchbtn_left"})
  public void test_common_serachbtn(String location,String toplocation,String leftlocation) throws InterruptedException
  {	
	
	  System.out.println("test_common_serachbtn");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道搜索框位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_quanwenchaxun","common_quanwenchaxun_top","common_quanwenchaxun_left"})
  public void test_common_quanwenchaxun(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("test_common_quanwenchaxun");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道全文搜索位置不对");	  
  }
  
 
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_biaotichaxun","common_biaotichaxun_top","common_biaotichaxun_left"})
  public void test_common_biaotichaxun(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("test_common_biaotichaxun");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道标题查询位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_yitiannei","common_yitiannei_top","common_yitiannei_left"})
  public void test_common_yitiannei(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("test_common_yitiannei");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道标题里的'一天内'位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_sangeyue","common_sangeyue_top","common_sangeyue_left"})
  public void test_common_sangeyue(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("test_common_sangeyue");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道标题里的'三个月'位置不对");	  
  }
  
  @UIMessage(description = "")@Test(groups={"common"})
  @Parameters({"common_zidingyishijian","common_zidingyishijian_top","common_zidingyishijian_left"})
  public void test_common_zidingyishijian(String location,String toplocation,String leftlocation) throws InterruptedException
  {	

	  System.out.println("test_zidingyishijian");
	  boolean flag=ut.getELementposition(sel,location,toplocation,leftlocation);
	  AssertEquals(driver,flag,true,"研报频道标题里的'自定义时间'位置不对");	  
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
