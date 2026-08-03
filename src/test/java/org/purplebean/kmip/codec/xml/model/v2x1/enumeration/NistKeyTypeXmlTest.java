package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NistKeyType XML Serialization")
class NistKeyTypeXmlTest extends AbstractXmlSerializationTestSuite<NistKeyType> {
  @Override
  public Class<NistKeyType> type() {
    return NistKeyType.class;
  }

  @Override
  public NistKeyType createDefault() {
    return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
  }

  @Override
  public NistKeyType createVariant() {
    return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
  }
}
