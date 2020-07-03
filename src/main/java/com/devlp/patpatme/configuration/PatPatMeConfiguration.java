package com.devlp.patpatme.configuration;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@EnableCaching
@Configuration
@Data
public class PatPatMeConfiguration {

    @Bean
    public ConversionService conversionService() {
        return new DefaultConversionService();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
