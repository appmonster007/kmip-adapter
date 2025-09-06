package org.purpleBean.kmip;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Test application configuration for Spring Boot integration tests.
 * 
 * This class serves as the main entry point for Spring Boot test context
 * initialization and provides the necessary configuration for integration tests.
 * It includes proper Jackson configuration for KMIP serialization testing.
 */
@SpringBootApplication
public class TestApplication {
    
    /**
     * Main method for running the test application.
     * This is typically not used directly but serves as the entry point
     * for Spring Boot's test context loading mechanism.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
    
    /**
     * Provides a configured JSON ObjectMapper for KMIP serialization.
     * This bean is used in integration tests for JSON serialization/deserialization.
     * 
     * @return configured ObjectMapper with JSR310 time module
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
    
    /**
     * Provides a configured XML mapper for KMIP XML serialization.
     * This bean is used in integration tests for XML serialization/deserialization.
     * 
     * @return configured XmlMapper with JSR310 time module
     */
    @Bean
    public XmlMapper xmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
