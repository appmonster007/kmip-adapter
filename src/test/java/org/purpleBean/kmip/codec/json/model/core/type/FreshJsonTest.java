package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Fresh;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Fresh JSON Serialization Tests")
class FreshJsonTest extends AbstractJsonSerializationTestSuite<Fresh> {

  @Override
  public Class<Fresh> type() {
    return Fresh.class;
  }

  @Override
  public Fresh createDefault() {
    return Fresh
        .builder()
        .value(true)
        .build();
  }

  @Override
  public Fresh createVariant() {
    return Fresh
        .builder()
        .value(false)
        .build();
  }
}