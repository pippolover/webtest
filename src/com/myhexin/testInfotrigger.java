package com.myhexin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import com.thoughtworks.selenium.Selenium;
import static com.myhexin.common.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;

public class testInfotrigger {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private WebDriver driver;
	// 判断ajax请求返回是否正确
	@UIMessage(description = "")@Test
	@Parameters({ "selenium.url", "ajaxurl.locate" })
	public void test_Ajax(String selenium_url, String url) throws Exception {
		System.out.println("test_Ajax");
		String Url = selenium_url + url;
		System.out.println(Url);
		String xString = ut.send_url(Url);
		System.out.println(xString);
		String a[] = xString.split(",");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
			AssertTrue(driver,a[i].startsWith("6"),"输入6时候ajax返回的结果中股票代码不都是以6开始的");

		}

	}

	// 今日新闻排行
	/**
	 * @param openurl
	 * @param newxpath
	 */
	@UIMessage(description = "")@Test(description = "用来判断新闻条数是否是五条")
	@Parameters({ "selenium.info", "sNew.locate" })
	public void testNewsRank(String openurl, String newxpath) {
		sel.open(openurl);
		driver.manage().window().maximize();
		//sel.windowMaximize();
		Map<Integer, String> Newsmap = new HashMap<Integer, String>();
		System.out.println("testNewsRank");
		for (int x = 1; x < 6; x++) {
			String xpath = newxpath + "/li" + "[" + x + "]" + "/a";
			String New = sel.getText(xpath);
			if (!Newsmap.containsValue(New)) {
				Newsmap.put(x, New);
			}
			System.out.println(New);
		}

		AssertEquals(driver,5, Newsmap.size(), "首页新闻条数不足5条");// 用来判断新闻条数是否是五条

	}

	/**
	 * @param sInput
	 * @param sInputValue_1
	 * @param sSearchButton
	 * @param avlocate
	 * @param expectvalue
	 * @param Skhref
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "sInput.locate", "sInputValue_1", "sSearchButton.locate",
			"sActualValue.locate", "sExpectValue.locate", "sStockhref.locate" })
	public void testQueryResult(String sInput, String sInputValue_1,
			String sSearchButton, String avlocate, String expectvalue,
			String Skhref) throws Exception {
		System.out.println("testQueryResult");

		ut.QueryResult(sel, sInputValue_1);
		String Actualvalue = sel.getText(avlocate);
		System.out.println(Actualvalue);
		String Stockhref = sel.getAttribute(Skhref);
		System.out.println(Stockhref);
		System.out.printf("实际值:%s,\t期望值：%s\n", Actualvalue, expectvalue);
		AssertEquals(driver,Actualvalue, expectvalue);
	}

	// 在上一个基础上测试走势图
	/**
	 * @param chartxpath
	 * @param clickxpath
	 * @throws InterruptedException
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "Chart.locate" })
	public void test_trendChart(String chartxpath) throws InterruptedException {
		System.out.println("test_TrendChart");
		String actualString = "";
		String hideChart = "oc-btn oc-btn-close";
		Thread.sleep(5000);
		actualString = sel.getAttribute(chartxpath);
		System.out.println(actualString);
		AssertTrue(driver,actualString.equals(hideChart), "走势图默认没有展开");

	}

	// 测试返回的概念股数据
	/**
	 * @param Input
	 * @param Btn
	 * @param Stock
	 * @param Stockxpath
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "sInput.locate", "sSearchButton.locate", "ConceptStock",
			"Stocknum.xpath" })
	public void test_ConceptStock(String Input, String Btn, String Stock,
			String Stockxpath) throws Exception {
		System.out.println("test_ConceptStock");
		ut.QueryResult(sel, Stock);
		/*
		 * int count=sel.getXpathCount(Stockxpath).intValue();
		 * System.out.println(count); AssertEquals(driver,count, 5);
		 */
		// 只需判断第五条在不在
		Thread.sleep(3000);
		boolean flag = sel.isElementPresent(Stockxpath);
		AssertEquals(driver,flag, true, "搜索石墨烯概念股票时候返回新闻数目小于5条");

	}

	// 测试搜索定义时候的结果里是否有百科
	/**
	 * @param wiki
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "wikiname.locate" })
	public void test_Definewiki(String wiki) throws Exception {
		ut.QueryResult(sel, "pe");
		String result = sel.getText(wiki);
		System.out.println(result);
		AssertTrue(driver,result.contains("百科"),
				String.format("搜索pe的时候结果里没有百科却变成了%s", result));

	}

	// 测试牛叉诊股
	/**
	 * @param Stock
	 * @param niucha
	 * @param niuchahref
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "niucha.locate", "niuchaHref.locate" })
	public void test_DiagnoseStock(String niucha, String niuchahref) throws Exception {
		String b = "";
		ut.QueryResult(sel, "浦发银行");

		boolean flag = sel.isElementPresent(niucha);
		AssertEquals(driver,flag, true);
		if (flag) {
			b = sel.getText(niucha);
		} else
			b = "";
		AssertTrue(driver,b.contains("牛叉诊股"));
		String href = sel.getAttribute(niuchahref);
		AssertTrue(driver,href.contains("http://doctor.10jqka.com.cn"), "牛叉诊骨没有出来");
	}

	// 测试股票术语时候是否会有数据探索
	/**
	 * @param Flashbar
	 * @param Flashstock
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "Flashbar.locate", "Flashstock.locate" })
	public void test_dataSearch(String Flashbar, String Flashstock) throws Exception {

		ut.QueryResult(sel, "天坛生物 应付职工薪酬");
		boolean flag_parent = sel.isElementPresent(Flashbar);
		AssertEquals(driver,flag_parent, true, "搜索天坛生物 应付职工薪酬没有出现数据探索图");
		boolean flag_child = sel.isElementPresent(Flashstock);
		AssertEquals(driver,flag_child, true);

	}

	// 测试热点人物的事件追踪
	/**
	 * @param EventTraceExist
	 * @param Flashgoin
	 * @param Eventnum
	 * @param Flashgoinxinj
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "EventTraceExist.locate", "Flashgoin.locate",
			"Eventnum.locate", "Flashgoinxinj.locate" })
	public void test_EventTrack(String EventTraceExist, String Flashgoin,
			String Eventnum, String Flashgoinxinj) throws Exception {
		ut.QueryResult(sel, "郭树清");
		AssertEquals(driver,sel.isElementPresent(EventTraceExist), true);
		System.out.println(sel.getLocation());
		int i=0;
		for(i=1;i<7;i++)
		{
			System.out.println(i);
			String xpath="//*[@id='newsCon']/li["+i+"]";
			AssertTrue(driver,sel.getText(xpath).contains("郭树清"),"搜索郭树清，人物追踪时候不是每条新闻都和郭树清有关");
		}

		
	}

	@UIMessage(description = "信息栏搜索同花顺出现的公告搜索的链接测试")@Test
	@Parameters({ "firstpubnote" })
	public void test_PubnoteSearch( String firstpubnote )throws Exception{
		ut.QueryResult(sel, "公告");
		Thread.sleep(6000);
		String pubnotetext = sel.getText(firstpubnote); 
		System.out.println(pubnotetext);
		sel.click(firstpubnote);
		Thread.sleep(3000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);      //切换到新窗口
			}
    	Thread.sleep(6000);
    	System.out.println(sel.getTitle());
    	AssertTrue(driver, sel.getTitle().contains(pubnotetext), "点击公告搜索的链接打开时不对");
	}

	@UIMessage(description = "信息栏搜索同花顺出现的公告搜索的链接测试")@Test
	@Parameters({ "selenium.info","firstreport" })
	public void test_ReportSearch(String openurl,String firstreport)throws Exception{
		sel.open(openurl);
		ut.QueryResult(sel, "招商地产 研报 ");
		Thread.sleep(6000);
		String reporttext = sel.getText(firstreport);
		System.out.println(reporttext);
		sel.click(firstreport);
		Thread.sleep(3000);
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);      //切换到新窗口
			}
    	Thread.sleep(6000);
    	System.out.println(sel.getTitle());
    	AssertTrue(driver, reporttext.contains(sel.getText("//*[@id='kuaizhao']/div[3]/div[1]/div[1]/div[1]/h2")),"点击研报的第一名链接打不开");
		
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
