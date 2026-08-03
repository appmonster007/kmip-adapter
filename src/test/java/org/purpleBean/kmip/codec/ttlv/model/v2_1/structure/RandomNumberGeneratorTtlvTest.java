package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;
import org.purpleBean.kmip.model.v2_1.structure.RandomNumberGenerator;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RandomNumberGenerator Ttlv Serialization Tests")
class RandomNumberGeneratorTtlvTest
    extends AbstractTtlvSerializationTestSuite<RandomNumberGenerator> {

  @Override
  public Class<RandomNumberGenerator> type() {
    return RandomNumberGenerator.class;
  }

  @Override
  public RandomNumberGenerator createDefault() {
    return RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
  }

  @Override
  public RandomNumberGenerator createVariant() {
    return RandomNumberGenerator.of(RngParameters.of(RngAlgorithm.Standard.UNSPECIFIED.inst()));
  }
}