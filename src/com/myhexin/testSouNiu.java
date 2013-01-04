package com.myhexin;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.internal.selenesedriver.GetTitle;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
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

import com.myhexin.common.*;

public class testSouNiu{
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	public static util ut;
	private static WebDriver driver;
	
	
	
	@UIMessage(description = "")@Test
	  @Parameters()
	  public void testTopRightClickChange(){
		System.out.println("testTopRightClickChange");
		//String title=sel.getText("//html/body/div[3]/div[2]/ul/li/span[2]");
		//System.out.println(title);
		AssertEquals(driver,sel.getText("//html/body/div[3]/div[2]/ul/li/span[2]"),"主力追踪选股", "点击无效");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters()
	  public void testBottomClickChange(){
		System.out.println("testBottomClickChange");
		sel.click("//html/body/div[4]/div[2]/span[4]");
		System.out.println(sel.getLocation());
		String Sty_num=sel.getAttribute("//html/body/div[4]/div[2]/span[4]@class");
		System.out.println(Sty_num);
		//AssertTrue(driver,Sty_num.contains("1"));
		System.out.println(Sty_num.contains("s_a_btn cur_btn"));
		//AssertEquals(driver,true,Sty_num.contains("s_a_btn cur_btn"),"点击无效");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters("openUrl_info_1")
	  public void testFeedBackOpinion(String Url){
		System.out.println("testFeedBackOpinion");
		sel.open(Url);
		driver.manage().window().maximize();
		sel.click("//*[@id='indexfb']");
		String url=sel.getAttribute("//*[@id='indexfb']@href");
		sel.open(url);
		//String PostingFeedBack=sel.getText("/html/body/div/div[3]/div/div/div/h2/a");
		AssertEquals(driver,true,sel.getText("//html/body/div[1]/div[3]/div[1]/div[1]/div/h2/a").contains("有任何问题或建议，欢迎在此发帖"), "‘反馈意见’按钮点击无效");
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","JPWJfirstSentence_xpath","JPWJfirstSentence_xpath_seacond"})
	  public void testJPWJ(String Url,String JPWJfirstSentence_xpath,String JPWJfirstSentence_xpath_seacond) throws InterruptedException{
		System.out.println("testJPWJ");
		//sel.open(Url);
		//driver.manage().window().maximize();
		String url=sel.getAttribute("//html/body/div[3]/div/ul/li/a@href");
		String title=sel.getAttribute("//html/body/div[3]/div/ul/li/a@title");
		//System.out.println(title);
		sel.open(url);
		//sel.click("//html/body/div[6]/div/div/span");
		System.out.println(sel.getLocation());
		String out=sel.getAttribute(JPWJfirstSentence_xpath_seacond);
		//System.out.println(out);
		AssertTrue(driver,sel.getAttribute(JPWJfirstSentence_xpath_seacond).equals(title));
		AssertEquals(driver,sel.getAttribute(JPWJfirstSentence_xpath_seacond),title, "‘金牌问句’点击跳转不成功");
		Thread.sleep(5000);
	  }
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"search_box_xpath"})
	  public void testSearch_box(String search_box_xpath){
		System.out.println("testSearch_box");
		String text=sel.getAttribute("//*[@id='auto']@class");
		System.out.println(text);
		AssertEquals(driver,sel.getAttribute("//*[@id='auto']@class"),"search_input favorit_input default_input", "打开新版搜牛首页，搜索框内不显示'请输入一句话进行选股'");
		sel.click("//*[@id='auto']");
		String Text=sel.getAttribute("//*[@id='auto']@class");
		System.out.println(Text);
		AssertEquals(driver,sel.getAttribute("//*[@id='auto']@class"),"search_input favorit_input default_input", "在索框内点击，准备输入内容时，语句'请输入一句话进行选股'不消失");
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"Employee_Query","search_box_xpath","openUrl_info_1","Employee_Query_2"})
	  public void testSearch_box_collectFavorite(String QE,String search_box_xpath,String Url,String QE_2)throws Exception {
		System.out.println("testSearch_box_collectFavorite");
		sel.open(Url);
		sel.click("//*[@id='q_collect']");
		Thread.sleep(5000);
		String Num=sel.getText("//*[@id='q_collect']/em");
		System.out.println(Num);
		sel.type("//*[@id='auto']", QE);
		Thread.sleep(5000);
		sel.click("//*[@id='favorit_btn']");
		Thread.sleep(5000);
		String Flag=sel.getAttribute("//*[@id='favorit_btn']@class");
		System.out.println(sel.getLocation());
		System.out.println(Flag);
		//sel.click("//*[@id='q_collect']");
		String Num2=sel.getText("//*[@id='q_collect']/em");
		System.out.println(Num2);
		System.out.println(sel.getAttribute("//*[@id='favorit_btn']@class"));
		AssertEquals(driver,sel.getAttribute("//*[@id='favorit_btn']@class"),"favorit_btn light_favorit_btn", "在索框内点击收藏标签后，没有弹出“已收藏”窗口");
		AssertTrue(driver,sel.getText("//*[@id='q_collect']/em").contains("1"), "收藏问句后收藏问句的数目没有加1");
		
		
		
		sel.click("//*[@id='q_collect']");
		Thread.sleep(5000);
		sel.click("//*[@id='top_collect_list']/li/a");
		Thread.sleep(5000);
		//sel.click("//html/body/div[6]/div/div/span");
		System.out.println(sel.getLocation());
		String Favorites_results=sel.getAttribute("//*[@id='auto']@value");
		System.out.println(Favorites_results);
		AssertEquals(driver,sel.getAttribute("//*[@id='auto']@value"),QE, "点击收藏的问句，没有跳到正确的页面");
		
		//sel.open(Url);
		//sel.type("//*[@id='auto']", QE);
		//.sleep(5000);
		//sel.click("//*[@id='favorit_btn']");
		//sel.type("//*[@id='auto']", QE);
		//sel.click("//*[@id='favorit_btn']");
		//Thread.sleep(5000);
		sel.click("//*[@id='favorit_btn']");
		Thread.sleep(2000);
		//sel.click("//*[@id='top_collect_list']/li/span");
		sel.click("//*[@id='q_collect']");
		Thread.sleep(1000);
		String L=sel.getText("//*[@id='q_collect']/em");
		System.out.println(L);
		AssertEquals(driver,true,L.contains("0"),"将仅有的一条收藏问句删除后，收藏问句不为0");
		Thread.sleep(5000);
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"OnlineToAnswerFlag_xpath"})
	   public void testOnlineToAnswer(String OnlineToAnswerFlag_xpath)throws Exception{
		System.out.println("testOnlineToAnswer");
		//sel.click(OnlineToAnswerFlag_xpath);
		sel.click("//*[@id='im_icon']");
		//String url=sel.getAttribute("//*[@id='im_icon']@href");
		//System.out.println(url);
		//sel.open(url);
		//System.out.println(sel.getLocation());
		Thread.sleep(5000);
		AssertEquals(driver,true,sel.getAttribute("//*[@id='logo_box']@class]").contains("logo_box_sn"),"点击在线解答按钮，相关窗口没有打开");
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"QuestionnaireFlag_xpath","Title_xpath"})
	   public void testQuestionnaire(String QuestionnaireFlag_xpath,String Title_xpath)throws Exception{
		sel.open(QuestionnaireFlag_xpath);
		AssertEquals(driver,"搜牛选股调查问卷",sel.getText(Title_xpath),"点击'调查问卷'按钮，相关窗口没有打开");
		Thread.sleep(5000);
	}
	
	
	
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"User_Experience_Survey_Flag_xpath","Name","Tel","openUrl_info_1"})
	   public void testUser_Experience_Survey(String User_Experience_Survey_Flag_xpath,String Name,String Tel,String Url)throws Exception{
		sel.open(Url);
		Thread.sleep(5000);
		sel.click(User_Experience_Survey_Flag_xpath);
		//String Text=sel.getText("//html/body/div[13]/div/table/tbody/tr/td/div/table/tbody/tr/td/div");
		//System.out.println(Text);
		//AssertEquals(driver,"搜牛选股产品用户调查",sel.getText("//html/body/div[13]/div/table/tbody/tr/td/div/table/tbody/tr/td/div"),"点击'用户体验调查'按钮，相关窗口没有打开");
		sel.type("//*[@id='interViewPopupForm']/table/tbody/tr/td[2]/input", Name);
		sel.click("//*[@id='gender_1']");
		sel.type("//*[@id='interViewPopupForm']/table/tbody/tr[3]/td[2]/input", Tel);
		sel.click("//*[@id='interView']/div[3]/form/div[3]/input");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters({"JiJin_xpath","JiJin_site_Detail"})
	   public void testDetailOfJiJin(String JiJin_xpath,String JiJin_site_Detail)throws Exception{
		String value=sel.getText(JiJin_xpath);
		System.out.println(value);
		String url=sel.getAttribute(JiJin_site_Detail);
		System.out.println(url);
		sel.open(url);
		sel.click("//html/body/div[6]/div/div/span");
		AssertTrue(driver,sel.getAttribute("//*[@id='auto']@value").contains(value),"点击选股方案集锦的详细内容，相关搜索网页没有打开");
		Thread.sleep(5000);
		System.out.println(sel.getLocation());
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"Flag_xpath","openUrl_info_1"})
	   public void testStoreSelectStocks(String Flag_xpath,String Url)throws Exception{
		sel.open(Url);
		sel.click(Flag_xpath);
		Thread.sleep(5000);
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"Register_Flag_xpath","Login_Flag_xpath","openUrl_info_1"})
	   public void testRegisterAndLogin(String Register_Flag_xpath,String Login_Flag_xpath,String Url)throws Exception{
		/*sel.open(Url);
		sel.click(Register_Flag_xpath);
		//String Title=sel.getAttribute("//html/body/div/div/div/img@src");
		//System.out.println(Title);
		AssertEquals(driver,"images/login.png",sel.getAttribute("//html/body/div/div/div/img@src"),"点击'注册'按钮，相关窗口没有打开");
		Thread.sleep(5000);*/
		sel.open(Url);
		sel.click(Login_Flag_xpath);
		Thread.sleep(5000);
		AssertEquals(driver,"欢迎进入同花顺网站",sel.getText("//html/body/div[4]/div[2]/div/div[1]/h2"),"点击'登录'按钮，相关窗口没有打开");
		Thread.sleep(5000);
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"MainLabel_xpath","Employee_Query"})
	   public void testClickMainLabel(String MainLabel_xpath,String QE)throws Exception{
		sel.type("//*[@id='auto']", QE);
		Thread.sleep(5000);
		sel.click(MainLabel_xpath);
		AssertEquals(driver,"/stockpick?tid=stockpick",sel.getAttribute("//html/body/div[2]/div/a@href"),"点击主标签按钮，没有正确跳转");
		Thread.sleep(5000);
	}
	
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1"})
	  public void testButton_YanBaoGu(String Url) throws InterruptedException{
		System.out.println("testButton_YanBaoGu");
		sel.open(Url);
		driver.manage().window().maximize();
		String url=sel.getAttribute("//html/body/div[3]/div/ul/li/a@href");
		sel.open(url);
		sel.click("//html/body/div[7]/div/div[3]/div/ul/li[4]");
		String explain=sel.getText("//html/body/div[7]/div/div[3]/div/div[5]/div/p/span[2]");
		AssertEquals(driver,explain,"研报股：研究报告关注的股票", "点击‘研报股’按钮没有弹出解释窗口");
		sel.click("//html/body/div[7]/div/div[3]/div/div[7]/em");
		String state=sel.getText("//html/body/div[7]/div/div[3]/div/div[7]/em");
		System.out.println(state);
		//AssertEquals(driver,state,"close", "点击‘基本筛选’按钮没有反应");
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1"})
	  public void testXGFangAnJiJin(String Url) throws InterruptedException{
		System.out.println("testXGFangAnJiJjn");
		sel.open(Url);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		System.out.println(sel.getLocation());
		//String Title=sel.getText("//html/body/div[4]/div/ul[2]/li/div/a/ul/li");
		String title=sel.getText("//html/body/div[4]/div/ul[2]/li/div/h4/a");
		System.out.println(title);
		
		
		String Title=sel.getText("//html/body/div[4]/div/ul[3]/li/div/h4/a");
		System.out.println(Title);
		String url=sel.getAttribute("//html/body/div[4]/div/ul[3]/li/div/h4/a@href");
		sel.open(url);
		//sel.click("//html/body/div[4]/div/ul[2]/li/div/h4/a@href");
		//Thread.sleep(5000);
		System.out.println(sel.getLocation());
		//System.out.println(sel.getLocation());
		//String out=sel.getAttribute(JPWJfirstSentence_xpath_seacond);
		//System.out.println(out);
		//AssertTrue(driver,sel.getAttribute("//*[@id='auto']@value").contains(title));
		AssertEquals(driver,true,sel.getText("//html/body/div[2]/div[5]/div/div/div[2]/div/strong").contains(Title), "‘选股方案集锦’点击跳转不成功");
		Thread.sleep(5000);
	  }
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","Popular_Stock_firstXpath","MatchTo_Popular_Stock_firstXpath","Popular_Stock_firstXpath_href"})
	  public void testPopular_Stock_first(String Url,String Popular_Stock_firstXpath,
			  String MatchTo_Popular_Stock_firstXpath,
			  String Popular_Stock_firstXpath_href)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("testPopular_Stock_first");
		//Thread.sleep(5000);
		//System.out.println(sel.getLocation());
		String Title_First=sel.getText(Popular_Stock_firstXpath);
		//System.out.println(Title_First);
		String url=sel.getAttribute(Popular_Stock_firstXpath_href);
		sel.open(url);
		//System.out.println(sel.getLocation());
		String MtachTo_Title_First=sel.getValue(MatchTo_Popular_Stock_firstXpath);
		//System.out.println(MtachTo_Title_First);
		//Thread.sleep(3000);
		AssertEquals(driver,Title_First,MtachTo_Title_First,"点击‘热门选股’第一条，跳转不成功");
		AssertEquals(driver,false,sel.getText("//*[@id='r_num_box']/span[2]/em").contains("0"),"点击‘热门选股’第一条，跳转后的网页搜索到的股票数目是0");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","Popular_Stock_secondXpath","MatchTo_Popular_Stock_secondXpath","Popular_Stock_secondXpath_href"})
	  public void testPopular_Stock_second(String Url,String Popular_Stock_secondXpath,
			  String MatchTo_Popular_Stock_secondXpath,
			  String Popular_Stock_secondXpath_href)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("testPopular_Stock_second");
		//Thread.sleep(5000);
		//System.out.println(sel.getLocation());
		String Title_second=sel.getText(Popular_Stock_secondXpath);
		//System.out.println(Title_second);
		String url=sel.getAttribute(Popular_Stock_secondXpath_href);
		sel.open(url);
		//System.out.println(sel.getLocation());
		String MtachTo_Title_second=sel.getValue(MatchTo_Popular_Stock_secondXpath);
		//System.out.println(MtachTo_Title_second);
		//Thread.sleep(3000);
		AssertEquals(driver,Title_second,MtachTo_Title_second,"点击‘热门选股’第二条，跳转不成功");
		AssertEquals(driver,false,sel.getText("//*[@id='r_num_box']/span[2]/em").contains("0"),"点击‘热门选股’第二条，跳转后的网页搜索到的股票数目是0");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","sevenPage_Select_Ushimata_fist_Xpath","sevenPage_Select_Ushimata_Num_fist_Xpath",
		  "MatchTo_sevenPage_Select_Ushimata_Num_fist_Xpath"})
	  public void testSelect_Ushimata_sevenPage(String Url,String sevenPage_Select_Ushimata_fist_Xpath,
			  String sevenPage_Select_Ushimata_Num_fist_Xpath,
			  String MatchTo_sevenPage_Select_Ushimata_Num_fist_Xpath)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("testSelect_Ushimata_sevenPage");
		String sevenPage_num=sel.getText(sevenPage_Select_Ushimata_Num_fist_Xpath);
		String url=sel.getAttribute(sevenPage_Select_Ushimata_fist_Xpath);
		sel.open(url);
		//Thread.sleep(3000);
		//System.out.println(sel.getLocation());
		String MatchTo_sevenPage_num=sel.getText(MatchTo_sevenPage_Select_Ushimata_Num_fist_Xpath);
		AssertEquals(driver,true,sevenPage_num.contains(MatchTo_sevenPage_num),"选出股票数目不匹配");
	}
	
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","sixPage_Select_Ushimata_fist_Xpath","sixPage_Select_Ushimata_Num_fist_Xpath",
		  "MatchTo_sixPage_Select_Ushimata_Num_fist_Xpath"})
	  public void testSelect_Ushimata_sixPage(String Url,String sixPage_Select_Ushimata_fist_Xpath,
			  String sixPage_Select_Ushimata_Num_fist_Xpath,
			  String MatchTo_sixPage_Select_Ushimata_Num_fist_Xpath)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("testSelect_Ushimata_sixPage");
		String sixPage_num=sel.getText(sixPage_Select_Ushimata_Num_fist_Xpath);
		String url=sel.getAttribute(sixPage_Select_Ushimata_fist_Xpath);
		sel.open(url);
		//Thread.sleep(3000);
		//System.out.println(sel.getLocation());
		String MatchTo_sixPage_num=sel.getText(MatchTo_sixPage_Select_Ushimata_Num_fist_Xpath);
		AssertEquals(driver,true,sixPage_num.contains(MatchTo_sixPage_num),"选出股票数目不匹配");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_1","firstPage_Select_Ushimata_fist_Xpath","firstPage_Select_Ushimata_Num_fist_Xpath",
		  "MatchTo_firstPage_Select_Ushimata_Num_fist_Xpath"})
	  public void testSelect_Ushimata_firstPage(String Url,String firstPage_Select_Ushimata_fist_Xpath,
			  String firstPage_Select_Ushimata_Num_fist_Xpath,
			  String MatchTo_firstPage_Select_Ushimata_Num_fist_Xpath)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("testSelect_Ushimata_firstPage");
		String FirstPage_num=sel.getText(firstPage_Select_Ushimata_Num_fist_Xpath);
		String url=sel.getAttribute(firstPage_Select_Ushimata_fist_Xpath);
		sel.open(url);
		Thread.sleep(3000);
		//System.out.println(sel.getLocation());
		String MatchTo_FirstPage_num=sel.getText(MatchTo_firstPage_Select_Ushimata_Num_fist_Xpath);
		AssertEquals(driver,true,FirstPage_num.contains(MatchTo_FirstPage_num),"选出股票数目不匹配");
	}
	
	@UIMessage(description = "A版选股首页,点击页码5,有没有跳转")@Test
	  @Parameters({"openUrl_info_1","PagingBbutton_Fourth","MatchTo_PagingBbutton_Fourth"})
	  public void test_FormFeed(String Url,String PagingBbutton_Fourth,
			  String MatchTo_PagingBbutton_Fourth)throws InterruptedException{
		sel.open(Url);
		Thread.sleep(3000);
		driver.manage().window().maximize();
		System.out.println("test_FormFeed");
		sel.click(PagingBbutton_Fourth);
		Thread.sleep(1000);
        //String Style_num=sel.getAttribute(MatchTo_PagingBbutton_Fourth);
		System.out.println(sel.getAttribute("//*[@id='s_a_btn_box']/span[5]@class"));
		AssertEquals(driver,sel.getAttribute("//*[@id='s_a_btn_box']/span[5]@class"),"s_a_btn cur_btn","‘选牛股啦’页面转换不成功");
	}
	
	@UIMessage(description = "")@Test
	  @Parameters({"openUrl_info_2","First_Xpath","MatchTo_First_Xpath",
		  "Second_Xpath","MatchTo_Second_Xpath"})
	  public void test_dajia_douzaiwen(String Url,String First_Xpath,
			  String MatchTo_First_Xpath,String Second_Xpath,
			  String MatchTo_Second_Xpath)throws InterruptedException{
		sel.open(Url);
		driver.manage().window().maximize();
		System.out.println("test_dajia_douzaiwen");
		String Title=sel.getText(First_Xpath);
		System.out.println(Title);
		String TitleArr[] = Title.split("\\…");
		System.out.println(TitleArr[0]);
		sel.click(First_Xpath);
		Thread.sleep(5000);
		//.getAttribute(MatchTo_First_Xpath);
		AssertTrue(driver,sel.getTitle().contains(TitleArr[0]),"点击B版首页面第一个问句，得到网页与问句不匹配");
		sel.open(Url);
		driver.manage().window().maximize();
		String Title_Second=sel.getText(Second_Xpath);
		System.out.println(Title_Second);
		String Title_secondArr[] = Title_Second.split("\\…");
		System.out.println((Title_secondArr)[0]);
		sel.click(Second_Xpath);
		Thread.sleep(5000);
		//sel.getAttribute(MatchTo_Second_Xpath);
		AssertTrue(driver,sel.getTitle().contains(Title_secondArr[0]),"点击B版首页面第二个问句，得到网页与问句不匹配");
	}

	
	@UIMessage(description = "")@Test  //解析条件中 修改 / 增加的条件无匹配结果,出现的提示
	@Parameters({"openUrl_info_2","qusetion_remind","add_cond","noResultHint","resultNum"})
	public void test_noResultHint(String Url,String qusetion_remind,String add_cond,String noResultHint,
			String resultNum)throws Exception{
	sel.open(Url);
	driver.manage().window().maximize();
	System.out.println("test_noResultHint");
	ut.QueryResult(sel, "2012年10月10号股价小于2");
	sel.click(qusetion_remind);
	sel.click(add_cond);
	sel.type("//*[@id='insearch_key']", "有"); //增加条件问句"有"
	sel.click("//*[@id='insearch_btn']");
	Thread.sleep(20000);
	//sel.isVisible(noResultHint);
	AssertEquals(driver,sel.isVisible(noResultHint),true,"增加条件无结果没有显示提示");
	AssertEquals(driver,sel.getText(resultNum),"5","添加条件后,选股结果变化");
	
	}
	
	
	
	@UIMessage(description = "")@Test //点选股首页我的自选股时会跳出的提示登录
    @Parameters({"openUrl_info_2","queryarea","queryarea_selfstock_tip"})
	public void test_queryAreaSelfStock(String Url,String queryarea,String queryarea_selfstock_tip)throws Exception{
	sel.open(Url);
	driver.manage().window().maximize();
	System.out.println("test_queryAreaSelfstock");
	sel.click(queryarea); //点击搜索框下的我的自选股
	AssertEquals(driver,sel.isVisible(queryarea_selfstock_tip),true,"点击我的自选股后,跳出一登录...的标签");
	
	
	}
	
	
	
/*	@UIMessage(description = "")@Test //在搜索问句无添加自选股的状态下,跳到自选股查看
	@Parameters({"openUrl_info_2","queryarea","denglu","selfstock_noresult"})
	public void test_clickQueryAreaSelfStock(String Url,String  queryarea,String denglu,String selfstock_noresult)throws Exception{
	sel.open(Url);
	driver.manage().window().maximize();
	ut.QueryResult(sel, "2012年10月10号股价小于2");
	sel.click(queryarea); //点击搜索下的我的自选股
	sel.click(denglu);   //点击登录
	sel.type("//*[@id='username']", "wdsrkj827"); //网站用户名登录
	sel.type("//*[@id='password']", "123456"); //网站密码
	sel.click("//*[@id='loginBtn']");//点击登录
	Thread.sleep(3000);
	sel.click(queryarea);//点击搜索下的我的自选股
	sel.click("//*[@id='searchform']/input[6]"); //点击执行选股
	Thread.sleep(3000);
	boolean selfstock = sel.isVisible(selfstock_noresult);
	AssertEquals(driver, selfstock, true, "在搜索问句无添加自选股的状态下,跳到自选股查看,没有无自选股提示出现");
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"queryarea_all","addselfstock","save_selfstock","queryarea","disabledvalue"})
	public void test_addQueryAreaSelfStock(String queryarea_all,String addselfstock,String save_selfstock
			,String queryarea,String disabledvalue)throws Exception {
	sel.click(queryarea_all);
	sel.click("//*[@id='searchform']/input[6]");
	Thread.sleep(3000);
	
	if (sel.getAttribute(disabledvalue)=="disabled"){ 
		 break;
	}
	else 
	{sel.click(addselfstock);
	sel.click(save_selfstock);
	Thread.sleep(5000);
	}
	sel.click(queryarea);
	sel.click("//*[@id='searchform']/input[6]");
	String num = sel.getText("//*[@id='r_num_box']/span/em[1]");
	AssertEquals(driver, 1,num, "我的自选股中没添加一个自选股");
	
	}*/
	
	@UIMessage(description = "")@Test //b版首页的换一换功能
	@Parameters({"openUrl_info_2","queryListEp","switch_btn"})
	public void test_switch(String Url,String queryListEp,String switch_btn)throws Exception{
	sel.open(Url);
	driver.manage().window().maximize();
	String ql = sel.getText(queryListEp); //大家都在问第一行,第一句问句
	sel.click(switch_btn); //点击换一换
	String ql2 = sel.getText(queryListEp);
	AssertEquals(driver, ql==ql2,false,"两个得到的字符串相同,没有执行换一换");
	}
	
	@UIMessage(description = "")@Test
	@Parameters({"openUrl_info_2","gengduo"})
	public void test_gengduo(String Url,String gengduo)throws Exception{
	sel.open(Url);	
	driver.manage().window().maximize();
	String gdURL = sel.getAttribute(gengduo);
	sel.open(gdURL);
	String tl = sel.getTitle();
	System.out.println(tl);
	AssertEquals(driver,"第1页-精选股票问句列表-搜牛选股", tl,"点击更多没有跳转到seo界面");
	}
	
	@UIMessage(description = "")@Test //搜牛搜索首页更多下面的选股面面观有没有跳到SEO页面
	@Parameters({"searchGengDuo","seo"})
	public  void test_search_gengduo(String searchGengDuo,String seo)throws Exception{
	sel.open("http://search.10jqka.com.cn/");
	driver.manage().window().maximize();
	sel.click(searchGengDuo);
	String seoUrl = sel.getAttribute(seo);
	sel.open(seoUrl);
	String tl1 = sel.getTitle();
	System.out.println(tl1);
	AssertEquals(driver,"第1页-精选股票问句列表-搜牛选股", tl1,"点击搜索更多下的选股面面观没有跳转到seo界面");
	}
	
	
	@UIMessage(description = "搜“连续9年每股收益大于1元”点往右移，看股票代码位置有没有改变")@Test(groups={"xuangu"}) //股票代码和简称有没有固定,(在往右移动后)
	@Parameters({"openUrl_info_2","test_fixedRow_code","test_fixedRow_code_top","test_fixedRow_code_left"})
	   public void test_fixedRow_code(String Url,String codelocation,String codetoplocation,String codeleftlocation) throws Exception
	{	
		  System.out.println("开始测试固定股票代码简称的case...");
		  System.out.println("test_fixedRow");
		  sel.open(Url);
		  ut.QueryResult(sel, "连续9年每股收益大于1元");
		  System.out.println(sel.getLocation());
		  boolean flag=ut.getELementposition(sel, codelocation,codetoplocation, codeleftlocation);
		  sel.click("//*[@id='scrollBtnBox']/span[2]"); //往右移动
		  AssertEquals(driver,flag,true,"选股结果页中的'股票代码'位置改变");
		  
	}
	
	@UIMessage(description = "搜pe，添加条件股价，点击恢复默认条件")@Test
	@Parameters({"qusetion_remind","add_cond","recoverCondsBtn","ZuiXinJia","tiaojian"})
	   public void test_recoverCondsBtn(String qusetion_remind,String add_cond,String recoverCondsBtn,String ZuiXinJia,String tiaojian) throws Exception
	{	
	   System.out.println("测试恢复默认条件");
	   System.out.println("test_recoverCondsBtn");
	   ut.QueryResult(sel, "pe");
	   sel.click(qusetion_remind); //点击查看&添加条件
	   Thread.sleep(2000);
	   sel.click(add_cond); //点击增加条件
	   sel.type("//*[@id='insearch_key']", "股价");
	   sel.click("//*[@id='insearch_btn']");//点击确定
	   Thread.sleep(20000);
	  String gj = sel.getText(ZuiXinJia);
	   System.out.println(gj);  //true 为添加条件最新价存在
	   System.out.println("11111111");
	   sel.click(recoverCondsBtn); //恢复默认条件
	   Thread.sleep(2000);
	  String  tj =  sel.getText(ZuiXinJia);
	  System.out.println(tj);   //为空,证明恢复默认后,最新价不存在
	   AssertEquals(driver, "", tj,"恢复默认条件后,新增条件股价还存在");
	   
	   
	}
	  
	@UIMessage(description = "搜市现率0-6，市盈率0-16的股票,删除条件2，点击恢复默认条件")@Test
	@Parameters({"qusetion_remind","del_cond","recoverCondsBtn","pe","tiaojian"})
	   public void test_recoverCondsBtn_second(String qusetion_remind,String del_cond,String recoverCondsBtn,String pe,String tiaojian) throws Exception
	{	
	   System.out.println("test_recoverCondsBtn_second");
	   ut.QueryResult(sel, "市现率0-6，市盈率0-16的股票");
	   sel.click(qusetion_remind); //点击查看&添加条件
	   Thread.sleep(3000);
	   String sy = sel.getText(pe);
	   System.out.println(sy);
	   sel.click(del_cond);
	   sel.click("//*[@id='qinfoForm']/div[2]/a[1]");
	   Thread.sleep(3000);
	   
	   sel.click(recoverCondsBtn);
	   Thread.sleep(3000);
	   String shiyinglv = sel.getText(pe);
	   AssertEquals(driver, sy,shiyinglv, "恢复默认条件后,删除条件不存在");  
	}
	
	
	 
	@UIMessage(description = "搜平安银行营业收入,鼠标移动数据上，有没有出现提示")@Test
	@Parameters({"openUrl_info_2","yingYeShouRu"})
	   public void test_shuJuSouSuo(String Url,String yingYeShouRu) throws Exception
	{	
		  System.out.println("测试 数值搜索");
		  System.out.println("test_shuJuSouSuo");
		  sel.open(Url);
		  ut.QueryResult(sel, "平安银行营业收入");
		  sel.mouseOver(yingYeShouRu);
		  AssertEquals(driver, "odd_row  cur_tr", sel.getAttribute("//*[@id='offtblbdy']/tr@class"));
	   
	
	}
	
	@UIMessage(description = "添加净利润,鼠标移动净利润数据上，有没有出现提示")@Test
	@Parameters({"qusetion_remind","add_cond","JinLiRun"})
	   public void test_shuJuSouSuo_Second(String qusetion_remind, String add_cond ,String JinLiRun ) throws Exception
	{
		sel.click(qusetion_remind); //点击查看&添加条件
		   Thread.sleep(1000);
		   sel.click(add_cond); //点击增加条件
		   sel.type("//*[@id='insearch_key']", "净利润");
		   sel.click("//*[@id='insearch_btn']");//点击确定
		   Thread.sleep(20000);
		   sel.mouseOver(JinLiRun);
		   AssertEquals(driver, "odd_row  cur_tr", sel.getAttribute("//*[@id='offtblbdy']/tr@class"));
		   
	}
	
	@UIMessage(description = "点击下一页,有数据显示")@Test
	@Parameters({"openUrl_info_2","next"})
	   public void test_xiayiye(String Url,String next) throws Exception
	   {
		 sel.open(Url);
		 ut.QueryResult(sel, "总资产");//搜总资产
		 Thread.sleep(3000);
		 sel.click(next);
		 AssertTrue(driver, sel.isElementPresent("//*[@id='offtblbdy']/tr[1]/td[2]/span"));
	   }
	
	@UIMessage(description = "搜macd金叉,会出现小贴士")@Test
	@Parameters({"openUrl_info_2","shangchuan"})
	   public void test_xiaotieshi(String Url,String shangchuan) throws Exception
	   {
		 sel.open(Url);
		 ut.QueryResult(sel, "macd金叉");
		 Thread.sleep(3000);
		 AssertEquals(driver, sel.getText(shangchuan), "上穿：","搜索macd金叉后 在小帖士中没有上穿出现");
		 
	   }
	
	@UIMessage(description = "小贴士后面的问号提示")@Test
	@Parameters({"matchTip","intro"})
	   public void test_matchTipTop(String matchTip,String  intro) throws Exception
	   {sel.mouseOver(matchTip);
	   Thread.sleep(3000);
	   AssertEquals(driver, sel.getAttribute(intro), "display: inline;","鼠标移到小贴后的问句上,没有出现提示" );
	   }
	
	@UIMessage(description = "小贴士下面的问句链接")@Test
	@Parameters({"XTSwenju"})
	   public void test_clickXTSwenju(String XTSwenju) throws Exception
	   {
		String text = sel.getText(XTSwenju);
		System.out.println(text);
		sel.click(XTSwenju);
	    Thread.sleep(3000);
	    for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);      //切换到新窗口
			}
	    Thread.sleep(3000);
	    System.out.println(sel.getTitle());
	    AssertTrue(driver, sel.getTitle().contains(text),"小贴士下的问句点击不能跳转");
	   }
	
	@UIMessage(description = "小贴士下面的问句链接")@Test
	@Parameters({"XTSgengduo","tips_crumb"})
	   public void test_clickXTSgengduo(String XTSgengduo,String tips_crumb) throws Exception
	   {	
		 sel.click(XTSgengduo);
		 Thread.sleep(2000);
		 for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
		    Thread.sleep(4000);
		    System.out.println(sel.getText(tips_crumb));
		    AssertEquals(driver, sel.getText(tips_crumb), "您的位置：搜牛小贴士（以下范例仅供参考，具体结果以行情为准）","更多没有跳转到小贴士汇总页");  
		

	   }
	
	@UIMessage(description = "小贴士问句集下面的问句链接")@Test
	@Parameters({"xiapo"})
	   public void test_clickxiapo(String xiapo) throws Exception //下破的第一个问句
	   {
		String xiapotext = sel.getText(xiapo);
		System.out.println(xiapotext);
		sel.click(xiapo);
		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);      //切换到新窗口
				}
		Thread.sleep(3000);
	  System.out.println(sel.getTitle());
	  AssertTrue(driver, sel.getTitle().contains(xiapotext),"点击下破的第一个问句没有跳转到相应的页面");
	   } 
	
	@UIMessage(description = "问句股价小于5结果,点击股票简称有走势图出现")@Test
	@Parameters({"firststock"})
	   public void test_stockInfo_con(String firststock) throws Exception //下破的第一个问句
	   {
		ut.QueryResult(sel, "股价小于5");
		Thread.sleep(3000);
		sel.mouseOver(firststock);
		//System.out.println(sel.getAttribute("//*[@id='stockInfo_con']@display"));
        AssertEquals(driver, sel.getAttribute("//*[@id='offtblbdy']/tr[1]@class"), "odd_row  cur_tr","鼠标移动股票简称后没有出现走势图");
	   }
	
	@UIMessage(description = "问句股价小于5结果,点击股票简称有走势图出现的UIlocation")@Test
	@Parameters({"stockInfo_con","stockInfo_con_top","stockInfo_con_left"})
	   public void test_stockInfo_con_UIlocation(String stockInfo_con, String stockInfo_con_top,String stockInfo_con_left)throws Exception{
		   System.out.println(" test_stockInfo_con_UIlocation");
		  boolean flag=ut.getELementposition(sel, stockInfo_con,stockInfo_con_top, stockInfo_con_left);
		  AssertEquals(driver,flag,true,"股票简称走势图位置不对");
	}
	 

	
	   
	@BeforeTest
	// 测试开始之前要执行这个
	@Parameters({"openUrl_info_1","selenium.host", "selenium.port", "selenium.browser","selenium.url" })
	public void beforeTest(String Url,String host, int port, String browser, String url)
	throws MalformedURLException {
		System.out.println("Before test");
		System.out.println(host);
        //Integer Dport = Integer.parseInt(port);
        //sel = new DefaultSelenium(host, Dport, browser, url);
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
		sel.open(Url);
		driver.manage().window().maximize();
	}

	@AfterTest
	// 每个测试结束后运行这个
	public void afterTest() {
		System.out.println("After test");
		sel.stop();
	}
}
