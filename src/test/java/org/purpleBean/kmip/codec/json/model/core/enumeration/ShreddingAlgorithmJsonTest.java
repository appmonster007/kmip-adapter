package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ShreddingAlgorithm;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ShreddingAlgorithm JSON Serialization")
class ShreddingAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<ShreddingAlgorithm> {
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
