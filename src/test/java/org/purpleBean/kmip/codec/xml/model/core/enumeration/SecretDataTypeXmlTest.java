package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SecretDataType XML Serialization")
class SecretDataTypeXmlTest extends AbstractXmlSerializationTestSuite<SecretDataType> {
  @Override
  public Class<SecretDataType> type() {
    return SecretDataType.class;
  }

  @Override
  public SecretDataType createDefault() {
    return SecretDataType.Standard.PASSWORD.inst();
  }

  @Override
  public SecretDataType createVariant() {
    return SecretDataType.Standard.SEED.inst();
  }
}
