package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("FinalIndicator Json Serialization Tests")
class FinalIndicatorJsonTest extends AbstractJsonSerializationTestSuite<FinalIndicator> {

  @Override
  public Class<FinalIndicator> type() {
    return FinalIndicator.class;
  }

  @Override
  public FinalIndicator createDefault() {
    return FinalIndicator.of(true);
  }

  @Override
  public FinalIndicator createVariant() {
    return FinalIndicator.of(false);
  }
}