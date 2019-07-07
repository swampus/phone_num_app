package load.phone.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PhoneNumApplication {
    public static void main(String[] args) {
        SpringApplication.run(PhoneNumApplication.class, args);
    }
}

