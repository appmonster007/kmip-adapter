package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ExtensionTag;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExtensionTag JSON Serialization Tests")
class ExtensionTagJsonTest extends AbstractJsonSerializationTestSuite<ExtensionTag> {

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