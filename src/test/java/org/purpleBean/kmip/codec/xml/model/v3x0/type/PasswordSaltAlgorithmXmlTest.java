package org.purpleBean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.v3x0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PasswordSaltAlgorithm Xml Serialization Tests")
class PasswordSaltAlgorithmXmlTest
    extends AbstractXmlSerializationTestSuite<PasswordSaltAlgorithm> {

  @Override
  public Class<PasswordSaltAlgorithm> type() {
    return PasswordSaltAlgorithm.class;
  }

  @Override
  public PasswordSaltAlgorithm createDefault() {
    return PasswordSaltAlgorithm.of(CryptographicAlgorithm.Standard.AES);
  }

  @Override
  public PasswordSaltAlgorithm createVariant() {
    return PasswordSaltAlgorithm.of(CryptographicAlgorithm.Standard.RSA);
  }
}