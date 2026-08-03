package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.FinalIndicator;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("FinalIndicator Ttlv Serialization Tests")
class FinalIndicatorTtlvTest extends AbstractTtlvSerializationTestSuite<FinalIndicator> {

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