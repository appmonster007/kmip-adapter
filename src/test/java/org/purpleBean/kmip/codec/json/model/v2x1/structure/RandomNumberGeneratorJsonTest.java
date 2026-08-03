package org.purplebean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;
import org.purplebean.kmip.model.v2x1.structure.RandomNumberGenerator;
import org.purplebean.kmip.model.v2x1.structure.RngParameters;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RandomNumberGenerator Json Serialization Tests")
class RandomNumberGeneratorJsonTest
    extends AbstractJsonSerializationTestSuite<RandomNumberGenerator> {

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