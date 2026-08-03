package org.purplebean.kmip.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

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