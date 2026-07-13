package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.MacVerifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacVerifyOpResponsePayload Ttlv Serialization Tests")
class MacVerifyOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacVerifyOpResponsePayload> {

    @Override
    public Class<MacVerifyOpResponsePayload> type() {
        return MacVerifyOpResponsePayload.class;
    }

    @Override
    public MacVerifyOpResponsePayload createDefault() {
        return MacVerifyOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }

    @Override
    public MacVerifyOpResponsePayload createVariant() {
        return MacVerifyOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }
}