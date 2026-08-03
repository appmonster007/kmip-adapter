package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PredictionResistance Json Serialization Tests")
class PredictionResistanceJsonTest
    extends AbstractJsonSerializationTestSuite<PredictionResistance> {

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