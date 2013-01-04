package com.myhexin.common;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.selenium.Selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author Administrator
 * 
 */
public class util {
	private String TIMEOUT = "30000";

	// 点击导航栏新闻标签
	public void clickNewsTab(Selenium sel) {
		sel.click("link=新闻");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickInfoTab(Selenium sel) {
		sel.click("link=信息");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickPubnoteTab(Selenium sel) {
		sel.click("link=公告");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickReportTab(Selenium sel) {
		sel.click("link=研报");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickBlogTab(Selenium sel) {
		sel.click("link=博客");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickForumTab(Selenium sel) {
		sel.click("link=论坛");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickWeiboTab(Selenium sel) {
		sel.click("link=微博");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickStockpickTab(Selenium sel) {
		sel.click("link=选股");
		sel.waitForPageToLoad(TIMEOUT);
	}

	public void clickPeopleTab(Selenium sel) {
		sel.click("link=人物");
		sel.waitForPageToLoad(TIMEOUT);
	}

	// 点击更多标签, 进入风云榜
	// 返回0表示更多的js可以调用，1 表示调用失败
	public boolean clickMoreTabandfyb(Selenium sel) {
		sel.click("link=更多");
		boolean flag = sel.isTextPresent("风云榜");
		if (flag) {
			sel.click("id=fengyun_more_item");
			sel.waitForPageToLoad(TIMEOUT);
			return flag;
		} else {
			flag = false;
			return flag;
		}
	}

	// 点击更多标签, 热股快讯
	// 返回0表示更多的js可以调用，1 表示调用失败
	public boolean clickMoreTabandrgkx(Selenium sel) {
		sel.click("link=更多");
		boolean flag = sel.isTextPresent("风云榜");
		if (flag) {
			sel.click("id=hotnews_more_item");
			sel.waitForPageToLoad(TIMEOUT);
			return flag;
		} else {
			flag = false;
			return flag;
		}
	}

	// 得到高亮显示的导航栏标签
	public String getHLTab(Selenium sel) {
		String tab = new String();
		tab = sel.getText("//*[@class='channel_item snli01']");
		return tab;

	}

	// 得到左边高亮的标签
	public List<String> getHLlabelonLeft(Selenium sel) {
		List<String> content = new ArrayList<String>();
		Number num = sel.getXpathCount("//ul/li[@class='li01']");
		for (int i = 1; i <= num.intValue(); i++) {
			content.add(sel.getText("//ul[" + String.valueOf(i)
					+ "]/li[@class='li01']"));
		}
		return content;
	}

	// 得到指定条数的查询结果的标题
	// 新闻 信息
	public String getRLTText(Selenium sel, String index) {
		String title = new String();
		String rltxpath = "//descendant::div[@class='repeat-contain'][" + index
				+ "]/table/tbody/tr/td/h2/a";
		title = sel.getText(rltxpath);
		return title;
	}

	// 使用域研报，公告，论坛以及博客
	public String getRltText2(Selenium sel, String index) {
		String title = new String();
		String rltxpath = "//descendant::div[@class='repeat-contain'][" + index
				+ "]/h2/a";
		title = sel.getText(rltxpath);
		return title;
	}

//	// 查询指定数据
//	public void QueryResult_headpage(Selenium sel, String keyString) {
//		sel.type("//*[@id='auto']", keyString);
//		sel.click("//*[@id='searchform']/input[6]");
//
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	// 查询指定数据
	public void QueryResult(Selenium sel, String keyString) throws Exception {
		sel.type("//*[@id='auto']", keyString);
		try{
		sel.click("//*[@id='searchform']/input[10]");
		}
		catch (Exception e) {
			try{
			sel.click("//*[@id='searchform']/input[7]");}
			catch (Exception e1) {
				sel.click("//*[@id='searchform']/input[6]");
			}
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 发送URL请求的方法
	 * 
	 * @param sreurl
	 *            请求的URL地址
	 * @throws Exception
	 */
	public String send_url(String sreUrl) throws Exception {

		URL url1 = new URL(sreUrl);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				con.getInputStream(), "utf-8"));
		String str = null;
		StringBuilder builder = new StringBuilder();
		while ((str = br.readLine()) != null) {
			builder.append(str);
		}
		return builder.toString();

	}

	// 判断是不是为纯数字
	/**
	 * @param str
	 * @return
	 */
	public boolean isnotNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个元素的位置是否正确
	 * 
	 * @param sel
	 * @param location
	 * @return
	 * @throws InterruptedException
	 */
	public boolean getELementposition(Selenium sel, String location,
			String  top_location, String left_location)
			throws InterruptedException {

		Thread.sleep(5000);
		sel.windowMaximize();
		Integer topposition = (Integer) sel.getElementPositionTop(location);	
		Integer leftposition = (Integer) sel.getElementPositionLeft(location);
		if(top_location=="")
		{
			top_location=topposition.toString();
		}
		if(left_location=="")
		{
			left_location=leftposition.toString();
		}
		System.out.println(String.format("正确的位置距离顶部为%spx，本次测试获得位置距离顶部为%dpx",
				top_location, topposition));
		System.out.println(String.format("正确的位置距离左边为%spx,本次获得位置距离左边为%dpx",
				left_location, leftposition));
		// 获取离窗口顶部和最左边的误差值
		Integer top_abs = (Integer) Math.abs(topposition
				- Integer.parseInt(top_location));
		Integer width_abs = (Integer) Math.abs(leftposition
				- Integer.parseInt(left_location));
		if (top_abs < 10 & width_abs < 10) {
			return true;
		} else {
			return false;
		}
	}

	public void maximise(WebDriver driver) {

		final JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.open（'','testwindow','width=400,height=200'）");

		driver.close();

		driver.switchTo().window("testwindow");

		js.executeScript("window.moveTo（0,0）；");

		/*
		 * 1280和1024分别为窗口的宽和高，可以用下面的代码得到
		 * 
		 * screenDims = Toolkit.getDefaultToolkit（）。getScreenSize（）；
		 * 
		 * width = （int） screenDims.getWidth（）；
		 * 
		 * height = （int） screenDims.getHeight（）；
		 */

		js.executeScript("window.resizeTo（1280,1024）；");

		System.out.println(Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth());

		System.out.println(Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight());
		;

	}

	/**
	 * 根据窗口title来选择窗口
	 * 
	 * @param driver
	 * @param windowTitle
	 * @return
	 */
	public boolean switchToWindow(WebDriver driver, String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(s);
					if (driver.getTitle().contains(windowTitle)) {
						flag = true;
						System.out.println("Switch to window: " + windowTitle
								+ " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.printf("Window: " + windowTitle + " cound not found!",
					e.fillInStackTrace());
			flag = false;
		}
		return flag;
	}
	public  void screenShot(WebDriver driver,int i)
	{
			String dir_name="./pic";
		  if (!(new File(dir_name).isDirectory())) {  // 判断是否存在该目录
		     new File(dir_name).mkdir();  // 如果不存在则新建一个目录
		  }
		 
		  SimpleDateFormat sdf = new SimpleDateFormat("-HHmmss");
		  String time = sdf.format(new Date()); 
		 WebDriver augmentedDriver = new Augmenter().augment(driver);
		  try {
			 File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE); // 关键代码，执行屏幕截图，默认会把截图保存到temp目录
		     FileUtils.copyFile(screenshot, new File(dir_name + File.separator + i+ time  + ".png"));  // 这里将截图另存到我们需要保存
		  } catch (IOException e) {
		     e.printStackTrace();
		  }
	}
}




