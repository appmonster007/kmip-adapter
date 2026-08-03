package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeValue Ttlv Serialization Tests")
class AttributeValueTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValue> {

  @Override
  public Class<AttributeValue> type() {
    return AttributeValue.class;
  }

  @Override
  public AttributeValue createDefault() {
    return AttributeValue.ofTextString("default-string");
  }

  @Override
  public AttributeValue createVariant() {
    return AttributeValue.ofInteger(123);
  }
}