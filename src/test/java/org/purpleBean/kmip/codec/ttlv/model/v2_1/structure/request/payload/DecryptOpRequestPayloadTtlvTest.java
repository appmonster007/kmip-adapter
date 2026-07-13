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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DecryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DecryptOpRequestPayload Ttlv Serialization Tests")
class DecryptOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DecryptOpRequestPayload> {

    @Override
    public Class<DecryptOpRequestPayload> type() {
        return DecryptOpRequestPayload.class;
    }

    @Override
    public DecryptOpRequestPayload createDefault() {
        return DecryptOpRequestPayload.builder().build();
    }

    @Override
    public DecryptOpRequestPayload createVariant() {
        return DecryptOpRequestPayload.builder().build();
    }
}