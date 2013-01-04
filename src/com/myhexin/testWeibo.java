package com.myhexin;


import org.openqa.selenium.WebDriver;
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

/**
 * @author Administrator
 *
 */
public class testWeibo {
	public static final String TIMEOUT = "30000";
	private WebDriverBackedSeleniumWrapper sel;
	private util ut; 
	private static WebDriver driver;
	
	
    /**
     * @param openUrl 搜牛首页
     * @param weibo 微博按钮
     * @param jigou 机构标签栏
     * @param jigouarea  机构中的内容
     * @throws InterruptedException 
     */
       @UIMessage(description = "")@Test
       @Parameters ({ "openUrl","weibo","jigou","jigoucount"
	  	  })
	     public void test_jigou(String openUrl,String weibo,String jigou,String jigoucount) throws InterruptedException {
	       System.out.println("test_jigou"); 
	       sel.open(openUrl); 
	       sel.click(weibo);
	       Thread.sleep(3000);
	  	   sel.click(jigou);
	  	   System.out.println("点击机构页面跳转成功！");
	  	   Number countj = sel.getXpathCount(jigoucount);
 	       System.out.println("机构微博一共有信息："+countj);
 	       assertEquals(countj,30);
         
       }
       
       /**
        * @param jipancount 解盘一个框显示的条数统计
        */
       @UIMessage(description = "")@Test
       @Parameters ({"jiepan", "jiepancount"
   	  	  })
   	     public void test_jipanCount(String jiepan,String jiepancount) {
   	       System.out.println("test_jiepanCount"); 
   	       sel.click(jiepan);
   	       Number count = sel.getXpathCount(jiepancount);
   	       System.out.println("解盘微博一共有信息："+count);
   	       assertEquals(count,30);
       }
  
       /**
        * @param newxpath_source 解盘的微博来源分析xpath
        */
       @UIMessage(description = "")@Test
       @Parameters({"newxpath_source"})
       public void test_sourceJp(String newxpath_source){
       System.out.println("测试解盘微博来源及时间"+"test_sourceJp");
       for (int x = 0; x < 30; x++) {
   			String xpath = String.format(newxpath_source,x);
   			String areatext = sel.getText(xpath);
	  	    System.out.println(areatext);
	  	    

	  	   assertTrue(areatext.contains("来自新浪微博")||areatext.contains("来自腾讯微博"));
   	}
   	System.out.println();
   }
     //*[@id="weibocontent_0"]/dd[1]/p[2]
     //*[@id="weibocontent_1"]/dd[1]/p[2]
       /**
        * @param newxpath_author 解盘的微博作者分析xpath
        */
       @UIMessage(description = "")@Test
       @Parameters({"Author"})
       public void test_authorJp(String newxpath_author){
       Map<Integer, String> Newsmap = new HashMap<Integer, String>();
       System.out.println("测试微博作者信息"+"test_authorJp");
       for (int x = 0; x < 30; x++) {
   			String xpath1 = String.format(newxpath_author,x) ; 
   			String New = sel.getText(xpath1);
   			if (!Newsmap.containsValue(New)) {
   				Newsmap.put(x, New); 
   			}
   			System.out.println(New);
   	}
   	System.out.println();
   } 
       
       /**
        * @param authorxpath  解盘微博每一条微博的作者与标签作者是否相同XPATH
        * @param authorMessage   作者信息
        */
       @UIMessage(description = "")@Test
       @Parameters({"Author","authorMessage"})
       public void test_authorMessage(String authorxpath,String authorMessage){
    	   System.out.println("微博作者测试："+"test_authorMessage");
    	   for(int x=0;x<30;x++){
        	 String xpath_author=String.format(authorxpath,x) ;
        	 String xpath_message=String.format(authorMessage,x);
        	 System.out.print(sel.getText(xpath_author)+"  ");
        	 sel.mouseOver(xpath_author);
        	 System.out.println(sel.getText(xpath_message));
        	 assertEquals(sel.getText(xpath_author),sel.getText(xpath_message));
    	   }
       } 
  
       /**
        * @param author_jipan 解盘微博的按钮
        * @param channel 微博显示内容  作者+ 的微博搜索结果
        * @throws InterruptedException 
        */
        @UIMessage(description = "")@Test
        @Parameters ({ "jiepancount","author_jipan","Author"
      	  })
         public void test_Linkauthor_jipan(String author_count,String author_jipan,String channel_author) throws InterruptedException {
        
           System.out.println("test_Linkauthor_jipan"); 
           sel.click(author_jipan);
           Thread.sleep(3000);
           System.out.println("点击的是"+sel.getText(author_jipan)+"的微博");
           Thread.sleep(3000);
           int  count=(Integer) sel.getXpathCount(author_count);
         
           for(int x=0;x<count;x++){
        	   String xpath_author=String.format(channel_author, x) ;
        	   System.out.println(sel.getText(xpath_author));
        	   assertEquals(sel.getText(xpath_author),sel.getText(author_jipan));
           }
           System.out.println(String.format(sel.getText(author_jipan)+"*"+"%d条微博", count));
    }
        /**
         * @param authorxpath1  鼠标移动到微博博主名上，点击博主名，是否跳转到新浪微博主页
         * @param sinaWeibo_author    新浪微博博主主页
         */
        @UIMessage(description = "")@Test
        @Parameters
        		({"author_jipan","sinaWeibo_author"})
        public void test_weiboLink(String authorxpath1,String sinaWeibo_author ) throws InterruptedException{
     	   System.out.println("判断微博页面跳转"+"test_weiboLink");
     	   sel.mouseOver(authorxpath1);
     	   Thread.sleep(3000);
     	   System.out.println(sel.getText(sinaWeibo_author));
     	   String areatext = sel.getText(sinaWeibo_author);
	  	   assertTrue(areatext.contains("http://weibo.com")||areatext.contains("http://t.qq.com/"));
        } 
        
        /**
         * @param openUrl 搜牛首页
         * @param weibo 微博按钮
         * @param result 输入浦发银行是否有返回结果
         * @throws Exception 
         */
           @UIMessage(description = "")@Test
           @Parameters ({ "result"
    	  	  })
    	     public void test_result(String result) throws Exception {
    	       System.out.println("test_result");       
    	       ut.QueryResult(sel, "浦发银行");
    	       Thread.sleep(3000);
    		   boolean yes = sel.isElementPresent(result);
    		   if(yes)
    			   System.out.println("搜索有结果");
    		   assertEquals(yes, true, "搜索浦发银行没有结果返回");
    		                
           }
         /**
            * @param openUrl 搜牛首页
            * @param weibo 微博按钮
            * @param resultgb 输入浦发银行是否有返回结果
         * @throws Exception 
         */
           @UIMessage(description = "")@Test
           @Parameters ({ "openUrl","resultgb"
    	  	  })
    	     public void test_resultgb(String openUrl,String resultgb) throws Exception {
    	       System.out.println("test_resultgb"); 
    	       ut.QueryResult(sel, "浦发银行");   			
    	       System.out.println(sel.getText(resultgb));
    		   assertEquals(sel.getText(resultgb),"浦发银行");    		              
           }
       /**
        * @param logo 微博LOGO
        * @param zhuyecount 主页栏目数
        * @throws InterruptedException
        */
         @UIMessage(description = "")@Test
         @Parameters({"logo","zhuyecount"})
         public void test_weiboLogo(String logo,String zhuyecount) throws InterruptedException{
       	  	System.out.println("test_weiboLogo");  	
       	  	sel.click(logo);
       	  	Thread.sleep(3000);
       	  	String uRLString=sel.getLocation();
       	  	assertTrue(uRLString.endsWith("channel/weibo/?tid=weibo"), "点击logo没有回到微博首页");
//       	  boolean sj = sel.isElementPresent(zhuyecount); 
//    	  System.out.println(sj);
//    	  assertEquals(true,sj);
         }
       
       
       /**
        * @param login 搜牛微博的登录按钮
        * @throws InterruptedException 
        */
          @UIMessage(description = "")@Test
          @Parameters({"login"})
          public void test_weibologin(String login) throws InterruptedException{
       	   System.out.println("test_weiboloin");
       	   sel.click(login);
       	   Thread.sleep(3000);
       	   System.out.println(sel.getTitle());
       	   assertEquals("同花顺用户登录", sel.getTitle());
          }

	@BeforeTest
    // 测试开始之前要执行这个
    @Parameters({ "selenium.host", "selenium.port", "selenium.browser",
		"selenium.url" })
    public void beforeTest(String host, int port, String browser, String url) throws MalformedURLException {
	System.out.println("Before test");
	System.out.println(host);
	DesiredCapabilities capability = DesiredCapabilities.firefox();
    driver = new RemoteWebDriver(new URL("http://172.20.23.95:4444/wd/hub"), capability);
    sel = new WebDriverBackedSeleniumWrapper(driver,url);
    ut=new util();
    driver.manage().window().maximize();
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