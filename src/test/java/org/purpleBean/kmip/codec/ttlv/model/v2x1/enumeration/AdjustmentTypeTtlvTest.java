package org.purpleBean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AdjustmentType Ttlv Serialization Tests")
class AdjustmentTypeTtlvTest extends AbstractTtlvSerializationTestSuite<AdjustmentType> {

  @Override
  public Class<AdjustmentType> type() {
    return AdjustmentType.class;
  }

  @Override
  public AdjustmentType createDefault() {
    return AdjustmentType.Standard.INCREMENT.inst();
  }

  @Override
  public AdjustmentType createVariant() {
    return AdjustmentType.Standard.DECREMENT.inst();
  }
}