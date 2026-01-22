package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RevokeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("RevokeOpRequestPayload Ttlv Serialization Tests")
class RevokeOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<RevokeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RevokeOpRequestPayload> type() {
        return RevokeOpRequestPayload.class;
    }

    @Override
    protected RevokeOpRequestPayload createDefault() {
        return RevokeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .revocationReason(
                        RevocationReason.builder()
                                .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
                                .build()
                )
                .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
                .build();
    }

    @Override
    protected RevokeOpRequestPayload createVariant() {
        return RevokeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .revocationReason(
                        RevocationReason.builder()
                                .revocationReasonCode(RevocationReasonCode.Standard.AFFILIATION_CHANGED.inst())
                                .build()
                )
                .compromiseOccurrenceDate(CompromiseOccurrenceDate.of(OffsetDateTime.now(ZoneOffset.UTC).plusDays(1)))
                .build();
    }
}
