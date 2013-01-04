package com.myhexin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.DefaultSelenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import com.myhexin.common.*;

import static com.myhexin.common.Assert.*;
public class testInfoyidong {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private WebDriver driver;
	public ArrayList<ArrayList<String>> lYidongvalue = new ArrayList<ArrayList<String>>();// 用来存放四个异动数据栏
	public ArrayList<String> lYeAFvalue = new ArrayList<String>();// 用来存放昨天下午的异动数据
	public ArrayList<String> lYeNIvalue = new ArrayList<String>();// 用来存放昨天晚上的异动数据
	public ArrayList<String> lToMovalue = new ArrayList<String>();// 用来存放今天早上的异动数据
	public ArrayList<String> lToNewvalue = new ArrayList<String>();// 用来存放今天最新的异动数据
	
	private String thiredtitleString="";
	
	// 测试一打开首页中间异动标签是不是第三个
	/**
	 * @param openUrl
	 * @param sFullTitle
	 * @param sThiredtitle
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "selenium.info", "sFullTitle.locate","sThiredtitle.locate" })
	public void test_Thirdyidong_isfirst(String openUrl, String sFullTitle,
			String sThiredtitle) { 
		System.out.println("test_Thirdyidong_isfirst");
		System.out.println("测试一打开首页中间异动标签是不是第三个");
		sel.open(openUrl);
		sel.windowMaximize();
		String fulltitleString = sel.getAttribute(sFullTitle);
		thiredtitleString = sel.getText(sThiredtitle);
		System.out.printf("实际值:%s,\t期望值：%s\n", fulltitleString,
				thiredtitleString);
		AssertEquals(driver,fulltitleString, thiredtitleString);

	}

	/**
	 * @param sYe1
	 * @param sYe2
	 * @param sYe3
	 * @param sYe4
	 */
	@UIMessage(description = "")@Test(dependsOnMethods={"test_Thirdyidong_isfirst"})
	@Parameters({ "sYIdong1_samexpath.locate", "sYIdong2_samexpath.locate",
			"sYIdong3_samexpath.locate", "sYIdong4_samexpath.locate" })
	public void test_Yidongnum(String sYe1, String sYe2, String sYe3,
			String sYe4) {// 测试每个异动消息的数目是不是小于6条
		System.out.println("test_Yidongnum");
		System.out.println("测试每个异动消息的数目是不是小于6条");
		ArrayList<String> lYidong = new ArrayList<String>();
		lYidong.add(sYe1);
		lYidong.add(sYe2);
		lYidong.add(sYe3);
		lYidong.add(sYe4);
		ArrayList<Integer> lYidongnum = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < lYidong.size(); i++) {
			try {
				System.out.println(lYidong.get(i));
				Number c = sel.getXpathCount(lYidong.get(i));
				count = c.intValue();
				lYidongnum.add(count);
				System.out.println(count);
			} catch (Exception e) {
				count = 0;
				lYidongnum.add(count);
			}
			for (int j = 0; j < lYidongnum.size(); j++) {
				AssertTrue(driver,lYidongnum.get(j) < 6,"异动消息数目超过了6条");
			}

		}
	}

	/**
	 * @param sYeAF
	 * @param sYeNI
	 * @param sToMo
	 * @param sToNew
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "sYIdong_1.locate", "sYIdong_2.locate", "sYIdong_3.locate",
			"sYIdong_4.locate" })
	public void testYidongInfo(String sYeAF, String sYeNI, String sToMo,
			String sToNew) {

		for (int x = 1; x < 6; x++) {
			String xpath1 = sYeAF + "tr" + "[" + x + "]" + "/td[3]";
			String xpath2 = sYeNI + "tr" + "[" + x + "]" + "/td[3]";
			String xpath3 = sToMo + "tr" + "[" + x + "]" + "/td[3]";
			String xpath4 = sToNew + "tr" + "[" + x + "]" + "/td[3]";
			try {
				String YeAFvalue = sel.getText(xpath1);
				lYeAFvalue.add(YeAFvalue);
				String YeNIvalue = sel.getText(xpath2);
				lYeNIvalue.add(YeNIvalue);
				String ToMovalue = sel.getText(xpath3);
				lToMovalue.add(ToMovalue);
				String ToNewvalue = sel.getText(xpath4);
				lToNewvalue.add(ToNewvalue);
			} catch (Exception e) {

			}
		}

		// 把几个异动列表添加一个大的列表里面
		lYidongvalue.add(lYeAFvalue);
		lYidongvalue.add(lYeNIvalue);
		lYidongvalue.add(lToMovalue);
		lYidongvalue.add(lToNewvalue);

	}
	
	//判断股票名称是不是都是数字

	@UIMessage(description = "判断异动结果中的股票名称是不是都是数字")@Test
	public void testisnotNum()
	{
		System.out.println("判断股票名称是不是都是数字");
		for(int i=0;i<lYidongvalue.size();i++)
		{
			for(int j=0;j<lYidongvalue.get(i).size();j++)
			{	
				AssertTrue(driver,ut.isnotNumeric(lYidongvalue.get(i).get(j)),"验证错误，异动结果中有股票名称是纯数字");
			}
		}
	}
	
	
	@UIMessage(description = "")@Test
	public void testAbnormalNews() throws InterruptedException {
		// 获取最新时间和系统时间进行对比
		System.out.println("testAbnormalNews");
		Calendar cal = Calendar.getInstance();
		Date time = cal.getTime();
		System.out.println(time);

		Integer m = cal.get(Calendar.MONTH) + 1;
		Integer d = cal.get(Calendar.DAY_OF_MONTH);
		Integer h = cal.get(Calendar.HOUR_OF_DAY);

        String expectUpdateTime = m.toString() + "月" + d.toString() + "日 "
				+ h.toString() + "点";
        
		sel.click("//*[@id='abstocknews']/div[1]/ul/li[4]/span[2]");
		Thread.sleep(3000);
		
		String updateTime = sel.getText("//*[@id='introWord']");
		System.out.println(expectUpdateTime);
		System.out.println(updateTime);
		AssertTrue(driver,updateTime.contains(expectUpdateTime),"异动更新时间和系统时间不一致");// 用来判断网页上显示更新时间是否和系统时间一致

	}

	@UIMessage(description = "")@Test
	@Parameters({ "ALLlook.locate","Jumptitle.locate"
	})
	public void testAllRank(String ALLlook,
			String Jumptitlexpath) {
		// 判断跳转后的异动消息和首页是否一致

		System.out.println("testAllRank");
		sel.open(sel.getAttribute(ALLlook));
		String jumptitleString=sel.getText(Jumptitlexpath);
		
		System.out.println(thiredtitleString);
		AssertTrue(driver,thiredtitleString.contains(jumptitleString),"查看全部跳转后异动标题前后不一致！");
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
