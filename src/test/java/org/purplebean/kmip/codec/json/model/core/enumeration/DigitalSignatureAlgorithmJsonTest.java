package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DigitalSignatureAlgorithm JSON Serialization")
class DigitalSignatureAlgorithmJsonTest
    extends AbstractJsonSerializationTestSuite<DigitalSignatureAlgorithm> {
  @Override
  public Class<DigitalSignatureAlgorithm> type() {
    return DigitalSignatureAlgorithm.class;
  }

  @Override
  public DigitalSignatureAlgorithm createDefault() {
    return DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
  }

  @Override
  public DigitalSignatureAlgorithm createVariant() {
    return DigitalSignatureAlgorithm.Standard.MD5_WITH_RSA_ENCRYPTION.inst();
  }
}
