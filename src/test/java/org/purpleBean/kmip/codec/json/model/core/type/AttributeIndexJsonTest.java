package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AttributeIndex;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeIndex JSON Serialization Tests")
class AttributeIndexJsonTest extends AbstractJsonSerializationTestSuite<AttributeIndex> {

  @Override
  public Class<AttributeIndex> type() {
    return AttributeIndex.class;
  }

  @Override
  public AttributeIndex createDefault() {
    return AttributeIndex
        .builder()
        .value(10)
        .build();
  }

  @Override
  public AttributeIndex createVariant() {
    return AttributeIndex
        .builder()
        .value(50)
        .build();
  }
}
