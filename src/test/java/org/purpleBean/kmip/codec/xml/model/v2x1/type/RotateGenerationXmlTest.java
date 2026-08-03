package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.RotateGeneration;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateGeneration Xml Serialization Tests")
class RotateGenerationXmlTest extends AbstractXmlSerializationTestSuite<RotateGeneration> {

  @Override
  public Class<RotateGeneration> type() {
    return RotateGeneration.class;
  }

  @Override
  public RotateGeneration createDefault() {
    return RotateGeneration.of(123);
  }

  @Override
  public RotateGeneration createVariant() {
    return RotateGeneration.of(456);
  }
}