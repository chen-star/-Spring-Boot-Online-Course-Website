package com.course.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EnableEurekaClient
public class SystemApplication {

    private static final Logger lOG = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SystemApplication.class);
        Environment env = app.run(args).getEnvironment();
        lOG.info("App Started!");
        lOG.info("System address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
