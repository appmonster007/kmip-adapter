package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.AttributeReference;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeReference Ttlv Serialization Tests")
class AttributeReferenceTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeReference> {

  @Override
  public Class<AttributeReference> type() {
    return AttributeReference.class;
  }

  @Override
  public AttributeReference createDefault() {
    return AttributeReference
        .builder()
        .build();
  }

  @Override
  public AttributeReference createVariant() {
    return AttributeReference
        .builder()
        .build();
  }
}