package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AdjustmentValue Domain Tests")
class AdjustmentValueTest extends AbstractKmipDataTypeTestSuite<AdjustmentValue> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<AdjustmentValue> type() {
    return AdjustmentValue.class;
  }

  @Override
  protected AdjustmentValue createDefault() {
    return AdjustmentValue.ofLongInteger(1L);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.LONG_INTEGER;
  }
}
