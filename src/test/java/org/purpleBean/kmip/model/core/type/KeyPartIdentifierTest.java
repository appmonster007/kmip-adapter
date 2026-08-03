package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("KeyPartIdentifier Domain Tests")
class KeyPartIdentifierTest extends AbstractKmipDataTypeTestSuite<KeyPartIdentifier> {

  @Override
  protected Class<KeyPartIdentifier> type() {
    return KeyPartIdentifier.class;
  }

  @Override
  protected KeyPartIdentifier createDefault() {
    return KeyPartIdentifier
        .builder()
        .value(1)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}