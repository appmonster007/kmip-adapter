package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.MaskGeneratorHashingAlgorithm;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MaskGeneratorHashingAlgorithm JSON Serialization")
class MaskGeneratorHashingAlgorithmJsonTest
    extends AbstractJsonSerializationTestSuite<MaskGeneratorHashingAlgorithm> {
  @Override
  public Class<MaskGeneratorHashingAlgorithm> type() {
    return MaskGeneratorHashingAlgorithm.class;
  }

  @Override
  public MaskGeneratorHashingAlgorithm createDefault() {
    return MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD2);
  }

  @Override
  public MaskGeneratorHashingAlgorithm createVariant() {
    return MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD4);
  }
}
