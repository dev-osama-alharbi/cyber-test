package sa.osama_alharbi.cyber.test.sql_injection;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import sa.osama_alharbi.cyber.core.TestBase;
import sa.osama_alharbi.cyber.pages.SQLInj.SQLInjectionLab1Page;

@Slf4j
public class SQLInjectionLab1Test extends TestBase {

    @Test
    public void sqlInjectionLab1Test(){
        new SQLInjectionLab1Page(this,"0a9d00ae048888c18603b28e00bc00e7")
                .goTo()
                .checkIsNotSolved()
                .findVulnerability()
                .attack()
                .checkIsSolved();
    }
}
