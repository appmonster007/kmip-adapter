package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("FinalIndicator Domain Tests")
class FinalIndicatorTest extends AbstractKmipDataTypeTestSuite<FinalIndicator> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<FinalIndicator> type() {
    return FinalIndicator.class;
  }

  @Override
  protected FinalIndicator createDefault() {
    return FinalIndicator.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}