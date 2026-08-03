package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ExtensionName Domain Tests")
class ExtensionNameTest extends AbstractKmipDataTypeTestSuite<ExtensionName> {

  @Override
  protected Class<ExtensionName> type() {
    return ExtensionName.class;
  }

  @Override
  protected ExtensionName createDefault() {
    return ExtensionName
        .builder()
        .value("test-extension")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}