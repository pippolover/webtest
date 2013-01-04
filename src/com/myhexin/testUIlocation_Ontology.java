package com.myhexin;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
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
import static org.testng.Assert.assertEquals;

public class testUIlocation_Ontology {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private String openurl = "/stockpick?tid=stockpick";
	private static WebDriver driver;

	/**
	 * 选股频道的搜索框和主页Xpath一样，所以可以复用主页的位置
	 * 
	 * @param searchbtn
	 * @param Index_searchbtn_top
	 * @param Index_searchbtn_left
	 * @throws InterruptedException
	 */
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "searchbtn", "Ontology_searchbtn_top","Ontology_searchbtn_left" })
	public void testOntology_searchbtn(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("开始选股频道....");
		System.out.println("testOntology_searchbtn");
		//sel.click("//html/body/div[15]/div/table/tbody/tr/td/div/div/div/div/span");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		//System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道搜索框位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({"openUrl_info_1","Ontology_Allask", "Ontology_Allask_top","Ontology_Allask_left" })
	public void testOntology_ALLask(String url,String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_ALLask");
		sel.open(url);
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[15]/div/table/tbody/tr/td/div/div/div/div/span");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(sel.getLocation());
		AssertEquals(driver, flag, true, "选股频道'大家都在问位置'不对");
	}
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({"Ontology_Logo", "Ontology_Logo_top","Ontology_Logo_left" })
	public void testOntology_Logo(String location, String toplocation,String leftlocation) throws InterruptedException {
		sel.open(openurl);
		System.out.println("testOntology_Logo");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		AssertEquals(driver, flag, true, "选股频道首页'Logo'位置不对");
	}
	
	
	/**
	 * 这里测试输入Query后的“详情”提示
	 * 
	 * @param location
	 * @param toplocation
	 * @param leftlocation
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_QuestionRemind", "Ontology_QuestionRemind_top","Ontology_QuestionRemind_left","Ontology_QuestionRemind_btn" })
	public void testOntology_QuestionRemind(String location,String toplocation, String leftlocation,String btn)
			throws Exception {
		System.out.println("testOntology_QuestionRemind");
		ut.QueryResult(sel, "股价低于5元的股票");
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[6]/div/div/span");
		ut.QueryResult(sel, "股价低于3元的股票");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		//System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'详情'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_SuggestQuery", "Ontology_SuggestQuery_top","Ontology_SuggestQuery_left" })
	public void testOntology_SuggestQuery(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_SuggestQuery");
		System.out.println(sel.getLocation());
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		//System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'可能感兴趣的'位置不对");
	}

	

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_StockOption", "Ontology_StockOption_top","Ontology_StockOption_left" })
	public void testOntology_StockOption(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_StockOption");
		System.out.println(sel.getLocation());
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		AssertEquals(driver, flag, true, "选股频道'股票选项'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_FundOption", "Ontology_FundOption_top","Ontology_FundOption_left" })
	public void testOntology_FundOption(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_FundOption");
		System.out.println(sel.getLocation());
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		AssertEquals(driver, flag, true, "选股频道'基金选项'位置不对");
	}



	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Onlinetoanswer", "Ontology_Onlinetoanswer_top","Ontology_Onlinetoanswer_left" })
	public void testOntology_Onlinetoanswer(String location,String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_Onlinetoanswer");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'在线解答'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Diaocha", "Ontology_Diaocha_top","Ontology_Diaocha_left" })
	public void testOntology_Diaocha(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_Diaocha");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'调查问卷'位置不对");
	}

	// 问答首页结束，开始具体Query页面

	
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Favorit_btn", "Ontology_Favorit_btn_top",
			"Ontology_Favorit_btn_left" })
	public void testOntology_Favorit_btn(String location,
			String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_Favorit_btn");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'搜索框里的五角星'位置不对");
	}
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Stock_pick", "Ontology_Stock_pick_top",
			"Ontology_Stock_pick_left" })
	public void testOntology_Stock_pick(String location,
			String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_Stock_pick_btn");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'收藏选股'位置不对");
	}
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Query_collect", "Ontology_Query_collect_top",
			"Ontology_Query_collect_left" })
	public void testOntology_Query_collect(String location,
			String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_Query_collect");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'收藏问句'位置不对");
	}
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_ExpandDetails","Ontology_ResultTopword", "Ontology_ResultTopword_top","Ontology_ResultTopword_left" })
	public void testOntology_ResultTopword(String Ontology_ExpandDetails,String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_ResultTopword");
		sel.click(Ontology_ExpandDetails);
		System.out.println(sel.getLocation());
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'选股结果'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_StockNum", "Ontology_StockNum_top","Ontology_StockNum_left" })
	public void testOntology_StockNum(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_StockNum");
		System.out.println(sel.getLocation());
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'股票数目'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_GoodPick", "Ontology_GoodPick_top","Ontology_GoodPick_left" })
	public void testOntology_GoodPick(String location, String toplocation,String leftlocation) throws InterruptedException {
		System.out.println("testOntology_GoodPick");
		System.out.println(location);
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'正确'位置不对");
	}
	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_WhetherSatisfied", "Ontology_WhetherSatisfied_top","Ontology_WhetherSatisfied_left" })
	public void testOntology_WhetherSatisfied(String location, String toplocation,
			String leftlocation) throws InterruptedException {
		System.out.println("testOntology_WhetherSatisfied");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'对结果是否满意'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_ConditionCombination","Ontology_ConditionCombination_top","Ontology_ConditionCombination_left" })
	public void testOntology_ConditionCombination(String location,String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_ConditionCombination");
		System.out.println(sel.getLocation());
		sel.click("//*[@id='qusetion_remind']/span[3]");
		           //html/body/div[9]/div/div[2]/p/span[3]
		           
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		System.out.println(sel.getLocation());
		AssertEquals(driver, flag, true, "选股频道'问句分析-条件组合'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({  "Ontology_conditionOuter","Ontology_conditionOuter_top", "Ontology_conditionOuter_left" })
	public void testOntology_conditionOuter(String location, String toplocation, String leftlocation)
			throws InterruptedException {
		System.out.println("testOntology_conditionOuter");
		System.out.println(sel.getLocation());
		//sel.click("//html/body/div[8]/div/div[2]/p/span[3]");
		           
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'问句分析-条件1'位置不对");

	}

	
	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Add_cond", "Ontology_Add_cond","Ontology_Add_cond_top", "Ontology_Add_cond_left" })
	public void testOntology_Add_cond(String questuinbtn,String location, String toplocation, String leftlocation)throws InterruptedException {
		System.out.println("testOntology_Add_cond");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'追加条件'位置不对");

	}
	
	

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_GOtoBBS", "Ontology_GOtoBBS_top",
			"Ontology_GOtoBBS_left" })
	public void testOntology_GOtoBBS(String location, String toplocation,
			String leftlocation) throws InterruptedException {
		System.out.println("testOntology_GOtoBBS");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'去论坛看看'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Feedback", "Ontology_Feedback_top",
			"Ontology_Feedback_left" })
	public void testOntology_Feedback(String location, String toplocation,
			String leftlocation) throws InterruptedException {
		System.out.println("testOntology_Feedback");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'提意见'位置不对");
	}

	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_Saveselfstock", "Ontology_Saveselfstock_top",
			"Ontology_Saveselfstock_left" })
	public void testOntology_Saveselfstock(String location, String toplocation,
			String leftlocation) throws InterruptedException {
		System.out.println("testOntology_Saveselfstock");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'加入自选股'位置不对");
	}



	@UIMessage(description = "")@Test(groups = { "Ontology" })
	@Parameters({ "Ontology_History", "Ontology_History_top",
			"Ontology_History_left" })
	public void testOntology_History(String location, String toplocation,
			String leftlocation) throws InterruptedException {
		System.out.println("testOntology_History");
		boolean flag = ut.getELementposition(sel, location, toplocation,
				leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'我问过的'位置不对");
	}

	@UIMessage(description = "")@Test
	@Parameters({"openUrl_info_2","ElectionUshimata","ElectionUshimata_top","ElectionUshimata_left"})
	public void testOntology_ElectionUshimataLocation(String Url,String location,
			String toplocation,String leftlocation)throws InterruptedException{
		sel.open(Url);
		System.out.println("testOntology_ElectionUshimataLocation");
		boolean flag = ut.getELementposition(sel, location, toplocation,leftlocation);
		System.out.println(flag);
		AssertEquals(driver, flag, true, "选股频道'选牛股啦'位置不对");
	}
	
	
	
	@BeforeTest
	// 测试开始之前要执行这个
	@Parameters({ "selenium.host", "selenium.port", "selenium.browser","selenium.url" })
	public void beforeTest(String host, int port, String browser, String url)throws MalformedURLException {
		System.out.println("Before test");
		System.out.println(host);
		// Integer Dport = Integer.parseInt(port);
		// sel = new DefaultSelenium(host, Dport, browser, url);
		// sel = new DefaultSelenium(host, Dport,
		// "C:\\Program Files\\Internet Explorer\\iexplore.exe", url);
		// sel = new DefaultSelenium(host, Dport, "*iexplore", url);
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(
				new URL("http://172.20.23.95:4444/wd/hub"), capability);
		sel = new WebDriverBackedSeleniumWrapper(driver, url);
		ut = new util();
		sel.open(openurl);
		driver.manage().window().maximize();
		// sel.start();
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
