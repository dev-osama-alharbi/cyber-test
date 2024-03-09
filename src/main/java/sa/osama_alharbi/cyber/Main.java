package sa.osama_alharbi.cyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(Main.class,args);
    }
}