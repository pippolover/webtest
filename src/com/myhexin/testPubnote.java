package com.myhexin;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;





<<<<<<< .mine
public class NewsTest{
  public static final String TIMEOUT = "30000"; 
  
private Selenium sel; 
=======
	/**
	 * @param linkxpath
	 * @param titlexpath2
	 * @param keywordquery
	 * @throws Exception 
	 */
	@UIMessage(description = "")@Test
	@Parameters({ "link.xpath", "title.text2", "keywordquery" })
	public void testClinkZhaiQuan(String linkxpath, String titlexpath2,
			String keywordquery) throws Exception {
		System.out.println("testing ClinkZhaiQuan");
		sel.click(linkxpath);
		sel.waitForPageToLoad(TIMEOUT);
		ut.QueryResult(sel, "600000 债券");
		Thread.sleep(30000);
		String title_text2 = sel.getText(titlexpath2);
		System.out.println(sel.getTitle());
		System.out.println(title_text2);
		AssertTrue(driver,title_text2.contains(keywordquery));
>>>>>>> .r46346

     @Test
     @Parameters ({ "openUrl", "queryInputId", "query", "searchbtn", "xpath1","query2"
  	  })
     public void QuerySearch(String openUrl, String inputId,  String query, String searchbtn, String xpath1,
  		                  String query2 ) {
  	   System.out.println("testing QuerySearch");
  	   sel.open(openUrl);
  	   sel.type(inputId, query);
  	   sel.click(searchbtn);
  	   sel.waitForPageToLoad(TIMEOUT);
  	   String title_text = sel.getText(xpath1);
  	   System.out.println(sel.getTitle());
  	   System.out.println(title_text);
  	   assertTrue(title_text.contains(query2));
      
    }
    

    
    
    
    @Test
    @Parameters ({ "link.xpath","title.text2","keywordquery"
  	 })
    public void  ClinkZhaiQuan(String linkxpath,String titlexpath2,String keywordquery	) {
      System.out.println("testing ClinkZhaiQuan");
      sel.click(linkxpath);
      sel.waitForPageToLoad(TIMEOUT);
  	  String title_text2 = sel.getText(titlexpath2);
  	  System.out.println(sel.getTitle());
  	  System.out.println(title_text2);		  	  
  	  assertTrue(title_text2.contains(keywordquery));

    } 
    

	
    
    @Test
    @Parameters ({ "openUrl", "queryInputId", "query3", "searchbtn", "linkxpath1","title.text3","keywordquery1"
  	 })
    public void QuerySearch2(String openUrl, String inputId,  String query3, String searchbtn, String linkxpath1,String titlexpath3,
  		                  String keywordquery1 ) {
  	  System.out.println("testing QuerySearch2");
  	  sel.open(openUrl);
  	  sel.type(inputId, query3);
  	  sel.click(searchbtn);
  	  sel.waitForPageToLoad(TIMEOUT);
  	  sel.click(linkxpath1);
  	  sel.waitForPageToLoad(TIMEOUT);
  	  System.out.println(sel.getTitle());
  	  String title_text3 = sel.getText(titlexpath3);
  	  
  	  System.out.println(title_text3);
  	  assertTrue(title_text3.contains(keywordquery1));
  	  
  	  }
    
    @Test
    @Parameters ({ "link.xpath2","title.text4","keywordquery"
  	 })
    public void ClinkGuangGu(String linkxpath2,String titlexpath4,String keywordquery	) {
      System.out.println("testing ClinkGuangGu");
      sel.click(linkxpath2);
      sel.waitForPageToLoad(TIMEOUT);
  	  String title_text2 = sel.getText(titlexpath4);
  	  System.out.println(sel.getTitle());
  	  System.out.println(title_text2);		  	  
  	  assertTrue(title_text2.contains(keywordquery));
    }
    
    @Test
    @Parameters ({  "openUr2","queryInputId2", "query1", "searchbtn2","link.xpath3", "xpath1","query2"
  	 })
    public void QuerySearch3( String openUr2,String inputId2,  String query1, String searchbtn2,String linkxpath3, String xpath1,
  		                  String query2 ) {
  	  System.out.println("testing QuerySearch3");
  	  sel.open(openUr2);
      sel.type(inputId2, query1);
  	  sel.click(searchbtn2);
  	  sel.waitForPageToLoad(TIMEOUT);
  	  sel.click(linkxpath3);
  	  String title_text = sel.getText(xpath1);
  	  System.out.println(sel.getTitle());
  	  System.out.println(title_text);
  	  assertTrue(title_text.contains(query2));
      
    }
    
    @Test
    @Parameters ({ "openUrl", "queryInputId", "query4", "searchbtn", "nextpage.xpath","title.text5","query5"
  	 })
    public void NextPage(String openUrl, String inputId,  String query4, String searchbtn, String nextpage,String titlexpath5,
  		                  String query5 ) {
  	  System.out.println("testing NextPage");
  	  sel.open(openUrl);
  	  sel.type(inputId, query4);
  	  sel.click(searchbtn);
  	  sel.waitForPageToLoad(TIMEOUT);
      sel.click(nextpage);
      sel.waitForPageToLoad(TIMEOUT);
      String title_text5 = sel.getText(titlexpath5);
  	  //System.out.println(sel.getTitle());
  	  System.out.println(title_text5);
  	  assertTrue(title_text5.contains(query5));     
    
    }
    
    @Test
    @Parameters ({  "return.main","query6"})
  	 
    public void ReturnToHome ( String return_main,  String query6
  		                   ) {
  	  System.out.println("testing ReturnToHome");
      sel.click(return_main);
      sel.waitForPageToLoad(TIMEOUT);
      System.out.println(sel.getTitle());
      assertEquals(sel.getTitle(),query6);
     
    }
    @BeforeTest
    @Parameters ({ "selenium.host", "selenium.port", "selenium.browser", "selenium.url"})
    public void beforeTest(String host, String port, String browser, String url) {
        System.out.println("Before test"); 
  	    System.out.println(host);
  	    Integer Dport = Integer.parseInt(port);
  	    //sel = new DefaultSelenium("172.20.23.95", 4444, "*firefox", "http://search.10jqka.com.cn/");
  	    sel = new DefaultSelenium (host, Dport, browser, url);
  	    sel.start();
        sel.setTimeout(TIMEOUT);
        
    }

    @AfterTest
    public void afterTest() {
  	    System.out.println("After test");
        sel.stop(); 
    }

}