package org.purplebean.kmip.test;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.purplebean.kmip.api.KmipContext;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.codec.KmipCodecManager;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

/**
 * Base test class providing common setup and utilities for KMIP tests. Follows the Template Method
 * pattern to provide consistent test environment setup.
 */
@ExtendWith(MockitoExtension.class)
public abstract class BaseKmipTest {

  protected JsonMapper jsonMapper;
  protected XmlMapper xmlMapper;
  protected TtlvMapper ttlvMapper;
  protected KmipSpec defaultSpec;

  @BeforeEach
  void setUp() {
    setupDefaultSpec();
    setupMappers();
    setupContext();
    setupTestSpecificResources();
  }

  @AfterEach
  void tearDown() {
    cleanupContext();
    cleanupTestSpecificResources();
  }

  /**
   * Sets {@link #defaultSpec} to the value used for tests unless overridden by a subclass.
   */
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  /**
   * Activates {@link #defaultSpec} in the {@link KmipContext}.
   */
  protected void setupContext() {
    KmipContext.setSpec(defaultSpec);
  }

  /**
   * Clears the active {@link KmipContext} spec.
   */
  protected void cleanupContext() {
    KmipContext.clear();
  }

  /**
   * Initializes the JSON, XML, and TTLV mappers used by tests.
   */
  protected void setupMappers() {
    jsonMapper = KmipCodecManager.getJsonMapper();
    xmlMapper = KmipCodecManager.getXmlMapper();
    ttlvMapper = KmipCodecManager.getTtlvMapper();
  }

  /**
   * Hook for subclasses to set up additional test-specific resources. No-op by default.
   */
  protected void setupTestSpecificResources() {
    // Default implementation - override in subclasses if needed
  }

  /**
   * Hook for subclasses to tear down additional test-specific resources. No-op by default.
   */
  protected void cleanupTestSpecificResources() {
    // Default implementation - override in subclasses if needed
  }

  /**
   * Sets the active {@link KmipContext} spec to {@code spec}.
   */
  protected void withKmipSpec(KmipSpec spec) {
    KmipContext.setSpec(spec);
  }

  /**
   * Runs {@code operation} with the active {@link KmipContext} spec temporarily set to
   * {@code spec}, restoring the original spec afterward.
   */
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

  protected JsonMapper getJsonMapper() {
    return jsonMapper;
  }

  protected XmlMapper getXmlMapper() {
    return xmlMapper;
  }

  protected TtlvMapper getTtlvMapper() {
    return ttlvMapper;
  }
}
