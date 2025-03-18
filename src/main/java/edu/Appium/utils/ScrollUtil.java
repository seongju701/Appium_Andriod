package edu.Appium.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ScrollUtil {

    private static final int MAX_SCROLLS = 10; // 최대 스크롤 횟수

    // 특정 요소가 나올 때까지 스크롤하는 메서드
    public static void scrollUntilElementVisible(AndroidDriver driver, String xpath) {
        int scrollCount = 0;

        while (scrollCount < MAX_SCROLLS) {
            List<WebElement> elements = driver.findElements(By.xpath(xpath));

            if (!elements.isEmpty()) {
                System.out.println("🎯 찾았다! '" + xpath + "' 요소가 화면에 나타남.");
                break; // 요소가 보이면 스크롤 중지
            }

            // 요소가 없으면 스크롤 실행
            scrollDown(driver);
            scrollCount++;
        }

        if (scrollCount == MAX_SCROLLS) {
            System.out.println("⚠️ '" + xpath + "' 요소를 찾지 못했습니다.");
        }
    }

    // W3C Actions API를 이용한 스크롤
    public static void scrollDown(AndroidDriver driver) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(finger, 1)
            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 500, 1500)) // 시작점 (50%, 80%)
            .addAction(finger.createPointerDown(0))
            .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), 500, 500)) // 끝점 (50%, 20%)
            .addAction(finger.createPointerUp(0));

        driver.perform(Arrays.asList(swipe));
    }
}
