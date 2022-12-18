package com.shopSpring.core.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secret.properties")
@EnableAspectJAutoProxy
@ComponentScan("com.shopSpring.core")
public class AppConfig {
}
