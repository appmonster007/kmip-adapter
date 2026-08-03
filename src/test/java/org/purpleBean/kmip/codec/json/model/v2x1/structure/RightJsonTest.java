package org.purpleBean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Right;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Right Json Serialization Tests")
class RightJsonTest extends AbstractJsonSerializationTestSuite<Right> {

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