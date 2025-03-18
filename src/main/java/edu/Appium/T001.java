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
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class T001 {
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
    	//비밀번호 오류 얼럿, 로그인 유지 검수
    
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(var.ID_1); // 사용자 ID 입력
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(var.PW_2); // 비밀번호 입력
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click(); // 로그인 버튼 클릭
    	String a = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"비밀번호가 맞지 않거나\n등록된 사용자가 아닙니다.\")")).getText(); // 오류 메시지 클릭 (비밀번호 오류)
    	System.out.print(a);
    	Assert.assertEquals(a, "비밀번호가 맞지 않거나\n등록된 사용자가 아닙니다.");
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"확인\")")).click(); // "확인" 버튼 클릭
    	// 로그인유지 확인
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(var.ID_1); // 사용자 ID 입력
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(var.PW_1); // 비밀번호 입력
    	driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"로그인 유지\"]")).click(); // 로그인 유지 체크박스 클릭
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click(); // 로그인 버튼 클릭
    	Thread.sleep(3000); // 3초 대기
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"닫기버튼\")")).click(); // "닫기버튼" 클릭
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"종료\")")).click(); // "종료" 버튼 클릭
    	driver.activateApp("com.homelearn.schoollearn"); // 앱 재시작
    	Thread.sleep(2000);
    	String grade1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"1\")")).getText(); // "1" 텍스트 가져오기
    	System.out.println(grade1); // "1" 값 출력
    	Assert.assertEquals(grade1, "1"); // "1" 값 확인
    	Thread.sleep(2000);
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"닫기버튼\")")).click(); // "닫기버튼" 클릭
    	Thread.sleep(2000);
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그아웃\")")).click(); // "로그아웃" 클릭
    	WebElement loginText = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")); // "로그인" 버튼 텍스트 가져오기
    	System.out.println(loginText.getText()); // "로그인" 버튼 텍스트 출력
    	Assert.assertEquals(loginText.getText(), "로그인"); // "로그인" 텍스트 확인
    	Thread.sleep(3000);
    }
    
    
    @Test(priority = 2, dependsOnMethods = {"비밀번호유효성체크"})
    public void 학년별로그인() throws InterruptedException {
    	
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).clear(); // 사용자 ID 초기화
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")).sendKeys(var.ID_2); // 사용자 ID 입력
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)")).sendKeys(var.PW_2); // 비밀번호 입력
    	driver.findElement(AppiumBy.xpath("//android.view.View[@text=\"로그인 유지\"]")).click(); // 로그인 유지 체크박스 클릭
    	driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"로그인\")")).click(); // 로그인 버튼 클릭
    	Thread.sleep(2000); // 2초 대기
    	String a = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"2\")")).getText(); // "2" 텍스트 가져오기
    	System.out.println(a); // "2" 값 출력
    	Assert.assertEquals(a, "2"); // "2" 값 확인
    	
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.homelearn.schoollearn");
            driver.quit();
        }
    }
}
