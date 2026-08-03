package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateGeneration;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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