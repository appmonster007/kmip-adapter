package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExtensionName JSON Serialization Tests")
class ExtensionNameJsonTest extends AbstractJsonSerializationTestSuite<ExtensionName> {

  @Override
  public Class<ExtensionName> type() {
    return ExtensionName.class;
  }

  @Override
  public ExtensionName createDefault() {
    return ExtensionName
        .builder()
        .value("test-extension")
        .build();
  }

  @Override
  public ExtensionName createVariant() {
    return ExtensionName
        .builder()
        .value("another-extension")
        .build();
  }
}