package sa.osama_alharbi.cyber.test.sql_injection;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import sa.osama_alharbi.cyber.core.TestBase;
import sa.osama_alharbi.cyber.pages.SQLInj.SQLInjectionLab2Page;


@Slf4j
public class SQLInjectionLab2Test extends TestBase {

    @Test
    public void sqlInjectionLab2Test(){
        new SQLInjectionLab2Page(this,"0a9d001604a0a8dc80fe4995007f00e0")
                .goTo()
                .checkIsNotSolved()
                .findVulnerability()
                .attack()
                .checkIsSolved();
    }
}
