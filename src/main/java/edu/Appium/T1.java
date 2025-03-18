package edu.Appium;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import edu.Appium.utils.LoginUtil;
import edu.Appium.utils.Variable;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class T1 {
    private AndroidDriver driver;
    Variable var = new Variable();

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
    }

    @Test(priority = 1)
    public void 비밀번호유효성체크() throws InterruptedException {
        // 로그인 시도 (비밀번호 오류)
        LoginUtil.login(driver, var.ID_1, var.PW_2);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click();

        // 오류 메시지 확인
        String a = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"비밀번호가 맞지 않거나\n등록된 사용자가 아닙니다.\")")).getText();
        System.out.print(a);
        Assert.assertEquals(a, "비밀번호가 맞지 않거나\n등록된 사용자가 아닙니다.");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"확인\")")).click();

        // 로그인 유지 체크 후 재로그인
        LoginUtil.login(driver, var.ID_1, var.PW_1);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"로그인 유지\"]")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click();

        Thread.sleep(3000);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"닫기버튼\")")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"종료\")")).click();

        driver.activateApp("com.homelearn.schoollearn");
        Thread.sleep(2000);
        String grade1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"1\")")).getText();
        System.out.println(grade1);
        Assert.assertEquals(grade1, "1");

        Thread.sleep(2000);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"닫기버튼\")")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그아웃\")")).click();

        WebElement loginText = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")"));
        System.out.println(loginText.getText());
        Assert.assertEquals(loginText.getText(), "로그인");
    }

    @Test(priority = 2, dependsOnMethods = {"비밀번호유효성체크"})
    public void 학년별로그인() throws InterruptedException {
        LoginUtil.login(driver, var.ID_2, var.PW_2);
        driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"로그인 유지\"]")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click();

        Thread.sleep(2000);
        String a = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2\")")).getText();
        System.out.println(a);
        Assert.assertEquals(a, "2");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.homelearn.schoollearn");
            driver.quit();
        }
    }
}
