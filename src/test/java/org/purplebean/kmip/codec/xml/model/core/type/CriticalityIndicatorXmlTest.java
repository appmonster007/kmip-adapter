package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CriticalityIndicator XML Serialization Tests")
class CriticalityIndicatorXmlTest extends AbstractXmlSerializationTestSuite<CriticalityIndicator> {

  @Override
  public Class<CriticalityIndicator> type() {
    return CriticalityIndicator.class;
  }

  @Override
  public CriticalityIndicator createDefault() {
    return CriticalityIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  public CriticalityIndicator createVariant() {
    return CriticalityIndicator
        .builder()
        .value(false)
        .build();
  }
}