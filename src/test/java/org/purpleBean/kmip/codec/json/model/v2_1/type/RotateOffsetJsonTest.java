package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateOffset;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateOffset Json Serialization Tests")
class RotateOffsetJsonTest extends AbstractJsonSerializationTestSuite<RotateOffset> {

  @Override
  public Class<RotateOffset> type() {
    return RotateOffset.class;
  }

  @Override
  public RotateOffset createDefault() {
    return RotateOffset.of(12345L);
  }

  @Override
  public RotateOffset createVariant() {
    return RotateOffset.of(54321L);
  }
}