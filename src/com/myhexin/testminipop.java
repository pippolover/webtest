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
public class testminipop {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private static WebDriver driver;
	private util ut;
	
	
	@UIMessage(description = "测试minipop的输入框存不存在")
	@Test
	@Parameters({ "minipopurl", "auto" })
	public void testAuto(String minipopurl, String auto)
			throws Exception {
		System.out.println("testAuto");
		sel.open(minipopurl);
		sel.windowMaximize();
		boolean at = sel.isElementPresent(auto); //判断输入框在不在
	    AssertEquals(driver, at, true,"minipop的搜索框不在");
	}	
	
	
	
	    @UIMessage(description = "测试minipop的大家都在问的标签句是不是12句")
		@Test
		@Parameters({"target"})
		public void testTageCount(String target)
				throws Exception {
		System.out.println("testTageCount");
		sel.windowMaximize();
		Number count =	sel.getXpathCount(target);
		AssertEquals(driver, count,12, "大家都在问的检签句不是12条");
     }
	    
	  
	    @UIMessage(description = "测试minipop的LOGO的链接")
		@Test
		@Parameters({"logolink"})
		public void testLogoLink(String logolink)
				throws Exception {
	    	System.out.println("testLogoLink");
	    	sel.windowMaximize();
	    	sel.click(logolink);
	    	Thread.sleep(2000);
	    	for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
	    	Thread.sleep(3000);
	    	//sel.click("//*[@id='user_guide']/div[1]/span[1]");	
	    	String logoclink = sel.getLocation();
	    	AssertEquals(driver,logoclink,"http://x.10jqka.com.cn/stockpick?tid=stockpick", "点击logo打开不是搜索选股");
	    	
	    }
	    
	    @UIMessage(description = "测试点击默认问句会不会跳转")
		@Test
		@Parameters({ "minipopurl","morenwenju" })
		public void testMRWJ(String minipopurl,String morenwenju)
				throws Exception {
			System.out.println("testSearchBtn");
            sel.open(minipopurl);
			sel.windowMaximize();
			String  morenwenju_text = sel.getAttribute(morenwenju); //取输入框默认文本
			System.out.println(morenwenju_text);
			sel.click("//*[@id='searchform']/div[3]/input");//点击执行选股
			Thread.sleep(5000);
			for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
		   // sel.click("//*[@id='help_con']/div/div[1]/span[1]");	//关闭新手帮助
            System.out.println(sel.getTitle());
            AssertTrue(driver,sel.getTitle().contains(morenwenju_text), "点击执行选股没有跳转到默认问句的选股页");
	    }
			
	    @UIMessage(description = "测试点击标签句会不会跳转")
		@Test
		@Parameters({ "minipopurl", "firsttaget"})
		public void testFirstTaget(String minipopurl,String firsttaget)
				throws Exception {
			System.out.println("testFirstTaget");
            sel.open(minipopurl);
			sel.windowMaximize();
			String ft = sel.getText(firsttaget);
			System.out.println(ft);
			sel.click(firsttaget);
			Thread.sleep(5000);
			for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
			
			System.out.println(sel.getTitle());
			AssertTrue(driver,sel.getTitle().contains(ft), "点击第一个标签句没有跳转到它的选股页");
			
	    }

	    
	    @UIMessage(description = "搜股价大于等于9元会不会跳转")
		@Test
		@Parameters({ "minipopurl", "auto","query","searchbtn"})
		public void testSearchQuery(String minipopurl,String auto,String query,String searchbtn)
				throws Exception {
			System.out.println("testSearchQuery");
            sel.open(minipopurl);
            sel.click(auto);
            sel.type(auto, query);
            sel.click(searchbtn); //点击我要选股
            Thread.sleep(2000);
            for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
          // sel.click("//*[@id='help_con']/div/div[1]/span[1]");
           System.out.println(sel.getTitle()); 
           AssertTrue(driver,sel.getTitle().contains(query), "搜索一个问句没有跳转到它的选股页");
           
	    }
	    
	    
	    @UIMessage(description = "点选牛股方案1跳转到对应页面")
		@Test
		@Parameters({ "minipopurl","niugu1","xuanniugu"})
		public void testClinkXuanNiuGu(String minipopurl,String niugu1,String xuanniugu)
				throws Exception {
			System.out.println("testClinkXuanNiuGu");
            sel.open(minipopurl);
            String ng = sel.getText(niugu1);
            System.out.println(ng);
            sel.click(xuanniugu);
            Thread.sleep(5000);
            for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
            Thread.sleep(3000);
            AssertTrue(driver,sel.getTitle().contains(ng), "点选牛股方案1没有跳转到对应页面");
	    } 
          
	    @UIMessage(description = "点更多方案跳转了搜牛A版首页")
		@Test
		@Parameters({ "minipopurl","gendoufanan"})
		public void testLinkPlaneMore(String minipopurl,String gendoufanan)
				throws Exception {
			System.out.println("testLinkPlaneMore");
            sel.open(minipopurl);
            sel.click(gendoufanan);
            Thread.sleep(5000);
            for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
            AssertEquals(driver,sel.getLocation(),"http://x.10jqka.com.cn/stockpick?tid=stockpick&shunt=a", "点选牛股没有跳转了搜牛A版首页");
	    } 
	    
	    
		@BeforeTest
		// 测试开始之前要执行这个
		@Parameters({ "selenium.host", "selenium.port", "selenium.browser",
				"selenium.url" })
		public void beforeTest(String host, int port, String browser, String url) throws MalformedURLException {
			System.out.println("Before test");
			System.out.println(host);
//			Integer Dport = Integer.parseInt(port);
//			sel = new DefaultSelenium(host, Dport, browser, url);
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


