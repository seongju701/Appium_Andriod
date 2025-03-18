package edu.Schoollearn;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import edu.Appium.utils.ScrollUtil; // 추가된 import
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class T13 {
    private AndroidDriver driver;

    @BeforeMethod
	@BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("R9PT7000CKM");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.homelearn.schoollearn");
        options.setAppActivity("com.homelearn.essential.activity.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(priority = 1)
    public void 네이버_로그인() throws InterruptedException {
    	 driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/popup_check_permission_btn_confirm")).click();

        // 권한 허용 버튼 클릭 (foreground only)
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        
        
    }

    @Test(priority = 2)
    public void 카카오_로그인() throws InterruptedException {
        // 두 번째 테스트 케이스
        System.out.println("두 번째 테스트 실행 중...");
        driver.findElement(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).click();

    }

    @AfterMethod
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
