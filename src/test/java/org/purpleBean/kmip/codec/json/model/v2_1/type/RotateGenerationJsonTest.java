package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateGeneration;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateGeneration Json Serialization Tests")
class RotateGenerationJsonTest extends AbstractJsonSerializationTestSuite<RotateGeneration> {

  @Override
  public Class<RotateGeneration> type() {
    return RotateGeneration.class;
  }

  @Override
  public RotateGeneration createDefault() {
    return RotateGeneration.of(123);
  }

  @Override
  public RotateGeneration createVariant() {
    return RotateGeneration.of(456);
  }
}