package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NeverExtractable Json Serialization Tests")
class NeverExtractableJsonTest extends AbstractJsonSerializationTestSuite<NeverExtractable> {

  @Override
  public Class<NeverExtractable> type() {
    return NeverExtractable.class;
  }

  @Override
  public NeverExtractable createDefault() {
    return NeverExtractable.of(true);
  }

  @Override
  public NeverExtractable createVariant() {
    return NeverExtractable.of(false);
  }
}