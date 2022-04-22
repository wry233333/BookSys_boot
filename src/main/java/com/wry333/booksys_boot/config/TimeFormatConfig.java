package com.wry333.booksys_boot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class TimeFormatConfig {
    @Bean
    public SimpleDateFormat getFormat() {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        return myFormat;
    }

}
