package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ValidateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidateOpRequestPayload Json Serialization Tests")
class ValidateOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ValidateOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ValidateOpRequestPayload> type() {
    return ValidateOpRequestPayload.class;
  }

  @Override
  public ValidateOpRequestPayload createDefault() {
    return ValidateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .validityDate(ValidityDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
  }

  @Override
  public ValidateOpRequestPayload createVariant() {
    return ValidateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .validityDate(ValidityDate.of(OffsetDateTime
            .now(ZoneOffset.UTC)
            .plusDays(1)))
        .build();
  }
}
