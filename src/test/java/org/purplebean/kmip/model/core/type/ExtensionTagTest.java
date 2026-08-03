package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ExtensionTag Domain Tests")
class ExtensionTagTest extends AbstractKmipDataTypeTestSuite<ExtensionTag> {

  @Override
  protected Class<ExtensionTag> type() {
    return ExtensionTag.class;
  }

  @Override
  protected ExtensionTag createDefault() {
    return ExtensionTag
        .builder()
        .value(1)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}