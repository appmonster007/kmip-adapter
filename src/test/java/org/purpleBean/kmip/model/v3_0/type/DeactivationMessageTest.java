package org.purpleBean.kmip.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DeactivationMessage Domain Tests")
class DeactivationMessageTest extends AbstractKmipDataTypeTestSuite<DeactivationMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<DeactivationMessage> type() {
    return DeactivationMessage.class;
  }

  @Override
  public DeactivationMessage createDefault() {
    return DeactivationMessage.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}