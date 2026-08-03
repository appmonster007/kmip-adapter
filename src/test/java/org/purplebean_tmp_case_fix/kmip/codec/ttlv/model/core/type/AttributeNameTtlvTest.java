package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeName TTLV Serialization Tests")
class AttributeNameTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeName> {

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
