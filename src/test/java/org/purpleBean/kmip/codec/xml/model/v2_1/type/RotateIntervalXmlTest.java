package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateInterval;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateInterval Xml Serialization Tests")
class RotateIntervalXmlTest extends AbstractXmlSerializationTestSuite<RotateInterval> {

  @Override
  public Class<RotateInterval> type() {
    return RotateInterval.class;
  }

  @Override
  public RotateInterval createDefault() {
    return RotateInterval.of(12345L);
  }

  @Override
  public RotateInterval createVariant() {
    return RotateInterval.of(54321L);
  }
}