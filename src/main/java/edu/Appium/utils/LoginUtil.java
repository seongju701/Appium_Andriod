package edu.Appium.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class LoginUtil {
	
    public static void login(AndroidDriver driver, String userId, String password) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(userId); // 사용자 ID 입력
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(password); // 비밀번호 입력
    }
}
