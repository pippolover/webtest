package com.myhexin;
import static com.myhexin.common.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class testBugfree_infotrigger {	
	private static WebDriverBackedSeleniumWrapper sel;
	public static final String TIMEOUT = "30000";
	public static util ut;
	//concepoStockString用来保存首页表格里的一个数据,numConceptStockString 用来保存由这个数据搜索到的股票数目
	private String conceptStockString="";
	private String ConceptStockUrl="";
	private static WebDriver driver;
	
	@UIMessage(description = "")@Test
	@Parameters({"openUrl_info","elemvalue.xpath"})
	public void testEquityunit(String Url,String elemvalueStringxpath) throws Exception
	{
		System.out.println("testEquityunit");
		sel.open(Url);
		sel.windowMaximize();
		ut.QueryResult(sel,"600000 股本结构");
		System.out.print(sel.getLocation());
		String elemvalueString=sel.getText(elemvalueStringxpath);
		AssertTrue(driver,elemvalueString.contains("亿股"),"股本单位结果不正确");
		
	}
	
	/**
	 * @param host
	 * @param port
	 * @param browser
	 * @param url
	 * @throws MalformedURLException 
	 */
	@BeforeTest
	@Parameters({ "selenium.host", "selenium.port", "selenium.browser",
			"selenium.url" })
	public void beforeTest(String host, String port, String browser, String url) throws MalformedURLException {
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
	public void afterTest() {
		System.out.println("After test");
		driver.quit();
	}
}