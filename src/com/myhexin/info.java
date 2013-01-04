package MyInfo;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.DefaultSelenium;
import java.util.*;

public class info {
  public static final String  TIMEOUT="30000";
  private Selenium sel;
  public ArrayList<ArrayList<String>> lYidongvalue =new ArrayList<>();//用来存放四个异动数据栏
  public ArrayList<String> lYeAFvalue =new ArrayList<>();//用来存放昨天下午的异动数据
  public ArrayList<String> lYeNIvalue =new ArrayList<>();//用来存放昨天晚上的异动数据
  public ArrayList<String> lToMovalue =new ArrayList<>();//用来存放今天早上的异动数据
  public ArrayList<String> lToNewvalue =new ArrayList<>();//用来存放今天最新的异动数据
  
  @Test
  @Parameters({"selenium.infoUrl","sYestAfter.locate","sYestNight.locate","sTodaMorni.locate"
	  ,"sTodaNew.locate"})
  public void testYidongInfo(String sOpenUrl,String sYeAF,String sYeNI,String sToMo,String sToNew) {
	  sel.open(sOpenUrl);
	  Number c =sel.getXpathCount("//html/body/div[2]/div[3]/div/div[2]/div/table/tbody");
	  int count =c.intValue();
	  System.out.println(count);
	  for(int x=1;x<6;x++)
	  	{
		 String xpath1=sYeAF+"tr"+"["+x+"]"+"/td[3]";
	  	 String xpath2=sYeNI+"tr"+"["+x+"]"+"/td[3]";
	  	 String xpath3=sToMo+"tr"+"["+x+"]"+"/td[3]";
	  	 String xpath4=sToNew+"tr"+"["+x+"]"+"/td[3]";
	  	 System.out.println(xpath1);
	  	 
	  	 try{
	  		 String YeAFvalue=sel.getText(xpath1);
	  		 lYeAFvalue.add(YeAFvalue);
	  		 String YeNIvalue=sel.getText(xpath2);
	  		 lYeNIvalue.add(YeNIvalue);
	  		 String ToMovalue=sel.getText(xpath3);
	  		 lToMovalue.add(ToMovalue);
	  		 String ToNewvalue=sel.getText(xpath4);
	  		 lToNewvalue.add(ToNewvalue);
	  		 }
	  	 catch (Exception e){
	  		
		    }
	  	}
	   
	   //把几个异动列表添加一个大的列表里面
	   lYidongvalue.add(lYeAFvalue);
	   lYidongvalue.add(lYeNIvalue);
	   lYidongvalue.add(lToMovalue);
	   lYidongvalue.add(lToNewvalue);
	   //assertEquals(i, 5);
 
	  
}
     
   @Test
  public void testAbnormalNews() {
		  //获取最新时间和系统时间进行对比
	      System.out.println("testAbnormalNews");
		  Calendar cal = Calendar.getInstance();
		  Date time = cal.getTime();
		  System.out.println(time);
		  
		  Integer m = cal.get(Calendar.MONTH) + 1;
		  Integer d = cal.get(Calendar.DAY_OF_MONTH);
		  Integer h = cal.get(Calendar.HOUR_OF_DAY);

		  //System.out.println(sel.getHtmlSource());
		  sel.click("//html/body/div[2]/div[3]/div/div/ul/li[4]/span[2]");
		  //sel.click("//*li[@subtitle='最新']/span[2]");
		  String expectUpdateTime = m.toString() + "月" + d.toString() + "日 " + h.toString() + "点";
		  String updateTime = sel.getText("//*[@id='introWord']");
		  System.out.println(expectUpdateTime);
		  System.out.println(updateTime);
		  assert(updateTime.contains(expectUpdateTime));//用来判断网页上显示更新时间是否和系统时间一致

	  }
	
   
   
   
    @Test
    @Parameters({"sYidongTitle.locate","ALLlook.locate","Jumptitle.locate","FirstStock.locate","Jumpvalue.locate"})
   public void testAllRank(String Yidong,String ALLlook,String Jumptitlexpath,String firststock,String jumpvaluexpath)
   { 	
    //查看全部排行，因为查看全部的时候网页会新打开一个，所以要特殊处理

    System.out.println("testAllRank");   	 
    for(int x=0;x<5;x++)
    	{	
    		//String xpath=newxpath+"li"+"["+x+"]"+"/a";
    		String xpath="";
    		String yidongtitle="";//首页异动title，用来和跳转的网页进行对比
    		String jumptitle="";//跳转后页面的异动title
    		
    		//xpath异动新闻栏title，这里要多进行一次x=1的操作是因为selenium取弹出网页的时候第一次不会跳转到弹出网页
    		if (x==0)    			
    			{
    			xpath=Yidong+"li"+"["+1+"]"+"/span[2]";
    			}
    		else {
    			xpath=Yidong+"li"+"["+x+"]"+"/span[2]";
    			yidongtitle=sel.getText(xpath);    				 
				}
    		
    		try{
    			sel.click(xpath);
    			sel.click(ALLlook);
    			//选择弹出的网页
    			sel.selectWindow(sel.getAllWindowTitles()[1]);   	
    			if (x>=1)   			
    				{ 
    				ArrayList<String> lJumpvalue =new ArrayList<>();
    				
    				//用来获取每个异动资讯下面前五条记录数
    				for(int j=1;j<6;j++)
    				{   try{
    					String jumpxpath=jumpvaluexpath+x+"]/table/tbody/tr"+"["+j+"]"+"/td[3]";    			  
    					lJumpvalue.add(sel.getText(jumpxpath));
    					}
    					catch (Exception e) {
							// TODO: handle exception
						}
    				}
    				
    				//用来比较首页异动资讯记录和全部查看里的是否一致
    				for (int j=0;j<lYeAFvalue.size();j++)
    					{
    					System.out.printf("期望值:%s,\t实际值：%s\n",lYidongvalue.get(x-1).get(j),lJumpvalue.get(j));
    					assertEquals(lYidongvalue.get(x-1).get(j), lJumpvalue.get(j));    				
    					}
    				System.out.println("下一个：");
    				
    				//用来比较原来网页和新打开网页异动title是否一致 
    				String xpath3=Jumptitlexpath+"li"+"["+x+"]"+"/span[2]";
    				jumptitle=sel.getText(xpath3);
    				assertEquals(yidongtitle, jumptitle);  			
    				}
    			
    				//重新选择最初的信息页面
    				sel.selectWindow(sel.getAllWindowTitles()[0]);   	
    				String firS=sel.getAllWindowTitles()[0];
    				assertEquals("搜牛财经搜索", firS);//用来判断是否跳转到首页
    				
    	
    				}
    		catch (Exception e) {
    			// TODO: handle exception
    			System.out.println(e);
    			}
    }
       
	   //assertEquals(4,4);
   }
   
   
    
    @Test
    @Parameters({"sNew.locate"})
    public void testNewsRank(String newxpath)
    {   //今日新闻排行
    	Map<Integer, String>Newsmap=new HashMap<>();
    	System.out.println("testNewsRank");
    	for(int x=1;x<6;x++)
    	{
    	 String xpath=newxpath+"li"+"["+x+"]"+"/a";
    	 String New=sel.getText(xpath);
    	 if(!Newsmap.containsValue(New))
    	 		{
    		 		Newsmap.put(x, New);
    	 		}
    	 System.out.println(New);
    	}
    	assertEquals(5, Newsmap.size());//用来判断新闻条数是否是五条
    	
    }
    
    
    @Test
    @Parameters({"sInput.locate","sInputValue_1","sSearchButton.locate","sActualValue.locate",
    	"sExpectValue.locate","sStockhref.locate"})
    public void testQueryResult(String sInput,String sInputValue_1,String sSearchButton,String avlocate,
    		String expectvalue,String Skhref )
    {
      //测试Query结果是否正确
  	  sel.type(sInput, sInputValue_1);
  	  sel.click(sSearchButton);
  	  sel.waitForPageToLoad(TIMEOUT);
  	  String Actualvalue=sel.getText(avlocate);
  	  System.out.println(Actualvalue);
  	  String Stockhref = sel.getAttribute(Skhref);
  	  System.out.println(Stockhref);
  	  System.out.printf("实际值:%s,\t期望值：%s\n", Actualvalue,expectvalue);
  	  assertEquals(Actualvalue, expectvalue);	
    }
    
  @BeforeTest//测试开始之前要执行这个
  @Parameters ({ "selenium.host", "selenium.port", "selenium.browser", "selenium.url"})
  public void beforeTest(String host, int port, String browser, String url) {
  	  System.out.println("Before test"); 
	  System.out.println(host);
	  //startSeleniumSession("172.20.23.95", 4444, "*firefox", "http://search.10jqka.com.cn/"); 
	  sel = new DefaultSelenium(host, port, browser, url);
	  sel.start();
      sel.setTimeout(TIMEOUT);     
  }

  @AfterTest//每个测试结束后运行这个
  public void afterTest() {
	  System.out.println("After test");
      sel.stop(); 
  }
  
}
