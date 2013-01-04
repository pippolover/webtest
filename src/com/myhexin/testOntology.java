package com.myhexin;

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
import org.openqa.selenium.firefox.FirefoxDriver;

import com.myhexin.common.*;

public class testOntology {
	private static WebDriverBackedSeleniumWrapper sel;
	private static WebDriver driver;
	// private String url;
	public static final String TIMEOUT = "30000";
	public static util ut;
	String query = "20120504股票价格小于2";
    String openurl="stockpick?tid=stockpick";
	/**
	 * @param inputId
	 * @param searchbtn
	 * @param xpath1
	 * @param xpath2
	 * @param xpath3
	 * 测试选股，Query为：20120504股票价格小于2
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "openUrl","CommonQuery_stock1", "CommonQuery_stock2","CommonQuery_stock3" })
	public void testCommonQuery(String openurl,String xpath1, String xpath2, String xpath3) throws Exception {
		
		String stock1 = "000725.SZ";
		String stock2 = "600087.SH";
		String stock3 = "600868.SH";
		System.out.println("testing testCommonQuery");

		ut.QueryResult(sel,query);
		sel.waitForPageToLoad(TIMEOUT);

		String realStock1 = sel.getText(xpath1);
		System.out.println(realStock1);
		AssertEquals(driver,realStock1, stock1);

		String realStock2 = sel.getText(xpath2);
		System.out.println(realStock2);
		AssertEquals(driver,realStock2, stock2);

		String realStock3 = sel.getText(xpath3);
		System.out.println(realStock3);
		AssertEquals(driver,realStock3, stock3);

	}
	

	/**
	 * @param QE
	 *            Query是员工
	 * @param want_word
	 *            提示框的名字
	 * @param want_fRc
	 *            提示框里的第一条记录,该case主要是为了测试搜索员工时候会出现提示框，并且提示框里内容是否和员工相关
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "Employee_Query", "Employee_want_word", "Employee_want_firstRecord" })
	public void testEmployee(String QE, String want_word, String want_fRc) throws Exception {
		
		System.out.println("testEmployee");
		ut.QueryResult(sel, QE);
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[6]/div/div/span");
		//AssertTrue(driver,sel.getText(want_word).contains("相关问句"),"搜索员工时候提示框不存在");
		String frcString=sel.getAttribute(want_fRc);
		System.out.println(frcString);
		AssertTrue(driver,frcString.contains("员工"), "提示框里内容和员工无关");
	}
	
	
	@UIMessage(description = "")@Test
	@Parameters({ "Employee_Query", "Employee_want_word", "Employee_want_firstRecord" })
	public void testEmployee2(String QE, String want_word, String want_fRc) throws Exception {
		
		System.out.println("testEmployee2");
		ut.QueryResult(sel, QE);
		//sel.click("//html/body/div[6]/div/div/span");
		//AssertTrue(driver,sel.getText(want_word).contains("相关问句"),"搜索员工时候提示框不存在");
		//String frcString=sel.getAttribute(want_fRc);
		//System.out.println(frcString);
		//AssertTrue(driver,frcString.contains("员工"), "提示框里内容和员工无关");
	}
	
	/**
	 * @param inputId
	 * @param searchbtn
	 * @param xpath1
	 * @param xpath2
	 * @param xpath3
	 * @throws Exception 
	 */
	
	
	@UIMessage(description = "查看历史问句有没有超过6条")@Test
	@Parameters({"History_Frame","History_LatestRecord","searchbtn"})
	public void testHistoryQuestion(String History,String History_LatestRecord, String searchbtn) throws Exception
	{
		System.out.println("testHistoryQuestion");			
		ut.QueryResult(sel,"智能电网概念");	
		sel.click("//*[@id='hisQuesBox']/div[1]");
		Thread.sleep(2000);
		Integer count=(Integer) sel.getXpathCount(History);
		AssertTrue(driver,count<7,"历史数据显示超过了6条");
		/*sel.click(searchbtn);
		String actualvalue=sel.getText(History_LatestRecord);
		AssertEquals(driver,actualvalue,"智能电网概念","最新的搜索问句没有出现在历史问句里面");*/
		
	}
	
	
	@UIMessage(description = "")@Test
	@Parameters({ "History_Clear"})
	public void testClearHistory(String History_Clear) throws InterruptedException {
		System.out.println("testClearHistory");
		Thread.sleep(1000);
		sel.click("//*[@id=\"hisQuesBox\"]/div[2]/div");
		Thread.sleep(1000);
		sel.click(History_Clear);
		Thread.sleep(1000);
		//sel.click("//*[@id='hisQuesBox']/div[1]/span[1]");
		//Thread.sleep(100000);
		System.out.println(sel.getText(History_Clear));
		AssertEquals(driver,sel.getText(History_Clear), "暂无记录", "清空历史后标记没有变成'暂无历史'");
		//sel.refresh();
		//String flag= sel.getText(TheResult_CleanHistory);
		//AssertEquals(driver,true,sel.getText(TheResult_CleanHistory).contains("暂无历史"),"清空历史后，记录还在");
				
	}
	
	
	
	@UIMessage(description = "")@Test
	@Parameters({"Viewdetails"})
	public void testViewdetails(String Viewdetails) throws Exception{
		System.out.println("testViewdetails");
		ut.QueryResult(sel,"股价在3元以下的股票");	
		sel.click(Viewdetails);
		System.out.println(sel.getText(Viewdetails));
		AssertEquals(driver,sel.getText(Viewdetails),"收起","点击查看详情没有发生变化");
		
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"Condition_combination_tips","Condition_combination_Content"})
	public void testCondition_combination_tips(String Condition_combination_tips,String Condition_combination_Content) throws InterruptedException{
		System.out.println("testCondition_combination_tips");
		//sel.click(Condition_combination_tips);
		//sel.click("//html/body/div[8]/div/div[2]/p/span[3]");
		           
		AssertTrue(driver,sel.getAttribute(Condition_combination_Content).contains("display: block"),"条件组合提示没有出来");
		
	}
	
	
	
	@UIMessage(description = "")@Test
	@Parameters({"QuestionAnalysis_condComp00","QuestionAnalysis_qinfoForm","QuestionAnalysis_Stocknum"})
	public void testQuestionAnalysis_ChangeConditions(String condComp00,String qinfoForm,String count) throws InterruptedException{
		String beforechangenumString=sel.getText(count);
		System.out.println(beforechangenumString);
		System.out.println("testChangeConditions");
		//sel.click("//html/body/div[8]/div/div[2]/p/span[3]");
		          
		sel.type(condComp00,"200"); //在条件1中改 100为200
		//Thread.sleep(2000);
		//sel.click("//html/body/div[8]/div/div[2]/div/div/div[2]/input[2]");
		sel.click(qinfoForm); //问句分析中的条件组合后的那个确定
		Thread.sleep(3000);
		String afterchangenumString=sel.getText(count);
		boolean flag=beforechangenumString.equals(afterchangenumString);
		AssertEquals(driver,flag,false,"改变条件后，结果未发生变化");
	}
	
	
	
	@UIMessage(description = "")@Test
	@Parameters({"Add_cond_btn","Add_cond_insearch","Add_cond_inserchbtn","Add_cond_result"})
	public void testQuestionAnalysis_Add_cond(String zhuijia,String insearch,String inserchbtn,String result) throws InterruptedException{
		System.out.println("testQuestionAnalysis_Add_cond");
		sel.click(zhuijia);
		sel.type(insearch, "2011年最低股价");
		sel.click(inserchbtn);
		Thread.sleep(3000);
		String stockresult = sel.getText(result);
		System.out.println(stockresult);
		AssertTrue(driver,stockresult.contains("华数传媒"));
		//Thread.sleep(2000);
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"Viewdetails","Conditionalcontent"})
	public void testLargestdecline(String Viewdetails,String Conditionalcontent) throws Exception{
		
		System.out.println("testQuestionAnalysis_ChangeConditions");
		ut.QueryResult(sel,"2012年8月30日总股本一亿以下的股票");
		//sel.click("//html/body/div[8]/div/div[3]/div[2]/div/table/tbody/tr/td[4]/span");
		//String low = sel.getText("html/body/div[8]/div/div[3]/div[2]/div/table/tbody/tr/td[2]");
		//System.out.println(low);
		System.out.println(sel.getLocation());
		sel.click(Viewdetails);
		System.out.println(sel.getLocation());
		                                     
		AssertEquals(driver,true,sel.getText("//*[@id='qusetion_remind']/em/em").contains("一亿以下"),"测试'2012年8月30日总股本一亿以下的股票'时候，理解为内容不包含‘一亿以下’");
	}
	
	@UIMessage(description = "测试'2012年8月30日总股本一亿以下的股票,点击剔除ST股")@Test
	@Parameters({"DEststock_btn","testDEststock_intro"})
	public void testDEststock(String DEststock_btn,String  DEststock_intro)
	{
		System.out.println("testDEststock");
		sel.click(DEststock_btn);
		AssertEquals(driver,false,sel.getText(DEststock_intro).contains("136"),"选活跃股分类的时候，提示无结果");
		
	}
	
	@UIMessage(description = "测试'2012年8月30日总股本一亿以下的股票,点击剔除停牌股,要有结果")@Test
	@Parameters({"DEtinpaistocks_btn","DEtinpaistocks_intro"})
	public void testDEtinpaistocks(String DEtinpaistocks_btn,String DEtinpaistocks_intro)throws Exception{
		System.out.println("testDEtinpaistocks");
		sel.click(DEtinpaistocks_btn);
		Thread.sleep(5000);
		AssertEquals(driver,false,sel.getText(DEtinpaistocks_intro).contains("142"),"选小盘股分类的时候，提示无结果");
		
	}
	
	@UIMessage(description = "测试'2012年8月30日总股本一亿以下的股票,点击研报股,要有结果")@Test
	@Parameters({"Reportstocks_btn","Smallcapstocks_intro"})
	public void testReportstocks(String Reportstocks_btn,String Reportstocks_intro)
	{
		System.out.println("testReportstocks");
		sel.click(Reportstocks_btn);
		AssertEquals(driver,false,sel.getText(Reportstocks_intro).contains("0"),"选研报股分类的时候，提示无结果");
		
	}
	
	@UIMessage(description = "高送转的小盘股,点击指标股")@Test
	@Parameters({"Indexstocks_btn","Indexstocks_intro"})
	public void testIndexstocks(String Indexstocks_btn,String Indexstocks_intro) throws Exception{
		ut.QueryResult(sel,"高送转的小盘股");
		System.out.println("testIndexstocks");
		Thread.sleep(2000);
		sel.click(Indexstocks_btn);
		AssertEquals(driver,false,sel.getText(Indexstocks_intro).contains("1"),"选指标股分类的时候，提示无结果");
		//Thread.sleep(2000);
	}
	
	
	
	@UIMessage(description = "")@Test
	@Parameters({ "goodBtn.xpath", "goodBtnGray.xpath", "badBtnGray.xpath" })
	public void testBtnGray(String goodBtn, String goodBtnGray,String badBtnGray) throws Exception {
		System.out.println("testBtnGray");
		ut.QueryResult(sel,"股价小于3");
		System.out.println("testing whether the confirm search result button turned gray ");
		sel.click(goodBtn);
		try {
			Thread.sleep(Long.parseLong(TIMEOUT));
		} catch (InterruptedException e) {
			System.out.println("sleep 30000ms error");
		}
		// System.out.println(sel.getTitle());
		String good = sel.getText(goodBtnGray);
		String bad = sel.getText(badBtnGray);
		System.out.println("点击后正确按钮后灰掉");
		AssertEquals(driver,good, "是");
		System.out.println("点击后错误按钮灰掉");
		AssertEquals(driver,bad, "否");
	}
	
	
	
	
	/**
	 * @param fbXpath
	 * @param BbsXpath
	 * @param suggestXpath
	 * @param contactXpath
	 * @param nameXpath
	 * @param fbSubmit
	 * @param fbOver
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "feedbackLink.xpath", "feedbackbbs.xpath", "suggest.xpath",
		"contact.xpath", "name.xpath", "feedbackSubmit.xpath","feedbackOver.xpath" })
	public void testFeedback(String fbXpath, String BbsXpath,String suggestXpath, 
			String contactXpath, String nameXpath,String fbSubmit, String fbOver) {
		System.out.println("testFeedback");
		String expectBbsUrl = "http://bbs.10jqka.com.cn/ths,5479,1";

		// 测试反馈栏
		sel.click(fbXpath);
		System.out.println(sel.getHtmlSource());
		try {
			Thread.sleep(Long.parseLong(TIMEOUT));
		} catch (InterruptedException e) {
			System.out.println("sleep 30000ms error");
		}
		String feedbackBbsUrl = sel.getAttribute(BbsXpath);
		System.out.println("反馈栏bbs实际地址：" + feedbackBbsUrl + "  反馈栏bbs预期地址"+ expectBbsUrl);
		AssertEquals(driver,feedbackBbsUrl, expectBbsUrl);

		sel.type(suggestXpath, "反馈框 改进输入");
		sel.type(contactXpath, "反馈框 联系方式输入");
		sel.type(nameXpath, "搜牛QA");
		sel.click(fbSubmit);
		sel.click(fbOver);
		System.out.println("反馈栏输入测试");

	}
	
	/**
	 * @param linkUrlXpath
	 * @param linkTitle
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "stockLinkUrl.xpath" })
	public void testStockLink(String linkUrlXpath) throws Exception {

		System.out.println("testStockLink");
		System.out.println(sel.getLocation());
		sel.click("//*[@id='searchform']/input[6]");
		Thread.sleep(2000);
		String url = sel.getAttribute(linkUrlXpath);

		// 股票超链接
		try {
			sel.open(url);
		} catch (Exception e) {
			System.out.println("open " + url + " error!");
		}
		try {
			Thread.sleep(Long.parseLong(TIMEOUT));
		} catch (InterruptedException e) {
			System.out.println("sleep 30000ms error");
		}
		System.out.println("打开股票超链接： " + url);
		System.out.println(sel.getTitle());
		AssertTrue(driver,sel.isElementPresent("//html/body/div[1]/div[1]/div[3]/div/div/div[2]/div[1]"));
		
	}
	


	/**
	 * @param openUrl
	 * @param inputId
	 * @param searchbtn
	 * @param bbsLink
	 * @throws InterruptedException
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "openUrl", "queryInputId", "searchbtn", "bbsLink.xpath" })
	public void testBbsLink(String openUrl, String inputId, String searchbtn,
			String bbsLink) throws InterruptedException {
		sel.open(openUrl);

		System.out.println("testBbsLink");
		sel.type(inputId, query);
		sel.click(searchbtn);
		//Thread.sleep(5000);
		System.out.println(sel.getTitle());
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[6]/div/div/span");
		String bbsUrl = sel.getAttribute(bbsLink);

		// 搜牛搜索 - 综合社区 - 同花顺社区
		try {
			sel.open(bbsUrl);
		} catch (Exception e) {
			System.out.println("open " + bbsUrl + " error!");
		}
		try {
			Thread.sleep(Long.parseLong(TIMEOUT));
		} catch (InterruptedException e) {
			System.out.println("sleep 30000ms error");
		}
		System.out.println("打开产品论坛超链接： " + bbsUrl);
		AssertEquals(driver,sel.getTitle(), "搜牛搜索 - 综合社区 - 同花顺社区");

	}
	
	
	/**
	 * @param url
	 * @param loginwindow
	 * @param closewindow
	 * 当没有登陆时候，加入自选股会有登陆弹出框
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({"openUrl","Login_windowstitle","Login_closewindow"})
	public void testLoginwindow(String url,String loginwindow,String closewindow) throws Exception	{
		System.out.println("testLoginwindow");
		sel.open(url);
		ut.QueryResult(sel,"2012年10月12日股价于2");
		sel.click("//*[@id='offtblbdy']/tr[1]/td[2]/input");
		sel.click("//*[@id='btn_save_selfstock']");
		System.out.println(sel.getText(loginwindow));
		AssertTrue(driver,sel.isElementPresent(loginwindow),"加入自选股没有弹出登陆窗口");
		sel.click(closewindow);
		
		
	}
	@UIMessage(description = "")@Test
	@Parameters({"QuestionCollection_btn","QuestionCollection_Number"})
	public void testQuestionCollection(String QuestionCollection_btn,String QuestionCollection_Number) throws InterruptedException
	{
		System.out.println("testQuestionCollection");
		System.out.println(sel.getLocation());
		sel.click(QuestionCollection_btn);
		System.out.println(sel.getLocation());
		Thread.sleep(1000);
		System.out.println(sel.getText(QuestionCollection_Number));
		AssertEquals(driver,sel.getText(QuestionCollection_Number),"1","收藏问句失败!");	
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"QuestionCollection_Clear","QuestionCollection_Number"})
	public void testQuestionCollection_Clear(String QuestionCollection_Clear,String QuestionCollection_Number) throws InterruptedException
	{
		System.out.println("testQuestionCollection_Clear");
		sel.click(QuestionCollection_Clear);
		Thread.sleep(1000);
		AssertEquals(driver,sel.getText(QuestionCollection_Number),"0","删除问句失败!");	
	}
	@UIMessage(description = "")@Test
	@Parameters({ "linkStockCode.xpath", "stockLinkUrl.xpath" })
	public void testStockLinkAdd(String stockCodeXpath, String linkUrlXpath) {
		System.out.println("testing stock link address");
		//String urlHead = "/stockpage.10jqka.com.cn/";
		//String urlTail = "/index.html";
		Pattern p = Pattern.compile("([0-9]{6})");
		String code = sel.getText(stockCodeXpath);
		Matcher m = p.matcher(code);
		String id = "";
		while (m.find()) {
			System.out.println(m.group());
			id = m.group();
		}
		System.out.println(id);
		String url = sel.getAttribute(linkUrlXpath);
		String benchUrl = "/stockpick/search?tid=stockpick&qs=stockpick_diag&ts=1&w="+id;
		System.out.println("真实股票超链接地址： " + url + "; " + "预期股票超链接地址：" + benchUrl);
		AssertEquals(driver,benchUrl, url);
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"stockpick_interview_link","stockpick_interview_Chart"})
	public void teststockpick_interview(String stockpick_interview_link,String stockpick_interview_Chart) throws InterruptedException
	{
		System.out.println("teststockpick_interview");
		sel.open(openurl);
		sel.click(stockpick_interview_link);
		//Thread.sleep(1000);
		AssertTrue(driver,sel.isElementPresent(stockpick_interview_Chart),"调查问卷没有弹出来");
		boolean flag=sel.isElementPresent(stockpick_interview_link);
		AssertEquals(driver,flag,false,"出来一次调查问卷窗口后，'我要参与'的窗口还在");
	}
	
	@UIMessage(description = "")@Test
	@Parameters("Add_cond_insearch")
	public void testAddConditions_SearchResult(String insearch)throws Exception{
		sel.open(openurl);
		System.out.println("testAddConditions_SearchResult");
		ut.QueryResult(sel,"2012年8月30日短线超卖,股价大于4");
		String Num_1=sel.getText("//html/body/div[8]/div/div[3]/h3/span[2]/em");
		AssertEquals(driver,false,sel.getText("//html/body/div[8]/div/div[3]/h3/span[2]/em").contains("0"),"搜索‘2012年8月30日短线超卖,股价大于4’，提示不存在");
		ut.QueryResult(sel,"2012年8月30日短线超卖");
		sel.click("//html/body/div[8]/div/div[2]/p/span[3]");
		sel.click("//*[@id='add_cond_btn']");
		sel.type(insearch, "股价大于4");
		sel.click("//*[@id='insearch_btn']");
		String Num_2=sel.getText("//html/body/div[7]/div/div[3]/h3/span[2]/em");
		AssertEquals(driver,Num_1,Num_2,"按两种方式搜索的结果不一致");
		//*[@id="insearch_key"]
	}
	
	
	
	
	
	/**
	 * @param host
	 * @param port
	 * @param browser
	 * @param url
	 * @throws MalformedURLException 
	 */
	@BeforeTest
	@Parameters({ "selenium.host", "selenium.port", "selenium.browser","selenium.url" })
	public void beforeTest(String host, String port, String browser, String url) throws MalformedURLException {
		System.out.println("Before test");
		System.out.println(host);
		//Integer Dport = Integer.parseInt(port);
		//sel = new DefaultSelenium(host, Dport, browser, url);
		//sel = new DefaultSelenium(host, Dport,"C:\\Program Files\\Internet Explorer\\iexplore.exe", url);
		//sel = new DefaultSelenium(host, Dport, "*iexplore", url);
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://172.20.23.95:4444/wd/hub"), capability);
	    sel = new WebDriverBackedSeleniumWrapper(driver,url);
	    ut=new util();
		//sel.start();
		sel.useXpathLibrary("javascript-xpath");
		sel.setTimeout(TIMEOUT);
		driver.manage().window().maximize();
		sel.open(openurl);

	}

	
	
	
	
	@AfterTest
	public void afterTest() {
		System.out.println("After test");
		sel.stop();
	}

}
