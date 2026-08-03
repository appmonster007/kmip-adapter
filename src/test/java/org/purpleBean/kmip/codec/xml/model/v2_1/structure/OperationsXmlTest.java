package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Operations;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Operations Xml Serialization Tests")
class OperationsXmlTest extends AbstractXmlSerializationTestSuite<Operations> {

  @Override
  public Class<Operations> type() {
    return Operations.class;
  }

  @Override
  public Operations createDefault() {
    return Operations
        .builder()
        .build();
  }

  @Override
  public Operations createVariant() {
    return Operations
        .builder()
        .build();
  }
}