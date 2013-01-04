package com.myhexin.common;

import com.thoughtworks.selenium.CommandProcessor;
//import com.thoughtworks.selenium.HttpCommandProcessor;

import com.thoughtworks.selenium.DefaultSelenium;

public class DefaultSelenium_xiejin extends DefaultSelenium{
	protected CommandProcessor commandProcessor_xiejin;
	
	public DefaultSelenium_xiejin(String serverHost, int serverPort, String browserStartCommand,String browserURL) {
		super(serverHost,serverPort, browserStartCommand,browserURL);
		}

		  /** Uses an arbitrary CommandProcessor */
    public DefaultSelenium_xiejin(CommandProcessor processor) {
    	super(processor);
    	}
   
    public String getText(String locator) {
    	System.out.println("in the getText");
        return commandProcessor.getString("getText", new String[] {locator,});
        
      }
}
