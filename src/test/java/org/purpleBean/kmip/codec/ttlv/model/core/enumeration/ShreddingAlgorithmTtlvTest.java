package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ShreddingAlgorithm TTLV Serialization")
class ShreddingAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<ShreddingAlgorithm> {
  @Override
  public Class<ShreddingAlgorithm> type() {
    return ShreddingAlgorithm.class;
  }

  @Override
  public ShreddingAlgorithm createDefault() {
    return ShreddingAlgorithm.Standard.UNSPECIFIED.inst();
  }

  @Override
  public ShreddingAlgorithm createVariant() {
    return ShreddingAlgorithm.Standard.CRYPTOGRAPHIC.inst();
  }
}
