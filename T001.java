package appium.andriod;

import appium.utils.ScrollUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class T001 {
    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("R3CN3019Z4P");
        options.setAutomationName("UiAutomator2");
        options.setCapability("noReset", true);
        options.setAppPackage("com.nhn.android.search");
	      options.setAppActivity(".ui.pages.SearchHomePage");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testAppLaunch() throws InterruptedException {
    
    	//       ScrollUtil을 사용하여 특정 요소가 나올 때까지 스크롤
        ScrollUtil.scrollUntilElementVisible(driver, "//android.widget.TextView[@resource-id=\"com.nhn.android.search:id/shortentsMoreBtnTitle\"]");

        // 찾은 후 클릭
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.nhn.android.search:id/shortentsMoreBtnTitle\"]")).click();
        Thread.sleep(3000);
        
        
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

		try {
		    WebElement optionElement = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"네이버페이, 버튼\"]")));
		    System.out.println("옵션이 존재함, 옵션 선택 진행");
		    optionElement.click();
		} catch (TimeoutException e) {
		    System.out.println("옵션이 없음, 바로 결제 진행");
		} 
        
        
        
        
        
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp("com.nhn.android.search");
            driver.quit();
        }
    }
}
