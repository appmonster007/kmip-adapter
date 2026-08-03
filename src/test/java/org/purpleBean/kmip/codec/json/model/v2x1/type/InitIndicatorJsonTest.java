package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.InitIndicator;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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