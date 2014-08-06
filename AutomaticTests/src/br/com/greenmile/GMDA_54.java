package br.com.greenmile;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GMDA_54 {
	
	private String server = "http://beta.greenmile.com";
	
	private AppiumDriver driver;
	
	private static final String url = "http://127.0.0.1:4723/wd/hub";


	@Before
	public void setUp() throws Exception {
		
		// setUp appium
		DesiredCapabilities capabilities = new DesiredCapabilities();

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "Apk");
		File app = new File(appDir, "greenmile-android-release.apk");

		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.greenmile.android");
		capabilities.setCapability("appActivity", ".activity.ServerActivity");

		driver = new AppiumDriver(new URL(url), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		//Entering server in EditText
		WebElement writeServer = driver.findElement(By.xpath("//android.widget.EditText[1]"));
		writeServer.click();
		writeServer.sendKeys(server);
		
		driver.hideKeyboard();
		
		WebElement buttonSave = driver.findElement(By.name("general.save"));
		buttonSave.click();
				
		//Select Language
		WebElement optionLanguage = driver.findElement(By.xpath("//android.widget.ListView[1]/android.widget.CheckedTextView[2]"));
		optionLanguage.click();
				
		//Click button ok
		WebElement buttonOk = driver.findElement(By.name("AndroidNameButtonOk"));
		buttonOk.click();
		
		try{
		//Click button Cancel
		WebElement number = driver.findElement(By.xpath("//android.widget.EditText[1]"));
		number.click();
		} catch(Exception e){
			System.out.println("Falha no teste");
			e.printStackTrace();
		}
	}

}
