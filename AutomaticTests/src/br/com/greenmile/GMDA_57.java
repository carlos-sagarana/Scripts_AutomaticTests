package br.com.greenmile;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GMDA_57 {
	
	private String server = "http://beta3.greenmile.com";
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		// setUp appium
		DesiredCapabilities capabilities = new DesiredCapabilities();

		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "Apk");
		File app = new File(appDir, "greenmile-android-release.apk");

		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.3");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.greenmile.android");
		capabilities.setCapability("appActivity", ".activity.ServerActivity");

		driver = new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"),
			capabilities);
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
						
		//Click button Save
		WebElement buttonSave = driver.findElement(By.name("general.save"));
		buttonSave.click();
		
		try{
		//Select Language
		WebElement optionLanguage = driver.findElement(By.xpath("//android.widget.ListView[1]/android.widget.CheckedTextView[2]"));
		optionLanguage.click();
		} catch(Exception e){
			System.out.println("Ocorreu tudo como esperado");
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println("O servidor está aceitando server invalido!");
	}

}
