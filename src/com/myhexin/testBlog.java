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
import com.thoughtworks.selenium.DefaultSelenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import com.myhexin.common.*;
import static com.myhexin.common.Assert.*;

/**
 * @author Administrator
 *
 */
public class testBlog {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut;
	private static WebDriver driver;
	
    
    /**
     * @param openUrl 搜牛首页
     * @param blog 博客按钮
     * @param wdgz 我的关注标签栏
     * @param blogarea 我的关注  中的内容
     * @throws InterruptedException 
     */

       @UIMessage(description = "")@Test
       @Parameters ({ "openUrl","blog","wdgz","blogarea"
	  	  })
	     public void test_WoDeGuanZhu(String openUrl,String blog,String wdgz,String blogarea) throws InterruptedException {
	       System.out.println("test_WoDeGuanZhu"); 
	       sel.open(openUrl); 
	       driver.manage().window().maximize();
	       //sel.windowMaximize();
	       sel.click(blog);
	       Thread.sleep(3000);
	  	   sel.click(wdgz);
	  	   String areatext = sel.getText(blogarea);
	  	   System.out.println(areatext);
	  	   AssertTrue(driver,areatext.contains("搜牛推荐："));
         
       } 

    /**
     * @param mrmbcount 市场信息一个框显示的条数统计
     */
    @UIMessage(description = "市场信息一个框")@Test
    @Parameters ({ "scxxcount"
	  	  })
	     public void test_ScxxCount(String scxxcount) {
	       System.out.println("test_ScxxCount"); 
	       Number count = sel.getXpathCount(scxxcount);
	       System.out.println(count);
	       AssertEquals(driver, count, 7);
    }
    
    
    /**
     * @param newxpath 经济杂谈的标题xpath
     */
    @UIMessage(description = "")@Test
    @Parameters({"newxpath"})
    public void test_TitleDe(String newxpath){
    Map<Integer, String> Newsmap = new HashMap<Integer, String>();
	System.out.println("test_TitleDe");
	for (int x = 1; x < 2; x++) {
		String xpath = newxpath + "/li" + "[" + x + "]" + "/a";
		
		String New = sel.getText(xpath);
		if (!Newsmap.containsValue(New)) {
			Newsmap.put(x, New);
		}
		System.out.println(New);
	}
}
    
    /**
     * @param authorxpath  市场信息每一条标题的作者XPATH
     * @param jiaguanzhu   加关注ID guanzhu23301
     */
    @UIMessage(description = "")@Test
    @Parameters({"authorxpath","jiaguanzhu"})
    public void test_JiaGuanZhu(String authorxpath,String jiaguanzhu){
      System.out.println("test_JiaGuanZhu");	
      System.out.println(sel.getText(authorxpath));
      sel.mouseOver(authorxpath);
      boolean bl = sel.isVisible(jiaguanzhu);
      AssertTrue(driver, bl);
    
     
    }
    
    
    /**
     * @param gendou 名人名博的更多按钮
     * @param channel 名人名博频道显示内容  博客首页>所有圈子> 名人名博
     * @throws InterruptedException 
     */
     @UIMessage(description = "")@Test
     @Parameters ({ "gendou","channel"
   	  })
      public void test_LinkGenduo(String gendou,String channel) throws InterruptedException {
     
        System.out.println("test_LinkGenduo"); 
        sel.click(gendou);
        Thread.sleep(3000);
        String channelname = sel.getText(channel);
        System.out.println(channelname);
        AssertTrue(driver,channelname.contains("名人名博"));
        
 }
     /**
      * @param allcount 名人名博更多点击后,显示的条数统计
      */
     @UIMessage(description = "")@Test
     @Parameters ({ "allcount"})
 	     public void test_Count(String allcount) {
 	       System.out.println("test_Count"); 
 	       Number count = sel.getXpathCount(allcount);
 	       System.out.println(count);
 	      AssertEquals(driver,count,10);
     }  
     
     /**
      * @param syqz 所有圈子
      * @param alllink 展开后的所有圈子的XPATH
      */
     @UIMessage(description = "")@Test
     @Parameters ({ "syqz","alllink"})
 	     public void test_Syqz(String syqz,String alllink) {
 	       System.out.println("test_Syqz"); 
 	       sel.click(syqz);
 	       String link = sel.getText(alllink);
 	       System.out.println(link);
 	       AssertTrue(driver,link.contains("名人名博"));
     } 
     
    /**
     * @param syqz (博客首页>所有圈子> 名人名博)所有圈子
     * @param alllink 所有圈子的下选框
     */
    @UIMessage(description = "")@Test
    @Parameters({"syqz","alllink"})
    public void test_CloseSyqz(String syqz,String alllink){
    	System.out.println("test_CloseSyqz");
    	sel.click(syqz);
    	boolean qz = sel.isVisible(alllink);
    	System.out.println(qz);
    	AssertTrue(driver,!qz);
    }
    
   /**
 * @param bksy (博客首页>所有圈子> 名人名博)中的博客首页
 * @param jctj 精彩推荐
 * @throws InterruptedException
 */
@UIMessage(description = "")@Test  
   @Parameters({"bksy","jctj"})
   public void test_ClickBksy(String bksy,String jctj) throws InterruptedException{
	   System.out.println("test_ClickBksy");
	   sel.click(bksy);
	   Thread.sleep(3000);
	   boolean sj = sel.isElementPresent(jctj); //判断有没有精彩推荐这个元素在
	   System.out.println(sj);
	 
	   AssertEquals(driver, sj,true);
   }
   
   
   

  /**
 * @param tjbkxpath 推荐 博客
 * @throws InterruptedException 
 */
@UIMessage(description = "")@Test
  @Parameters({"tjbkxpath"})
  public void test_TuiJianBlog(String tjbkxpath) throws InterruptedException{
	  System.out.println("test_TuiJianBlog");
	  Thread.sleep(3000);
	  sel.type("//*[@id='auto']","浦发银行");
	  Thread.sleep(3000);
	  System.out.println(sel.getLocation());
	  
	  sel.click("//html/body/div[2]/div/div/div/form/input[10]");
	  
	  Thread.sleep(3000);
	  Map<Integer, String> Newsmap = new HashMap<Integer, String>();
		System.out.println("test_TuiJianBlog");
		for (int x = 1; x < 20; x= x + 2) {
			String xpath = tjbkxpath + "/li" + "[" + x + "]" ;   
			String New = sel.getText(xpath);
			if (!Newsmap.containsValue(New)) {
				Newsmap.put(x, New);
			}
			System.out.println(New);  
		}
  }  
  
   /**
 * @param baotiradio 标题按钮
 * @throws InterruptedException
 */
   @UIMessage(description = "")@Test
   @Parameters({"biaotiradio","suoyou"})
   public void test_BiaoTiRadio(String biaotiradio,String suoyou ) throws InterruptedException{
	   System.out.println("test_BiaoTiRadio");
	   sel.click(biaotiradio); //选 中标题按钮
	   sel.click("//html/body/div[2]/div/div/div/form/input[10]"); //按搜索铵钮
	   
	   Thread.sleep(3000);
	   //boolean bt =  sel.isElementPresent(suoyou); //判断是否有"所有圈子"这个元素ID //*[@id='ntb_1']
	   //System.out.println(bt);
	   
	   //AssertEquals(driver, bt,true);
   }
    
   /**
 * @param blognameradio 博主名按钮
 * @throws InterruptedException
 */
   @UIMessage(description = "")@Test
   @Parameters({"blognameradio","result"})
   public void test_BlogNameRadio(String blognameradio,String result) throws InterruptedException{
	  System.out.println("test_BlogNameRadio"); 
	  sel.click(blognameradio); //点击博主名
	  sel.click("//*[@id='searchform']/input[10]"); //点击搜索铵钮
	  Thread.sleep(3000);
	  String nors = sel.getText(result); //返回的搜索内容
	  System.out.println(nors);
	  AssertTrue(driver,nors.contains("抱歉")); //判断是否包含 抱歉两字
	  
   }
 
    /**
     * @param from 来自
     * @throws Exception 
     */
    @UIMessage(description = "")@Test
    @Parameters({"from"})
    public void test_BlogNameSearch(String from) throws Exception{
    	System.out.println("test_BlogNameSearch");
    	ut.QueryResult(sel, "老科的投资");
    	String blogname = sel.getText(from);
    	System.out.println(blogname);
    	AssertEquals(driver,blogname,"老科的投资");
    }
  
  
   @UIMessage(description = "")@Test
   @Parameters({"biaoqian","result"})
   public void test_BiaoQianRadio(String biaoqian,String result) throws InterruptedException{
	   System.out.println("test_BiaoQianRadio");
	   sel.click(biaoqian);
	   sel.click("//*[@id='searchform']/input[10]");
	   Thread.sleep(3000);
	   String nors = sel.getText(result);
	   System.out.println(nors);
	   AssertTrue(driver,nors.contains("抱歉"));
	   
   }
   
   @UIMessage(description = "")@Test
   @Parameters({"suoyou"})
   public void test_BiaoQianSearch(String suoyou) throws Exception{
	   System.out.println("test_BiaoQianSearch");
	   ut.QueryResult(sel, "银行"); 
	   boolean bt = sel.isElementPresent(suoyou); //判断是否含有"所有圈子"这个元素ID //*[@id='ntb_1']
	   System.out.println(bt);
	   AssertEquals(driver, bt,true);
   }

  /**
 * @param jrbksy 进入博客首页>> 按扭
 * @param jctj 精彩推荐 
 * @throws InterruptedException
 */
@UIMessage(description = "")@Test
  @Parameters({"tjrbksyxpath","jctj"})
  public void test_jrbksy(String tjrbksyxpath,String jctj) throws InterruptedException{
	 System.out.println("test_jrbksy");
	 sel.click(tjrbksyxpath);
	 Thread.sleep(3000);
	 boolean sj = sel.isElementPresent(jctj); //判断有没有精彩推荐这个元素在
	 System.out.println(sj);
	 AssertEquals(driver,true,sj);
	 
  }

  /**
 * @param logo 博客LOGO
 * @param jctj 精彩推荐
 * @throws InterruptedException
 */
@UIMessage(description = "")@Test
  @Parameters({"logo","jctj"})
  public void test_BlogLogo(String logo,String jctj) throws InterruptedException{
	  System.out.println("test_BlogLogo");
	  sel.goBack();
	  Thread.sleep(3000);
	  sel.click(logo);
	  Thread.sleep(3000);
	  boolean sj = sel.isElementPresent(jctj); //判断有没有精彩推荐这个元素在
	  System.out.println(sj);
	  AssertEquals(driver,true,sj);
  }
  
  
  
   /**
 * @param loin 搜牛博客的登录按钮
 * @throws InterruptedException 
 */
   @UIMessage(description = "")@Test
   @Parameters({"loin"})
   public void test_Blogloin(String loin) throws InterruptedException{
	   System.out.println("test_Blogloin");
	   sel.click(loin);
	   Thread.sleep(3000);
	   System.out.println(sel.getTitle());
	   AssertEquals(driver,"同花顺用户登录", sel.getTitle());
   }

  
 
    

	@BeforeTest
    // 测试开始之前要执行这个
    @Parameters({ "selenium.host", "selenium.port", "selenium.browser",
		"selenium.url" })
    public void beforeTest(String host, int port, String browser, String url) throws MalformedURLException {
	System.out.println("Before test");
	System.out.println(host);
    //startSeleniumSession("172.20.23.95", 4444, "*firefox",
	//"http://search.10jqka.com.cn/");
//	sel = new DefaultSelenium(host, port, browser, url);
//	sel.start();
	DesiredCapabilities capability = DesiredCapabilities.firefox();
	driver = new RemoteWebDriver(new URL("http://172.20.23.95:4444/wd/hub"), capability);
    sel = new WebDriverBackedSeleniumWrapper(driver,url);
    ut=new util();
	//sel.start();
	sel.useXpathLibrary("javascript-xpath");
	sel.setTimeout(TIMEOUT);
	sel.useXpathLibrary("javascript-xpath");
	sel.setTimeout(TIMEOUT);
	ut=new util();
    }













	@AfterTest
    // 每个测试结束后运行这个
    public void afterTest() {
	System.out.println("After test");
	sel.stop();
    }

    }