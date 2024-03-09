package sa.osama_alharbi.cyber.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SeleniumConfiguration {

    @Bean("chromeDriver")
    public WebDriver generateChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }
    @Bean("firefoxDriver")
    public WebDriver generateFirefoxDriver(){
        return new FirefoxDriver();
    }
}
