package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.RotateNameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateNameValue Ttlv Serialization Tests")
class RotateNameValueTtlvTest extends AbstractTtlvSerializationTestSuite<RotateNameValue> {

  @Override
  public Class<RotateNameValue> type() {
    return RotateNameValue.class;
  }

  @Override
  public RotateNameValue createDefault() {
    return RotateNameValue.of("default-string");
  }

  @Override
  public RotateNameValue createVariant() {
    return RotateNameValue.of("variant-string");
  }
}