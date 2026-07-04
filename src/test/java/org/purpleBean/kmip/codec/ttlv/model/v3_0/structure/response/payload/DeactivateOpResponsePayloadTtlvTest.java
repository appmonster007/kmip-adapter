package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.DeactivateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivateOpResponsePayload Ttlv Serialization Tests")
class DeactivateOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DeactivateOpResponsePayload> {

    @Override
    public Class<DeactivateOpResponsePayload> type() {
        return DeactivateOpResponsePayload.class;
    }

    @Override
    public DeactivateOpResponsePayload createDefault() {
        return DeactivateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public DeactivateOpResponsePayload createVariant() {
        return DeactivateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}