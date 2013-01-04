package com.myhexin;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
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

public class testBugfree_ontology {	
	private static WebDriverBackedSeleniumWrapper sel;
	public static final String TIMEOUT = "30000";
	public static util ut;
	//concepoStockString用来保存首页表格里的一个数据,numConceptStockString 用来保存由这个数据搜索到的股票数目
	private String conceptStockString="";

	public  static WebDriver driver;
	public String OpenUrl="stockpick?tid=stockpick";
	
//	/**
//	 * 该case主要是来测试点击下一页后出现的还是第一页的结果这个BUG（Bugfree-547）
//	 */
//	@UIMessage(description = "")@Test
//	@Parameters({"Nextpage.xpath", "SecondPage.xpath" })
//	public void testNextPage(String NP, String SP) {
//			sel.open(OpenUrl);
//			System.out.println("testNextPage");
//			sel.click(NP);			
//			String statu = sel.getText(SP);
//			AssertTrue(driver,statu.contains("2"), "对不起，点击下一页后没有跳到第二页");
//	}
//	
//	/**
//	 * 该case测试： 从表格中点热点概念，没有任何数据 BUG298
//	 * 从股票代码中不存在超链接，BUG299
//	 * @param openUrl 网址
//	 * @param FirstC 第一条概念数据
//	 * @param FirstS 第一条股票数据
//	 * @throws InterruptedException 
//	 */
//	@UIMessage(description = "")@Test
//	@Parameters({"openUrl_ontology","FirstConcept","CodelinktoStock","stockNum"})
//	public void testFormhotconcept(String openUrl,String FirstC,String CodetoLinkS,String 
//			stcoknum) throws InterruptedException
//	{	System.out.println("testFormhotconcept");
//		conceptStockString=sel.getText(FirstC);	
//		
//		boolean flag2=sel.getAttribute(CodetoLinkS).contains("/stockpick/concept?");
//		AssertTrue(driver,flag2,"首页表格里热点概念数据没有超链接");
//		sel.click(FirstC);	
//		
//	}
//	
//	/**该case:点击星空图的概念和直接搜索概念得到的结果不同
//	 * @param stockNum
//	 * @throws Exception 
//	 */
//	@UIMessage(description = "")@Test
//	@Parameters({"stockNum"})
//	public void testClickXingKongTuconcepts_And_directSearchconcept(String stockNum) throws Exception
//	{
//		System.out.println("testClickXingKongTuconcepts_And_directSearchconcept");
//	  String numConceptStockString=sel.getText("//html/body/div[7]/div[1]/div[3]/h3/span[3]/em[1]");
//	  
//	  System.out.println(numConceptStockString);
//	  
//	  ut.QueryResult(sel,conceptStockString);
//	  String stcoknumString=sel.getText(stockNum);
//	  System.out.println(stcoknumString);
//	  boolean flag=stcoknumString.equals(numConceptStockString);
//	  AssertEquals(driver,flag,true,String.format("星空图点击股票概念:%s返回数据是%s，而直接搜返回股票数目是%s",conceptStockString,numConceptStockString,stcoknumString));
//	  
//	}
	
	
	
	
	
	/**该case:大于且小于指标条件显示不完整 bug327

	 * @param Beta
	 * @param And
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({"BetaXpath","AndXpath"})
	public void testIndexconditions(String Beta,String And) throws Exception
	{    
		System.out.println("testIndexconditions");
		ut.QueryResult(sel,"与大盘走势相同的个股");
		
		
		//System.out.println(sel.getLocation());
		//sel.click("//html/body/div[6]/div/div/span");
		System.out.println(sel.getLocation());
		Thread.sleep(1000);
		sel.click("//*[@id='qusetion_remind']/span[3]");
		                            
		boolean existBeta =sel.isElementPresent(Beta);
		System.out.println(existBeta);
		AssertEquals(driver,existBeta,true,"前置比较条件不存在");
		boolean existAnd =sel.isElementPresent(And);
		AssertEquals(driver,existAnd,true,"后置比较条件不存在");
		
		
		
	}

	
	/**
	 * @param QC
	 * @param CR
	 *            该case主要是测试搜索结果中是否含有分号等其他符号(bugfree-537)
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "QueryConcept", "ConceptResult.xpath" })
	public void testResultOfConceptFormat(String QC, String CR) throws Exception {
		Pattern p = Pattern.compile("[，【  】 ？  ; . ：、 “  ”]");
		System.out.println("testResultOfConceptFormat");
		ut.QueryResult(sel, QC);
		Thread.sleep(3000);
		System.out.println(sel.getLocation());
		System.out.println(sel.getText(CR));
		Matcher matcher = p.matcher(sel.getText(CR));
		boolean find = matcher.find();
		AssertEquals(driver,find, false, "查询结果中含有分号或者其他异常符号");
	}

	
	
	/**
	 * 该case主要为了测试：搜索个别结果的时候，结果中天数是否是整数(Bugfree-556)
	 * 
	 * @param QD
	 * @param FRc
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "QueryDay", "FirstRecord" })
	public void testDaysIsRounded(String QD, String FRc) throws Exception {
		System.out.println("testDaysIsRounded");
		System.out.println(sel.getLocation());
		ut.QueryResult(sel, QD);
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[6]/div/div/span");
		String Daynum = sel.getText(FRc);
		System.out.println(sel.getLocation());
		boolean isNum = Daynum.matches("[0.00-9.00]+");
		AssertTrue(driver,isNum, "搜索连续天数时候返回的天数不是整数");

	}
	
	/**
	 * @param QBR
	 * @param SN
	 *            该case用来测试，当返回搜索结果很多条的时候，会不会在第一页显示一共有几条数据(Bugfree-341)
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "QueryBigResult", "stockNum" })
	public void testResultsInLarge(String QBR, String SN) throws Exception {
		System.out.println("testResultsInLarge");
		ut.QueryResult(sel,QBR);
		Thread.sleep(1000);
		System.out.println(sel.getLocation());
		String StockNum = sel.getText(SN);
		System.out.println(StockNum);
		Integer num = Integer.parseInt(StockNum);
		
		AssertTrue(driver,num > 0, "返回结果中没有");

	}
	
	/**
	 * @param errmsg
	 * @param errmsglocate
	 * 搜索 \u003e 错误结果页无任何提示 bug362
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({"Errormessage","Errormessage.xpath"})
	public void testSpecialCharacters(String errmsg,String errmsglocate) throws Exception
	{
		System.out.println("testSpecialCharacters");
		ut.QueryResult(sel,"003e");
		
		//sel.click("//html/body/div[6]/div/div/span");
		System.out.println(sel.getLocation());
		AssertEquals(driver,sel.getText(errmsglocate),errmsg,"特殊字符串测试无效");
		
	}
	
	
	/**
	 * @param rank
	 * @param sort_btn
	 * @param rank_aftersort
	 * @throws Exception 

	 */
	@UIMessage(description = "")@Test
	@Parameters({"rank","sort_btn","rank_aftersort"})
	public void testTablesort(String rank,String sort_btn,String rank_aftersort) throws Exception
	{
		System.out.println("testTablesort");
		ut.QueryResult(sel,"负债率最高的股票");
		Thread.sleep(1000);
		//AssertEquals(driver,sel.getText(rank),"1","默认的表排序不是降序");
		sel.click(sort_btn);
		Thread.sleep(1000);
		sel.click(sort_btn);
		Thread.sleep(1000);
		//System.out.println(sel.getText("//*[@id='offtblbdy']/tr[1]/td[4]"));
		AssertEquals(driver,sel.getText(rank_aftersort),"10","点击排序后，左边排名没有变动");
		
	}
	
	
	@UIMessage(description = "")@Test
	@Parameters("result")
	public void testAnalysisIncludedUnit(String re) throws Exception
	{
		System.out.println("testAnalysisIncludedUnit");
		ut.QueryResult(sel,"总股本大于一亿股");
		AssertTrue(driver,sel.getText(re).contains("亿股"),"展开的问句分析中不包含单位");
		
	}

	/**
	 * @param errormsg
	 * Bugfree705
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({"errormsg.xpath"})
	public void testSTstock(String errormsg) throws Exception
	{
		System.out.println("testSTock");
		ut.QueryResult(sel,"上交所st股票");
		//sel.click("//html/body/div[6]/div/div/span");
		System.out.println(sel.getLocation());
		String errormsgString=sel.getText(errormsg);
		System.out.println(errormsgString);
		AssertEquals(driver,errormsgString.contains("抱歉"),true,"搜索上交所ST股票时候没有数据返回");
	}


	@UIMessage(description = "")@Test(groups={"sprint17"})
	@Parameters({"erromsg.xpath"})
	public void testLiftedstock(String erromsg) throws Exception
	{
		System.out.print("testLiftedstock");
		ut.QueryResult(sel,"2012年7月11日限售股解禁");
		
		boolean errorflag=sel.isElementPresent(erromsg);
		AssertEquals(driver,errorflag,false,"限售解禁股票相关数据还不能查询");
	
		
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
		sel.open(OpenUrl);
		driver.manage().window().maximize();
	}

	
	
	
	
	@AfterTest
	public void afterTest() {
		System.out.println("After test");
		driver.quit();
	}

}


