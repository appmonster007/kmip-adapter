package org.purpleBean.kmip.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PasswordSaltAlgorithm Domain Tests")
class PasswordSaltAlgorithmTest extends AbstractKmipDataTypeTestSuite<PasswordSaltAlgorithm> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<PasswordSaltAlgorithm> type() {
    return PasswordSaltAlgorithm.class;
  }

  @Override
  public PasswordSaltAlgorithm createDefault() {
    return PasswordSaltAlgorithm.of(CryptographicAlgorithm.Standard.AES);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }
}