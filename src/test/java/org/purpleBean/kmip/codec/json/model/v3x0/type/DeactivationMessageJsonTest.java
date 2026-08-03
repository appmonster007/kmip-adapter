package org.purpleBean.kmip.codec.json.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.DeactivationMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivationMessage Json Serialization Tests")
class DeactivationMessageJsonTest extends AbstractJsonSerializationTestSuite<DeactivationMessage> {

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