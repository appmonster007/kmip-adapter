package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("FixedFieldLength TTLV Serialization Tests")
class FixedFieldLengthTtlvTest extends AbstractTtlvSerializationTestSuite<FixedFieldLength> {

  @Override
  public Class<FixedFieldLength> type() {
    return FixedFieldLength.class;
  }

  @Override
  public FixedFieldLength createDefault() {
    return FixedFieldLength.of(128);
  }

  @Override
  public FixedFieldLength createVariant() {
    return FixedFieldLength.of(256);
  }
}