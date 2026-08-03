package org.purpleBean.kmip.codec.xml.model.v3_0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Ephemeral XML Serialization")
class EphemeralXmlTest extends AbstractXmlSerializationTestSuite<Ephemeral> {
  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.Standard.DATA.inst();
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.Standard.EMPTY.inst();
  }
}
