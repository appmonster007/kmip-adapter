package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ExtensionType Domain Tests")
class ExtensionTypeTest extends AbstractKmipDataTypeTestSuite<ExtensionType> {

  @Override
  protected Class<ExtensionType> type() {
    return ExtensionType.class;
  }

  @Override
  protected ExtensionType createDefault() {
    return ExtensionType
        .builder()
        .value(1)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}