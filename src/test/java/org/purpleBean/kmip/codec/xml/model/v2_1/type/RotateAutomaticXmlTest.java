package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateAutomatic;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateAutomatic Xml Serialization Tests")
class RotateAutomaticXmlTest extends AbstractXmlSerializationTestSuite<RotateAutomatic> {

  @Override
  public Class<RotateAutomatic> type() {
    return RotateAutomatic.class;
  }

  @Override
  public RotateAutomatic createDefault() {
    return RotateAutomatic.of(true);
  }

  @Override
  public RotateAutomatic createVariant() {
    return RotateAutomatic.of(false);
  }
}