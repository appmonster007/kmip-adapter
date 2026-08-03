package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.RotateLatest;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateLatest Json Serialization Tests")
class RotateLatestJsonTest extends AbstractJsonSerializationTestSuite<RotateLatest> {

  @Override
  public Class<RotateLatest> type() {
    return RotateLatest.class;
  }

  @Override
  public RotateLatest createDefault() {
    return RotateLatest.of(true);
  }

  @Override
  public RotateLatest createVariant() {
    return RotateLatest.of(false);
  }
}