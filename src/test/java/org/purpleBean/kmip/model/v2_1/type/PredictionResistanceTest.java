package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


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