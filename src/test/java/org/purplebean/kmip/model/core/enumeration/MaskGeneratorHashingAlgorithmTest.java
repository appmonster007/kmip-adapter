package org.purplebean.kmip.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipEnumerationTestSuite;

@DisplayName("MaskGeneratorHashingAlgorithm Domain Tests")
class MaskGeneratorHashingAlgorithmTest
    extends AbstractKmipEnumerationTestSuite<MaskGeneratorHashingAlgorithm> {

  @Override
  protected Class<MaskGeneratorHashingAlgorithm> type() {
    return MaskGeneratorHashingAlgorithm.class;
  }

  @Override
  protected MaskGeneratorHashingAlgorithm createDefault() {
    return MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD2);
  }

  @Override
  protected MaskGeneratorHashingAlgorithm createEqualToDefault() {
    return MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD2);
  }

  @Override
  protected MaskGeneratorHashingAlgorithm createDifferentFromDefault() {
    return MaskGeneratorHashingAlgorithm.of(HashingAlgorithm.Standard.MD4);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.ENUMERATION;
  }

  @Override
  protected boolean supportsRegistryBehavior() {
    return false;
  }

  @Override
  protected void assertLookupBehaviour() {
    // No registry behavior: this type reuses HashingAlgorithm's registered values.
  }

  @Override
  protected void assertEnumerationRegistryBehavior() {
    // No registry behavior: this type reuses HashingAlgorithm's registered values.
  }
}
