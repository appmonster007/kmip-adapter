package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ExtensionType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionType TTLV Serialization Tests")
class ExtensionTypeTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionType> {

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