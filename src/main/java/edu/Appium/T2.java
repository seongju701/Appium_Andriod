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

public class T2 {
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
    public void 속담의달인() throws InterruptedException {
        	//속담의 달인 접속
        try {
            driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"오늘의 학습\"]"));
            System.out.println("로그인 완료");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("요소가 존재하지 않습니다. 다음 단계로 진행합니다.");
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
            LoginUtil.login(driver, var.ID_1, var.PW_1);
        	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click(); // 로그인 버튼 클릭
        }
        	WebElement text1 = driver.findElement(By.xpath("//android.view.View[@text=\"속담의 달인\"]"));
        	Assert.assertEquals("속담의 달인", text1.getText() );
        	text1.click();
        	
        	WebElement text2 = driver.findElement(By.xpath("//android.widget.Image[@text=\"재미있는 영상으로 속담을 배우고, 간단한 활동으로 배운 내용을 정리해 보세요.\"]"));
        	Assert.assertEquals("재미있는 영상으로 속담을 배우고, 간단한 활동으로 배운 내용을 정리해 보세요.", text2.getText() );
        	driver.findElement(By.xpath("(//android.widget.Button[@text=\"닫기\"])[2]")).click();        	
        	
        	WebElement text3 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"날씨\"]"));
        	Assert.assertEquals("날씨", text3.getText() );
        	Thread.sleep(3000);
        	driver.findElement(By.xpath("//android.view.View[@resource-id=\"root\"]/android.view.View/android.view.View[3]/android.view.View/android.view.View[2]/android.widget.ListView/android.view.View[1]/android.view.View[1]/android.view.View")).click();
        	//Thread.sleep(3000);
        	
        	WebElement text4 = driver.findElement(By.xpath("//android.widget.TextView[@text=\"날씨\"]"));
        	Assert.assertEquals("날씨", text4.getText() );
        	driver.findElement(By.xpath("//android.view.View[@resource-id=\"root\"]/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.widget.Button")).click();
        
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.homelearn.schoollearn");
            driver.quit();
        }
    }
}
