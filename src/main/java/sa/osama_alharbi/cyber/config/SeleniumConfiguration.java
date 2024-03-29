package sa.osama_alharbi.cyber.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class SeleniumConfiguration {

    @Bean("chromeDriver")
    public WebDriver generateChromeDriver(){
        log.info("Opening Chrome Browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }
    @Bean("firefoxDriver")
    public WebDriver generateFirefoxDriver(){
        log.info("Opening Firefox Browser");
        return new FirefoxDriver();
    }
}
