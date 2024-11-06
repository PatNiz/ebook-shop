package org.example.ebookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EbookServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EbookServiceApplication.class);
        app.addInitializers(new InstanceIdInitializer());  // Register the custom initializer
        app.run(args);
    }

}
