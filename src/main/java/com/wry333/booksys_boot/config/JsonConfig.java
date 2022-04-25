package com.wry333.booksys_boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JsonConfig {
    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        final JavaTimeModule module = new JavaTimeModule();
//        module.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
//        module.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);

        return builder.createXmlMapper(false).modulesToInstall(module).build();
    }
}