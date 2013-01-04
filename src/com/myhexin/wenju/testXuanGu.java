package com.myhexin.wenju;

import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.bcel.verifier.statics.Pass1Verifier;
import org.apache.xerces.impl.io.UTF8Reader;
import org.apache.xpath.operations.Equals;
import org.openqa.jetty.util.UrlEncoded;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.seleniumemulation.FireEvent;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.webbitserver.helpers.UTF8Output;
import org.yaml.snakeyaml.util.UriEncoder;

import static com.myhexin.common.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.myhexin.common.UIMessage;
import com.myhexin.common.WebDriverBackedSeleniumWrapper;
import com.myhexin.common.util;
import com.thoughtworks.selenium.Selenium;

public class testXuanGu {
	public static final String TIMEOUT = "30000";
	private static final File file = null;
	private static  WebDriverBackedSeleniumWrapper sel;
	private static WebDriver driver;
	private static  util ut;
	
	
	@Test
	@Parameters({ "url","txt"})
    public static void testwenjuhuigui(String url,String txt) throws Exception {         
	        File file = new File(txt); 
	        String[] strs = file2StringArray(file); 
	        System.out.println(Arrays.toString(strs));
	        System.out.println(strs.length); 
	        
	        //sel.click("//*[@id='user_guide']/div[1]/span[1]");
	        for(int i = 0;i<strs.length;i++) {
	        String openurl = url + UriEncoder.encode(strs[i].trim()) + "&queryarea=all";
	        System.out.println(openurl);
	        sel.open(openurl);
	        System.out.println(sel.getLocation());
	        driver.manage().window().maximize();
	        
	        try {
				sel.click("//html/body/div[1]/div[4]/div[18]");
			} catch (Exception e) {
				break;
			}
			Thread.sleep(3000);
			String classvalue =(sel.getAttribute("//*[@id='qinfo']@class"));
			System.out.println(classvalue);
			if (classvalue!=null) {
				if (classvalue.equals("question_info closed_qinfo")){
					sel.click("//*[@id='qusetion_remind']/span[3]");
				 } 
			}
			
		    Thread.sleep(4000);
		     ut.screenShot(driver, i);

	        }
	    } 
	      


	
	private static String[] file2StringArray(File file ) { 
	        BufferedReader br = null; 
	        List<String> list = new ArrayList<String>(); 
	        try { 
	            br = new BufferedReader(new FileReader(file)); 
	            String str = null;             
	              while ( (str = br.readLine()) != null) { 
	            	
	                   list.add(str);
	            } 
	            br.close(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        }         
	        return list.toArray(new String[0]);         
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
	

