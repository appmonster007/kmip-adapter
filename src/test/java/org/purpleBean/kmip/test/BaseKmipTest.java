package org.purpleBean.kmip.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;

/**
 * Base test class providing common setup and utilities for KMIP tests. Follows the Template Method
 * pattern to provide consistent test environment setup.
 */
@ExtendWith(MockitoExtension.class)
public abstract class BaseKmipTest {

    protected ObjectMapper jsonMapper;
    protected XmlMapper xmlMapper;
    protected KmipSpec defaultSpec = KmipSpec.V1_2;

    @BeforeEach
    void setUp() {
        setupContext();
        setupMappers();
        setupTestSpecificResources();
    }

    @AfterEach
    void tearDown() {
        cleanupContext();
        cleanupTestSpecificResources();
    }

    /**
     * Sets up the KMIP codec context with default specification.
     */
    protected void setupContext() {
        KmipContext.setSpec(defaultSpec);
    }

    /**
     * Cleans up the KMIP codec context.
     */
    protected void cleanupContext() {
        KmipContext.clear();
    }

    /**
     * Sets up JSON and XML mappers with required modules.
     */
    protected void setupMappers() {
        // JSON Mapper setup
        jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.registerModule(new KmipJsonModule());

        // XML Mapper setup
        xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.registerModule(new KmipXmlModule());
    }

    /**
     * Template method for test-specific resource setup. Override in subclasses to provide custom
     * setup logic.
     */
    protected void setupTestSpecificResources() {
        // Default implementation - override in subclasses if needed
    }

    /**
     * Template method for test-specific resource cleanup. Override in subclasses to provide custom
     * cleanup logic.
     */
    protected void cleanupTestSpecificResources() {
        // Default implementation - override in subclasses if needed
    }

    /**
     * Changes the KMIP specification context for testing different versions.
     *
     * @param spec the KMIP specification to use
     */
    protected void withKmipSpec(KmipSpec spec) {
        KmipContext.setSpec(spec);
    }

    /**
     * Executes a test operation with a specific KMIP specification context.
     *
     * @param spec      the KMIP specification to use
     * @param operation the test operation to execute
     */
    protected void withKmipSpec(KmipSpec spec, Runnable operation) {
        KmipSpec originalSpec = KmipContext.getSpec();
        try {
            KmipContext.setSpec(spec);
            operation.run();
        } finally {
            // Ensure we properly clean up and restore the original spec
            if (originalSpec != null) {
                KmipContext.setSpec(originalSpec);
            } else {
                KmipContext.clear();
            }
        }
    }

    /**
     * Gets the current JSON mapper instance.
     *
     * @return configured ObjectMapper for JSON operations
     */
    protected ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    /**
     * Gets the current XML mapper instance.
     *
     * @return configured XmlMapper for XML operations
     */
    protected XmlMapper getXmlMapper() {
        return xmlMapper;
    }
}
