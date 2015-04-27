package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.cgsinc.producer.controller","com.cgsinc.producer.service","com.cgsinc.producer.repository","com.cgsinc.producer.model","demo"})
public class ResourceServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceServiceAppApplication.class, args);
    }
}
