package com.myhexin.common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import com.thoughtworks.selenium.*;
import com.myhexin.common.TakeMethon;


public class WebDriverBackedSeleniumWrapper extends WebDriverBackedSelenium{
	protected CommandProcessor commandProcessor;
//	public WebDriverBackedSeleniumWrapper(Supplier<WebDriver> maker, String baseUrl) {
//	    super(maker,baseUrl);
//	  }
	public WebDriverBackedSeleniumWrapper(WebDriver baseDriver, String baseUrl) {
	    super(baseDriver,baseUrl);
	  }
	
	public void click(String locator){	
		try{
			super.click(locator);
		}catch(SeleniumException sele_exp){
		    TakeMethon takeMethon=new TakeMethon(sele_exp.getMessage());
            takeMethon.TakeMethonWrapper();
            
		}
	}
	  
	  public String getText(String locator) {
	      try{
	            return super.getText(locator);
	        }catch(SeleniumException sele_exp)
	        {
	           TakeMethon takeMethon=new TakeMethon(sele_exp.getMessage());
	           takeMethon.TakeMethonWrapper();
	           
	        }
	      catch (NullPointerException e) {
	          TakeMethon takeMethon=new TakeMethon(String.format("Element %s not found",locator));
              takeMethon.TakeMethonWrapper();
              
        }
	      return null;
	    	
	    	}
	  public String getAttribute(String attributeLocator){
	      try{
              return super.getAttribute(attributeLocator);
          }catch(SeleniumException sele_exp){
              TakeMethon takeMethon=new TakeMethon(sele_exp.getMessage());
              takeMethon.TakeMethonWrapper();
              
              
          }
	      return null;
          }
		
	  }


