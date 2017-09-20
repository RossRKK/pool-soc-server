package poolsoc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {
	public static ApplicationContext appContext;
	
    public static void main(String[] args) {
    	appContext = new ClassPathXmlApplicationContext();
    	
        SpringApplication.run(Application.class, args);
    }
}