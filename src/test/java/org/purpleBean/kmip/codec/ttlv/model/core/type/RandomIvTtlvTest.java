package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.RandomIv;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RandomIv TTLV Serialization Tests")
class RandomIvTtlvTest extends AbstractTtlvSerializationTestSuite<RandomIv> {

  @Override
  public Class<RandomIv> type() {
    return RandomIv.class;
  }

  @Override
  public RandomIv createDefault() {
    return RandomIv.of(true);
  }

  @Override
  public RandomIv createVariant() {
    return RandomIv.of(false);
  }
}