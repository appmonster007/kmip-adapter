package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Right;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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