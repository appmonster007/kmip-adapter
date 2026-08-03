package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PaddingMethod XML Serialization")
class PaddingMethodXmlTest extends AbstractXmlSerializationTestSuite<PaddingMethod> {
  @Override
  public Class<PaddingMethod> type() {
    return PaddingMethod.class;
  }

  @Override
  public PaddingMethod createDefault() {
    return PaddingMethod.Standard.NONE.inst();
  }

  @Override
  public PaddingMethod createVariant() {
    return PaddingMethod.Standard.PKCS5.inst();
  }
}
