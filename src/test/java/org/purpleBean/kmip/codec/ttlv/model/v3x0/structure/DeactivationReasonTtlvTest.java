package org.purplebean.kmip.codec.ttlv.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.DeactivationReasonCode;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;
import org.purplebean.kmip.model.v3x0.type.DeactivationMessage;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivationReason Ttlv Serialization Tests")
class DeactivationReasonTtlvTest extends AbstractTtlvSerializationTestSuite<DeactivationReason> {

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