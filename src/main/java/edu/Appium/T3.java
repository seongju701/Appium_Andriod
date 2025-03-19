package edu.Appium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import edu.Appium.utils.ScrollUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import edu.Appium.utils.Variable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import edu.Appium.utils.LoginUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class T3 {
    private AndroidDriver driver;
    Variable var = new Variable();
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("R9PT7000CKM");
        options.setAutomationName("UiAutomator2");
        options.setCapability("noReset", true);
        options.setAppPackage("com.homelearn.schoollearn");
        options.setAppActivity("com.homelearn.essential.activity.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void 내공상자() throws InterruptedException {
        	//내공상자 접속
        try {
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"오늘의 학습\"]"));
            System.out.println("로그인 완료");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("요소가 존재하지 않습니다. 다음 단계로 진행합니다.");
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
            LoginUtil.login(driver, var.ID_1, var.PW_1);
        	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click(); // 로그인 버튼 클릭
        }
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"학교공부\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"핵심전과\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.homelearn.schoollearn:id/dicessence_mybox_img\").instance(0)")).click();
        driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/btnYes")).click();
        driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/btnConfirm")).click();
        driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/btnExit")).click();
        driver.findElement(AppiumBy.id("com.homelearn.schoollearn:id/btnYes")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"/ability_study\")")).click();
        WebElement a = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"1장 자음자와 모음자 찾기\")"));
        
        System.out.println(a);
        Assert.assertEquals("1장 자음자와 모음자 찾기", a.getText());
        

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.homelearn.schoollearn");
            driver.quit();
        }
    }
}
