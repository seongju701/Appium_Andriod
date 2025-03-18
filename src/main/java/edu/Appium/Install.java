package edu.Appium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import edu.Appium.utils.ScrollUtil; // 추가된 import
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v131.css.model.CSSStyleSheetHeader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Install {
    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("R9PT7000CKM");
        options.setAutomationName("UiAutomator2");
        options.setCapability("noReset", true); // 앱 재시작 시 상태 값 저장 
        //options.setApp("C:\\Users\\sjcho88\\Desktop\\ApiDemos-debug.apk");  // APK 인스톨 URL 선택
        options.setAppPackage("com.homelearn.schoollearn");
	    options.setAppActivity("com.homelearn.essential.activity.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 객체 못 찾을 경우 5초간 대기
    }
    
    
    @Test(priority = 1)
    public void 앱사용권한체크() throws InterruptedException {
    	//앱사용 권한체크 
    	driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/popup_check_permission_btn_confirm")).click(); // 권한 확인 팝업에서 확인 버튼 클릭
    	driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click(); // '위치 권한' 허용 버튼 클릭
    	driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click(); // '위치 권한' 다시 허용 버튼 클릭
    	driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).click(); // 최종 권한 허용 버튼 클릭
    }
    
    @Test(priority = 2, dependsOnMethods = {"앱사용권한체크"})
    public void 학부모에게_요청하여_앱_사용하기() throws InterruptedException {
    	//학부모에게 요청하여 앱 사용하기
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(5)")).click(); // 학부모 선택
    	
    	driver.findElement(AppiumBy.className("android.widget.EditText")).click(); // 연도 입력 필드 선택
    	Thread.sleep(3000);
    	
    	int[] keycodes = {8, 16, 15, 15}; // 1, 9, 8, 8의 키코드 배열

    	for (int keycode : keycodes) {
    	    Map<String, Object> keyEvent = new HashMap<>();
    	    keyEvent.put("keycode", keycode);
    	    driver.executeScript("mobile: pressKey", keyEvent);
    	    Thread.sleep(300); // 키 입력 간 딜레이 (필요 시 조절)
    	}
    	
    	driver.executeScript("mobile: hideKeyboard");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"확인\"]")).click();
    	Thread.sleep(3000);
    }

    @AfterClass
   public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.homelearn.schoollearn");
            driver.quit();
        }
    }
}
 