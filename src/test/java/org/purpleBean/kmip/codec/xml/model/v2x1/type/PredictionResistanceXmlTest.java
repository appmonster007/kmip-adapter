package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.PredictionResistance;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PredictionResistance Xml Serialization Tests")
class PredictionResistanceXmlTest extends AbstractXmlSerializationTestSuite<PredictionResistance> {

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