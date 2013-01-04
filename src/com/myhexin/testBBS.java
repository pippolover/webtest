package com.myhexin;

import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import static com.myhexin.common.Assert.*;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.Selenium;

public class testBBS {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private static WebDriver driver;
	/**
	 * @param openurl
	 * @param FR
	 *            论坛测试浦发银行有无结果返回
	 * @throws Exception 
	 */
	@UIMessage(description = "论坛频道，搜索浦发银行没有结果返回")
	@Test
	@Parameters({ "openurl", "FirstResult" })
	public void testQuery(String openurl, String FR)
			throws Exception {
		System.out.println("testQuery");
		try{
			
		sel.open(openurl);
		}
		catch (Exception e) {
			sel.open(openurl);
		}
		sel.windowMaximize();
		ut.QueryResult(sel, "浦发银行");
		// 查看第一条记录是否存在
		boolean firstResult = sel.isElementPresent(FR);
		AssertEquals(driver,firstResult, true, "搜索浦发银行没有结果返回");
		Thread.sleep(5000);
		
	}

	/**
	 * @param resultnum
	 */
	@Test
	@Parameters({ "Resultnum" })
	public void testResultnum(String resultnum) {
		System.out.println("testResultnum");
		Number numInteger = sel.getXpathCount(resultnum);
		AssertEquals(driver,numInteger, 10, "返回的结果第页结果数目不足10条");
		
		

	}
	
	@Test
	@Parameters({"updatetime"})
	public void testGubatime(String updatetime)
	{
		System.out.println("testGubatime");
		System.out.println(sel.getLocation());
		String timeString1=sel.getText(updatetime);
		System.out.println(timeString1);
		Date dt=new Date();
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		String timeString2=sf.format(dt);
		System.out.println(timeString2);
		AssertTrue(driver,timeString1.contains(timeString2),"股吧最新时间不是今天");
		
	}
	@UIMessage(description = "论坛频道，点击左侧股吧，验证股吧内容")
	@Test
	@Parameters({ "guba.xpath", "guba_result" })
	public void testguba(String guba_xpath, String guba_result)
			throws InterruptedException {
		System.out.println("testguba");
		// 验证该位置上是股吧
		
        
		AssertTrue(driver,sel.getText(guba_xpath).equals("股吧"));
		// 点击股吧链接
		sel.click(guba_xpath);
		// 证明结果存在而且是股吧的内容
		Thread.sleep(5000);
		System.out.println(sel.getText(guba_result));
		//System.out.println(sel.getText(guba_result));
		AssertEquals(driver,sel.isElementPresent(guba_result), true, "股吧第一页内容没有");
		String guba_attr = guba_result + "@class";
		String typeString = sel.getAttribute(guba_attr);
		AssertTrue(driver,typeString.contains("guba"), "内容不是股吧里的");
	}
	@UIMessage(description="论坛频道，点击左侧的论坛，查看是否有内容")
	@Test
	@Parameters({ "luntan.xpath", "luntan_result" })
	public void testluntan(String luntan_xpath, String luntan_result)
			throws InterruptedException {
		System.out.println("testluntan");
		// 验证该位置上是论坛
		AssertTrue(driver,sel.getText(luntan_xpath).equals("论坛"));
		// 点击论坛链接
		sel.click(luntan_xpath);
		// 证明结果存在
		Thread.sleep(5000);
		System.out.println(sel.getXpathCount(luntan_result));

		AssertEquals(driver,sel.isElementPresent(luntan_result), true, "论坛第一页内容没有");
	}

	@BeforeTest
	// 测试开始之前要执行这个
	@Parameters({ "selenium.host", "selenium.port", "selenium.browser",
			"selenium.url" })
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
