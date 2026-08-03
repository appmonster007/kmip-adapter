package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.PredictionResistance;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PredictionResistance Ttlv Serialization Tests")
class PredictionResistanceTtlvTest
    extends AbstractTtlvSerializationTestSuite<PredictionResistance> {

  @Override
  public Class<PredictionResistance> type() {
    return PredictionResistance.class;
  }

  @Override
  public PredictionResistance createDefault() {
    return PredictionResistance.of(true);
  }

  @Override
  public PredictionResistance createVariant() {
    return PredictionResistance.of(false);
  }
}