package org.purpleBean.kmip.codec.xml.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.model.v3x0.structure.DeactivationReason;
import org.purpleBean.kmip.model.v3x0.type.DeactivationMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeactivationReason Xml Serialization Tests")
class DeactivationReasonXmlTest extends AbstractXmlSerializationTestSuite<DeactivationReason> {

  @Override
  public Class<DeactivationReason> type() {
    return DeactivationReason.class;
  }

  @Override
  public DeactivationReason createDefault() {
    return DeactivationReason
        .builder()
        .deactivationReasonCode(
            DeactivationReasonCode.of(DeactivationReasonCode.Standard.UNSPECIFIED))
        .build();
  }

  @Override
  public DeactivationReason createVariant() {
    return DeactivationReason
        .builder()
        .deactivationReasonCode(
            DeactivationReasonCode.of(DeactivationReasonCode.Standard.DEACTIVATION_DATE))
        .deactivationMessage(DeactivationMessage.of("expired"))
        .build();
  }
}