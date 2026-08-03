package org.purpleBean.kmip.codec.json.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.v3_0.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PasswordSaltAlgorithm Json Serialization Tests")
class PasswordSaltAlgorithmJsonTest
    extends AbstractJsonSerializationTestSuite<PasswordSaltAlgorithm> {

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