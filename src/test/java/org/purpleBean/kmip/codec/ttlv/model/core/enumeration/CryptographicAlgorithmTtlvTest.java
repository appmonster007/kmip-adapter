package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CryptographicAlgorithm TTLV Serialization")
class CryptographicAlgorithmTtlvTest
    extends AbstractTtlvSerializationTestSuite<CryptographicAlgorithm> {
  @Override
  public Class<CryptographicAlgorithm> type() {
    return CryptographicAlgorithm.class;
  }

  @Override
  public CryptographicAlgorithm createDefault() {
    return CryptographicAlgorithm.Standard.AES.inst();
  }

  @Override
  public CryptographicAlgorithm createVariant() {
    return CryptographicAlgorithm.Standard.TRIPLE_DES.inst();
  }
}
