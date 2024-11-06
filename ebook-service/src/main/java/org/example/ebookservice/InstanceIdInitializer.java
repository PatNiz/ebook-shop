package org.example.ebookservice;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

public class InstanceIdInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String uniqueId = UUID.randomUUID().toString();
        System.setProperty("application.instance.unique-id", uniqueId);
    }
}
