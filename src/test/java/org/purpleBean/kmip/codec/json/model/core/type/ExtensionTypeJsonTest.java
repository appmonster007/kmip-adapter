package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExtensionType JSON Serialization Tests")
class ExtensionTypeJsonTest extends AbstractJsonSerializationTestSuite<ExtensionType> {

  @Override
  public Class<ExtensionType> type() {
    return ExtensionType.class;
  }

  @Override
  public ExtensionType createDefault() {
    return ExtensionType
        .builder()
        .value(1)
        .build();
  }

  @Override
  public ExtensionType createVariant() {
    return ExtensionType
        .builder()
        .value(2)
        .build();
  }
}