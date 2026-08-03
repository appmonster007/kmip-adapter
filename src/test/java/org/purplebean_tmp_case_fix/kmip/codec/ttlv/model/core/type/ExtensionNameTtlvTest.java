package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ExtensionName;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionName TTLV Serialization Tests")
class ExtensionNameTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionName> {

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