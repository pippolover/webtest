package com.myhexin;

import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import static com.myhexin.common.Assert.*;

import java.awt.AWTException;
import java.lang.Thread;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.*;

public class testXuanGuClient {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	public static util ut;
	private static WebDriver driver;
	
	
	
	@UIMessage(description = "选股客户端,点击选股,跳转对应页")@Test
	  @Parameters({"clienturl","auto","searchbtn"})
	  public void testSearchBtn(String clienturl,String  auto,String searchbtn)throws Exception {
		System.out.println("testSearchBtn");
		sel.open(clienturl);
		//driver.navigate().to(clienturl);
//		if (sel.isAlertPresent()) {
//			sel.getAlert();
//		}
		
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		driver.manage().window().maximize();
		String autotext = sel.getAttribute(auto); 
	    System.out.println(autotext);
		sel.click(searchbtn);
		Thread.sleep(2000);

		boolean firststock = sel.isElementPresent("//*[@id='cover_con']/table/tbody/tr[1]/td[2]/span");
		System.out.println(firststock);
		AssertTrue(driver, autotext.contains(sel.getAttribute(auto)), "点击默认的选股后,没有跳转到对应的页面");
	    
	}
	
  
	@UIMessage(description = "点击收藏问句,查看有没有收藏")@Test
	  @Parameters({"auto","favoritbtn","openfavoritbtn","favorit"})
	  public void testFavoritBtn(String auto,String favoritbtn,String openfavoritbtn,String favorit)throws Exception {
		System.out.println("testFavoritBtn");
		String autotext = sel.getAttribute(auto); 
		System.out.println(autotext);
		sel.click(favoritbtn);
		Thread.sleep(4000);
	    AssertEquals(driver,sel.getAttribute("//*[@id='favorit_btn']@class"),"favorit_btn light_favorit_btn", "在索框内点击收藏标签后，没有弹出“已收藏”窗口");
        sel.click(openfavoritbtn); //打开我的收藏
        Thread.sleep(2000);
        System.out.println("111111");
        String favorittext = sel.getText(favorit);
        System.out.println(favorittext); //我的收藏中的收藏问句
        AssertEquals(driver, autotext ,favorittext,"我的收藏中的问句没有刚收藏的问句" );
	}
	
	@UIMessage(description = "点击收藏问句,查看有没有收藏")@Test
	  @Parameters({"favoritbtn","openfavoritbtn","favoritcon"})
	  public void testRemoveFavoritBtn(String favoritbtn,String openfavoritbtn,String favoritcon)throws Exception {
		System.out.println("testRemoveFavoritBtn");
		sel.click(favoritbtn);
		AssertEquals(driver,sel.getAttribute("//*[@id='favorit_btn']@class"),"favorit_btn", "在索框内点击收藏标签后，没有弹出“收藏问句”窗口");
		sel.click(openfavoritbtn); //打开我的收藏
        Thread.sleep(2000);
        System.out.println("222222");
		String favorittext = sel.getText(favoritcon);
		AssertEquals(driver, "x暂无收藏问句。", favorittext,"点击输入框收藏问句,我的收藏中的问句还存在");
	
	}
	
	@UIMessage(description = "点击收藏问句,查看有没有收藏")@Test
	  @Parameters({"result"})
	  public void testQuerySearch(String result)throws Exception {
		System.out.println("testQuerySearch");
		ut.QueryResult(sel, "2012年11月09日股价小于2");
		String resultitem = sel.getText(result);
		System.out.println(resultitem);
		
		AssertEquals(driver, "4只股票", resultitem,"所搜问句2012年11月09日股价小于2没有选股4只股票");
	    AssertTrue(driver, sel.isElementPresent("//*[@id='cover_con']/table/tbody/tr[4]/td[1]")); //选股结果中的序号4存在
	
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