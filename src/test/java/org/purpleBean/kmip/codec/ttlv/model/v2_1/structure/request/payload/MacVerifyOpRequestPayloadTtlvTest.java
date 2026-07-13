package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacVerifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacVerifyOpRequestPayload Ttlv Serialization Tests")
class MacVerifyOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacVerifyOpRequestPayload> {

    @Override
    public Class<MacVerifyOpRequestPayload> type() {
        return MacVerifyOpRequestPayload.class;
    }

    @Override
    public MacVerifyOpRequestPayload createDefault() {
        return MacVerifyOpRequestPayload.builder().build();
    }

    @Override
    public MacVerifyOpRequestPayload createVariant() {
        return MacVerifyOpRequestPayload.builder().build();
    }
}