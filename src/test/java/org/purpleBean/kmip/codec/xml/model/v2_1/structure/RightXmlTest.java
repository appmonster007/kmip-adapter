package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Right;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Right Xml Serialization Tests")
class RightXmlTest extends AbstractXmlSerializationTestSuite<Right> {

  @Override
  public Class<Right> type() {
    return Right.class;
  }

  @Override
  public Right createDefault() {
    return Right
        .builder()
        .build();
  }

  @Override
  public Right createVariant() {
    return Right
        .builder()
        .build();
  }
}