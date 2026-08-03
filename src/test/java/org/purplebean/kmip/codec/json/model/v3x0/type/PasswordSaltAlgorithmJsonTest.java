package org.purplebean.kmip.codec.json.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.v3x0.type.PasswordSaltAlgorithm;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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