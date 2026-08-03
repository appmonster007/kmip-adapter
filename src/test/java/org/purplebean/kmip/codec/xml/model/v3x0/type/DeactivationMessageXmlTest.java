package org.purplebean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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