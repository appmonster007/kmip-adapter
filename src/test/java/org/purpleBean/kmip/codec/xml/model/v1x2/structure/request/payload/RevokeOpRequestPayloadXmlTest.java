package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RevokeOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RevokeOpRequestPayload Xml Serialization Tests")
class RevokeOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RevokeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RevokeOpRequestPayload> type() {
    return RevokeOpRequestPayload.class;
  }

  @Override
  public RevokeOpRequestPayload createDefault() {
    return RevokeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .revocationReason(
            RevocationReason
                .builder()
                .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
                .build()
        )
        .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
        .build();
  }

  @Override
  public RevokeOpRequestPayload createVariant() {
    return RevokeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .revocationReason(
            RevocationReason
                .builder()
                .revocationReasonCode(RevocationReasonCode.Standard.AFFILIATION_CHANGED.inst())
                .build()
        )
        .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime
            .now(ZoneOffset.UTC)
            .plusDays(1)))
        .build();
  }
}
