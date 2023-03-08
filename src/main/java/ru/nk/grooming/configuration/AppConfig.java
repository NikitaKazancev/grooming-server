package ru.nk.grooming.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@EnableMBeanExport
@Import({DatabaseConfig.class, SecurityConfig.class})
public class AppConfig {
}
