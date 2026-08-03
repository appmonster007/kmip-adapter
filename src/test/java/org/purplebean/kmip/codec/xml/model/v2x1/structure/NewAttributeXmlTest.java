package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NewAttribute Xml Serialization Tests")
class NewAttributeXmlTest extends AbstractXmlSerializationTestSuite<NewAttribute> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public Class<NewAttribute> type() {
    return NewAttribute.class;
  }

  @Override
  public NewAttribute createDefault() {
    return NewAttribute
        .builder()
        .attribute(CryptographicAlgorithm.Standard.AES.inst())
        .build();
  }

  @Override
  public NewAttribute createVariant() {
    return NewAttribute
        .builder()
        .attribute(CryptographicAlgorithm.Standard.RSA.inst())
        .build();
  }
}
