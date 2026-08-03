package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("BatchContinueCapability Domain Tests")
class BatchContinueCapabilityTest extends AbstractKmipDataTypeTestSuite<BatchContinueCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<BatchContinueCapability> type() {
    return BatchContinueCapability.class;
  }

  @Override
  protected BatchContinueCapability createDefault() {
    return BatchContinueCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}