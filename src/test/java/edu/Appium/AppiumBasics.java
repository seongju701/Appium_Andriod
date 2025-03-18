package edu.Appium;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumBasics {
	@Test
	public void AppiumTest() throws MalformedURLException {
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("R3CN3019Z4P");
		options.setApp("C:\\Users\\sjcho88\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		
		//AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),null);
		
		
	}
}
