package com.myhexin;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import com.myhexin.common.util;

public class TestReport {
	private Selenium sel;
	private String TIMEOUT = "30000";
	
  @Test
  @Parameters({"openUrl", "queryInputId", "searchbtn" ,"query","firstRlt"})
  public void testQuery(String url,String queryInputId,String searchbtn, String query, String firstRlt ) {
	  sel.open(url);
	  sel.type(queryInputId,query);
	  sel.click(searchbtn);
	  sel.waitForPageToLoad(TIMEOUT);
	  String rlt = sel.getText(firstRlt);

	  System.out.println(rlt);
	  int index = rlt.indexOf(query);
	  System.out.println(index);
	  assertTrue(index>=0);
  }
  
  @Test
  @Parameters({"openUrl"})
  public void testFengyun(String url){
	  System.out.println(url);
	  sel.open("search?preParams=&ts=1&f=1&qs=1&tid=blog&w=浦发银行");
	  util ut = new util();
	  //String tab = ut.getHLlabelonLeft(sel);
	  System.out.println(ut.getRltText2(sel,"2"));
  }
  
  @BeforeTest
  @Parameters({ "selenium.host", "selenium.port", "selenium.browser", "selenium.url"}) 
  public void beforeTest(String host, int port, String browser, String url) {
	  
	  sel = new DefaultSelenium(host,port,browser,url);
	  sel.start();
	  sel.setTimeout(TIMEOUT);
  }

  @AfterTest
  public void afterTest() {
	  sel.stop();
  }

}
