package sa.osama_alharbi.cyber.test.sql_injection;

import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
import sa.osama_alharbi.cyber.core.TestBase;
import org.testng.annotations.Test;
import sa.osama_alharbi.cyber.pages.SQLInj.SQLInjectionLab1Page;

//@SpringBootTest
//@RequiredArgsConstructor
@Slf4j
public class OsamaTest extends TestBase {

    @Test
    public void testTest(){
//        log.info("HIIIIIIIIIIIIIIIIIIIIIII");
//        actionsBotService.navigate("http://google.com");
//        actionsBotService.type(By.cssSelector("div>textarea.gLFyf"),"أصيل");
//        actionsBotService.click(By.cssSelector("div.A8SBwf>div.lJ9FBc>center>input.gNO89b"));
//        SQLInj1Page sqlInj1Page = new SQLInj1Page(this,"");
        new SQLInjectionLab1Page(this,"0a0a001403975d8a80064921004d0057")
                .goTo()
                .checkIsNotSolved()
                .findVulnerability()
                .attack()
                .checkIsSolved();
    }
}
