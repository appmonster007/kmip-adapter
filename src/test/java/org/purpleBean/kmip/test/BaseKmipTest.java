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
    protected KmipSpec defaultSpec = KmipSpec.UnknownVersion;

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

    protected void setupContext() {
        KmipContext.setSpec(defaultSpec);
    }

    protected void cleanupContext() {
        KmipContext.clear();
    }

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

    protected void setupTestSpecificResources() {
        // Default implementation - override in subclasses if needed
    }

    protected void cleanupTestSpecificResources() {
        // Default implementation - override in subclasses if needed
    }

    protected void withKmipSpec(KmipSpec spec) {
        KmipContext.setSpec(spec);
    }

    protected void withKmipSpec(KmipSpec spec, Runnable operation) {
        KmipSpec originalSpec = KmipContext.getSpec();
        try {
            KmipContext.setSpec(spec);
            operation.run();
        } finally {
            if (originalSpec != null) {
                KmipContext.setSpec(originalSpec);
            } else {
                KmipContext.clear();
            }
        }
    }

    protected ObjectMapper getJsonMapper() {
        return jsonMapper;
    }

    protected XmlMapper getXmlMapper() {
        return xmlMapper;
    }
}
