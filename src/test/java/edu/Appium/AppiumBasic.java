package edu.Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumBasic {
	@Test
	public void appiumTest() throws MalformedURLException, URISyntaxException {
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("R3CN3019Z4P");
		options.setApp("C:\\Users\\sjcho88\\Desktop\\ApiDemos-debug.apk");
		AndroidDriver driver = new AndroidDriver(new URI("127.0.0.1:4723").toURL(), options);
	}
}
