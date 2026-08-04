package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.AdjustmentValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AdjustmentValue Json Serialization Tests")
class AdjustmentValueJsonTest extends AbstractJsonSerializationTestSuite<AdjustmentValue> {

  @Override
  public Class<AdjustmentValue> type() {
    return AdjustmentValue.class;
  }

  @Override
  public AdjustmentValue createDefault() {
    return AdjustmentValue.ofLongInteger(1L);
  }

  @Override
  public AdjustmentValue createVariant() {
    return AdjustmentValue.ofInteger(5);
  }
}
