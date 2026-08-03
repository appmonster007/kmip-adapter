package org.purpleBean.kmip.codec.xml.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.DeactivationMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivationMessage Xml Serialization Tests")
class DeactivationMessageXmlTest extends AbstractXmlSerializationTestSuite<DeactivationMessage> {

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