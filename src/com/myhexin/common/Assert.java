/**
 * 
 */
package com.myhexin.common;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author Administrator
 *
 */
public class Assert {
	protected Assert() {
	    // hide constructor
	  }
	public static void screenShot(WebDriver driver,String dir_name)
	{
		
		  if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
		     new File(dir_name).mkdir();  // 如果不存在则新建一个目录
		  }
		 
		  String methodnameString=Thread.currentThread().getStackTrace()[3].getMethodName();
		 WebDriver augmentedDriver = new Augmenter().augment(driver);
		  try {
			 File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE); // 关键代码，执行屏幕截图，默认会把截图保存到temp目录
		     FileUtils.copyFile(screenshot, new File(dir_name + File.separator +methodnameString+ ".png"));  // 这里将截图另存到我们需要保存的目录，例如screenshot\20120406-165210.png
		  } catch (IOException e) {
		     e.printStackTrace();
		  }
		  /*
		   *  DesiredCapabilities capability = DesiredCapabilities.firefox();//
				driver = new RemoteWebDriver(new URL("http://172.20.23.95:4444/wd/hub"), capability);
				Selenium selenium = new WebDriverBackedSelenium(driver,"http://search.10jqka.com.cn/");
		   */
		  
	
	}
	static public void AssertEquals(WebDriver driver,Object expected,Object actual,String  message)
	{
		try
		{	
			assertEquals(expected,actual,message);
		}
		catch (AssertionError e) {
			screenShot(driver,"./pic");
			TakeMethon takeMethon=new TakeMethon(e.getMessage());
            takeMethon.TakeMethonWrapper_assert();
		}
	}
	static public void AssertEquals(WebDriver driver,String expected,String actual,String  message)
	{
		AssertEquals(driver, (Object)expected, (Object)actual, message);
		
	}
	static  public void AssertEquals(WebDriver driver,int expected,int  actual,String  message)
	{
		AssertEquals(driver, (Object)expected, (Object)actual, message);
		
	}
	static public void AssertEquals(WebDriver driver,String  expected,String  actual)
		{
			AssertEquals(driver, (Object)expected, (Object)actual,null);
			
		}
	static public void AssertEquals(WebDriver driver,int  expected,int  actual)
		{
			AssertEquals(driver, (Object)expected, (Object)actual,null);
			
		}
	static public void AssertEquals(WebDriver driver,Number  expected,int  actual)
		{
			AssertEquals(driver, (Object)expected, (Object)actual,null);
			
		}
	static public void AssertEquals(WebDriver driver,Boolean  expected,Boolean  actual)
		{
			AssertEquals(driver, (Object)expected, (Object)actual,null);
			
		}
	static public void AssertTrue(WebDriver driver,Boolean condition,String  message)
	{
		try
		{	
			assertTrue(condition,message);
		}
		catch (AssertionError e) {
			screenShot(driver,"./pic");
			TakeMethon takeMethon=new TakeMethon(e.getMessage());
            takeMethon.TakeMethonWrapper_assert();
		}
	}
	static public void AssertTrue(WebDriver driver, Boolean condition)
	{
		try
		{	
			assertTrue(condition);
		}
		catch (AssertionError e) {
			screenShot(driver,"./pic");
			TakeMethon takeMethon=new TakeMethon(e.getMessage());
            takeMethon.TakeMethonWrapper_assert();

		}
		catch (NullPointerException e) {
            System.out.println("老纸在这");
        }
		
	}
}
