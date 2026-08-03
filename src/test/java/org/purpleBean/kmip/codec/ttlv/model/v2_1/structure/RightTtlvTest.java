package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Right;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Right Ttlv Serialization Tests")
class RightTtlvTest extends AbstractTtlvSerializationTestSuite<Right> {

  @Override
  public Class<Right> type() {
    return Right.class;
  }

  @Override
  public Right createDefault() {
    return Right
        .builder()
        .build();
  }

  @Override
  public Right createVariant() {
    return Right
        .builder()
        .build();
  }
}