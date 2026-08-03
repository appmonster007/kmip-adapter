package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeName JSON Serialization Tests")
class AttributeNameJsonTest extends AbstractJsonSerializationTestSuite<AttributeName> {

  @Override
  public Class<AttributeName> type() {
    return AttributeName.class;
  }

  @Override
  public AttributeName createDefault() {
    return AttributeName
        .builder()
        .value("attribute name")
        .build();
  }

  @Override
  public AttributeName createVariant() {
    return AttributeName
        .builder()
        .value("attribute name variant")
        .build();
  }
}
