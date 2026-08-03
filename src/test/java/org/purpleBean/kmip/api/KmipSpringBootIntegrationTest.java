package org.purplebean.kmip.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.purplebean.kmip.TestApplication;
import org.purplebean.kmip.model.core.type.CryptographicLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Integration test for KMIP serialization within a Spring Boot context.
 * <p>
 * This test verifies that the KMIP serialization infrastructure is correctly configured
 * and functional when running inside a Spring Boot application.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class)
@Order(Integer.MAX_VALUE)
class KmipSpringBootIntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldSerializeAndDeserializeKmipObject() throws Exception {
    // Arrange
    CryptographicLength original = CryptographicLength.of(256);

    // Act
    String json = objectMapper.writeValueAsString(original);
    CryptographicLength deserialized = objectMapper.readValue(json, CryptographicLength.class);

    // Assert
    assertEquals(original, deserialized);
  }
}
