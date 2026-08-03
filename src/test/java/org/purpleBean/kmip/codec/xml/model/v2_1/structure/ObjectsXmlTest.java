package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Objects;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Objects Xml Serialization Tests")
class ObjectsXmlTest extends AbstractXmlSerializationTestSuite<Objects> {

  @Override
  public Class<Objects> type() {
    return Objects.class;
  }

  @Override
  public Objects createDefault() {
    return Objects
        .builder()
        .build();
  }

  @Override
  public Objects createVariant() {
    return Objects
        .builder()
        .build();
  }
}