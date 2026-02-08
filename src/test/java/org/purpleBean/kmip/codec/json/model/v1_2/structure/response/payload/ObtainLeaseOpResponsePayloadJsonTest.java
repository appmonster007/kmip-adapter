package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ObtainLeaseOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ObtainLeaseOpResponsePayload Json Serialization Tests")
class ObtainLeaseOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<ObtainLeaseOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<ObtainLeaseOpResponsePayload> type() {
        return ObtainLeaseOpResponsePayload.class;
    }

    @Override
    public ObtainLeaseOpResponsePayload createDefault() {
        return ObtainLeaseOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .leaseTime(LeaseTime.of(3600))
                .lastChangeDate(LastChangeDate.of(OffsetDateTime.now(ZoneOffset.UTC)))
                .build();
    }

    @Override
    public ObtainLeaseOpResponsePayload createVariant() {
        return ObtainLeaseOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .leaseTime(LeaseTime.of(7200))
                .lastChangeDate(LastChangeDate.of(OffsetDateTime.now(ZoneOffset.UTC).plusDays(1)))
                .build();
    }
}
