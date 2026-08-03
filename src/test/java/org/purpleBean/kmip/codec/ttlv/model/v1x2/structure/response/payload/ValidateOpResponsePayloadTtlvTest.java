package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ValidateOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidateOpResponsePayload Ttlv Serialization Tests")
class ValidateOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ValidateOpResponsePayload> type() {
    return ValidateOpResponsePayload.class;
  }

  @Override
  public ValidateOpResponsePayload createDefault() {
    return ValidateOpResponsePayload
        .builder()
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
  }

  @Override
  public ValidateOpResponsePayload createVariant() {
    return ValidateOpResponsePayload
        .builder()
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.INVALID))
        .build();
  }
}
