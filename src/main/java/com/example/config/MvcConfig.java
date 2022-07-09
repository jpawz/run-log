package com.example.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig {

    @Bean
    public LocaleResolver localeResolver() {
	SessionLocaleResolver resolver = new SessionLocaleResolver();
	resolver.setDefaultLocale(Locale.US);
	return resolver;
    }
}
