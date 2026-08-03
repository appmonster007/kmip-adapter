package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExtensionTag TTLV Serialization Tests")
class ExtensionTagTtlvTest extends AbstractTtlvSerializationTestSuite<ExtensionTag> {

  @Override
  public Class<ExtensionTag> type() {
    return ExtensionTag.class;
  }

  @Override
  public ExtensionTag createDefault() {
    return ExtensionTag
        .builder()
        .value(1)
        .build();
  }

  @Override
  public ExtensionTag createVariant() {
    return ExtensionTag
        .builder()
        .value(2)
        .build();
  }
}