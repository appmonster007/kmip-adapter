package org.purpleBean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Rights;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Rights Xml Serialization Tests")
class RightsXmlTest extends AbstractXmlSerializationTestSuite<Rights> {

  @Override
  public Class<Rights> type() {
    return Rights.class;
  }

  @Override
  public Rights createDefault() {
    return Rights
        .builder()
        .build();
  }

  @Override
  public Rights createVariant() {
    return Rights
        .builder()
        .build();
  }
}