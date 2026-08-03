package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SplitKeyThreshold XML Serialization Tests")
class SplitKeyThresholdXmlTest extends AbstractXmlSerializationTestSuite<SplitKeyThreshold> {

  @Override
  public Class<SplitKeyThreshold> type() {
    return SplitKeyThreshold.class;
  }

  @Override
  public SplitKeyThreshold createDefault() {
    return SplitKeyThreshold
        .builder()
        .value(2)
        .build();
  }

  @Override
  public SplitKeyThreshold createVariant() {
    return SplitKeyThreshold
        .builder()
        .value(3)
        .build();
  }
}