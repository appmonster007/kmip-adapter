package org.purpleBean.kmip.codec.ttlv.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.DeactivationMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivationMessage Ttlv Serialization Tests")
class DeactivationMessageTtlvTest extends AbstractTtlvSerializationTestSuite<DeactivationMessage> {

  @Override
  public Class<DeactivationMessage> type() {
    return DeactivationMessage.class;
  }

  @Override
  public DeactivationMessage createDefault() {
    return DeactivationMessage.of("default-string");
  }

  @Override
  public DeactivationMessage createVariant() {
    return DeactivationMessage.of("variant-string");
  }
}