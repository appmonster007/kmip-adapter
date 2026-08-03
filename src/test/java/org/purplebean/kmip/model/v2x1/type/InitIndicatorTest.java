package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InitIndicator Domain Tests")
class InitIndicatorTest extends AbstractKmipDataTypeTestSuite<InitIndicator> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<InitIndicator> type() {
    return InitIndicator.class;
  }

  @Override
  protected InitIndicator createDefault() {
    return InitIndicator.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}