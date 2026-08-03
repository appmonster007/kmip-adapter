package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("AsynchronousCapability Domain Tests")
class AsynchronousCapabilityTest extends AbstractKmipDataTypeTestSuite<AsynchronousCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AsynchronousCapability> type() {
    return AsynchronousCapability.class;
  }

  @Override
  protected AsynchronousCapability createDefault() {
    return AsynchronousCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}