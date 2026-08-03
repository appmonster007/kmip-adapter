package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("StreamingCapability Domain Tests")
class StreamingCapabilityTest extends AbstractKmipDataTypeTestSuite<StreamingCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<StreamingCapability> type() {
    return StreamingCapability.class;
  }

  @Override
  protected StreamingCapability createDefault() {
    return StreamingCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}