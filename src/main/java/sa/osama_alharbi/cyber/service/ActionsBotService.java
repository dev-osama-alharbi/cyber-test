package sa.osama_alharbi.cyber.service;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

//@Service
@Slf4j
//@RequiredArgsConstructor
public class ActionsBotService {
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public ActionsBotService(WebDriver driver,Wait<WebDriver> wait){
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Navigate to URL")
    public void navigate(String url){
        log.info("Navigating to: "+url);
        driver.get(url);
    }

    @Step("Type into element")
    public void type(By locator, CharSequence text){
        log.info("Typing: "+text+", into: "+locator);
        wait.until(f -> {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            return true;
        });
    }

    @Step("Click on element")
    public void click(By locator){
        log.info("Clicking: "+locator);
        wait.until(f -> {
            try {
                log.debug("Using Native Selenium Click");
                driver.findElement(locator).click();
            } catch (ElementClickInterceptedException exception){
                log.debug("Using JavaScript Click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
            }
            return true;
        });
    }

    @Step("Drag draggedBy into droppedBy")
    public void dragAndDrop(By draggedBy, By droppedBy) {
        log.info("dragAndDrop: "+draggedBy+" into "+droppedBy);
        new Actions(driver)
                .dragAndDrop(driver.findElement(draggedBy), driver.findElement(droppedBy))
                .build()
                .perform();
    }

    @Step("Get text from element")
    public String getText(By by) {
        log.info("getText: "+by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).getText();
    }

    @Step("Capturing Screenshot Evidence")
    public void capturingScreenshotEvidence(By parentBy,By evidenceBy) {
        WebElement parentElement = driver.findElement(parentBy);
        WebElement evidenceElement = driver.findElement(evidenceBy);
        // To highlight the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='2px solid red'", evidenceElement);
        try (InputStream is = Files.newInputStream(parentElement.getScreenshotAs(OutputType.FILE).toPath())) {
            Allure.attachment("Evidence.png", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // To remove border from highlighted element(Optional)
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = \"none\";", evidenceElement);
    }

    @Step("Capturing Screenshot")
    public void capturingScreenshot(By capturBy) {
        WebElement captureElement = driver.findElement(capturBy);
        try (InputStream is = Files.newInputStream(captureElement.getScreenshotAs(OutputType.FILE).toPath())) {
            Allure.attachment("image.png", is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void waitDisplay(By by) {
//        wait.until(driver1 -> driver1.findElement(by).isDisplayed());
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Step("Submit on element")
    public void submit(By by) {
        log.info("Submitting: "+by);
        wait.until(f -> {
            try {
                log.debug("Using Native Selenium submit");
                driver.findElement(by).submit();
            } catch (ElementClickInterceptedException exception){
                log.debug("Using JavaScript submit");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
            }
            return true;
        });
    }
}
