package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustmentValue Ttlv Serialization Tests")
class AdjustmentValueTtlvTest extends AbstractTtlvSerializationTestSuite<AdjustmentValue> {

  @Override
  public Class<AdjustmentValue> type() {
    return AdjustmentValue.class;
  }

  @Override
  public AdjustmentValue createDefault() {
    return AdjustmentValue.ofLongInteger(1L);
  }

  @Override
  public AdjustmentValue createVariant() {
    return AdjustmentValue.ofInteger(5);
  }
}
