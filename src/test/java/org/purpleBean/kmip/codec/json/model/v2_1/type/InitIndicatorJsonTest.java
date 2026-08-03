package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InitIndicator Json Serialization Tests")
class InitIndicatorJsonTest extends AbstractJsonSerializationTestSuite<InitIndicator> {

  @Override
  public Class<InitIndicator> type() {
    return InitIndicator.class;
  }

  @Override
  public InitIndicator createDefault() {
    return InitIndicator.of(true);
  }

  @Override
  public InitIndicator createVariant() {
    return InitIndicator.of(false);
  }
}