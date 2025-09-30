package org.purpleBean.kmip;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.purpleBean.kmip.codec.KmipCodecManager;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Test application configuration for Spring Boot integration tests.
 * <p>
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
    public JsonMapper jsonMapper() {
        return KmipCodecManager.createJsonMapper();
    }

    /**
     * Provides a configured XML mapper for KMIP XML serialization.
     * This bean is used in integration tests for XML serialization/deserialization.
     *
     * @return configured XmlMapper with JSR310 time module
     */
    @Bean
    public XmlMapper xmlMapper() {
        return KmipCodecManager.createXmlMapper();
    }

    /**
     * Provides a configured TTLV mapper for KMIP TTLV serialization.
     * This bean is used in integration tests for TTLV serialization/deserialization.
     *
     * @return configured TtlvMapper
     */
    @Bean
    public TtlvMapper ttlvMapper() {
        return KmipCodecManager.createTtlvMapper();
    }
}
