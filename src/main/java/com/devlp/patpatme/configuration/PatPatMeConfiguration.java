package com.devlp.patpatme.configuration;

import lombok.Data;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@EnableCaching
@Configuration
@Data
public class PatPatMeConfiguration {

//    @Value("${patpatme.configuration.security.cors.allowed.origins}")
//    private List<String> allowedCorsOrigins;

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }
}
