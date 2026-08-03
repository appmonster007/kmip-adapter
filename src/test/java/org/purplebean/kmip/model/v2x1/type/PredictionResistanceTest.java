package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PredictionResistance Domain Tests")
class PredictionResistanceTest extends AbstractKmipDataTypeTestSuite<PredictionResistance> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<PredictionResistance> type() {
    return PredictionResistance.class;
  }

  @Override
  protected PredictionResistance createDefault() {
    return PredictionResistance.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}