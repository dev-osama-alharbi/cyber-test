package sa.osama_alharbi.cyber.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import sa.osama_alharbi.cyber.service.ActionsBotService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Slf4j
@SpringBootTest
public class TestBase extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebDriver chromeDriver;
//    @Autowired
//    private WebDriver firefoxDriver;
    protected WebDriver driver;
    public Wait<WebDriver> wait;
    public ActionsBotService actionsBotService;

    @BeforeClass(alwaysRun = true)
    @BeforeSuite(alwaysRun = true)
    @Override
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @BeforeMethod
    @Parameters("target-browser")
    public void beforeMethod(@Optional("chrome") String targetBrowser){
//        log.info("Opening Chrome Browser");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("start-maximized");

        switch (targetBrowser){
            case "chrome" -> driver = chromeDriver;
//            case "firefox" -> driver = firefoxDriver;
            case "edge" -> driver = new EdgeDriver();
        }
//        driver = chromeDriver;
        driver.manage().window().setSize(new Dimension(1382,736));
        driver.manage().window().setPosition(new Point(1912,-8));

        log.info("Configuring 5 second explicit wait");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1)).withTimeout(Duration.ofSeconds(2));

        actionsBotService = new ActionsBotService(driver, wait);
    }

}
