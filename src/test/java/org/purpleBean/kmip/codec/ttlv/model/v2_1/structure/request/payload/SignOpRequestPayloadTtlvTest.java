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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SignOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SignOpRequestPayload Ttlv Serialization Tests")
class SignOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<SignOpRequestPayload> {

    @Override
    public Class<SignOpRequestPayload> type() {
        return SignOpRequestPayload.class;
    }

    @Override
    public SignOpRequestPayload createDefault() {
        return SignOpRequestPayload.builder().build();
    }

    @Override
    public SignOpRequestPayload createVariant() {
        return SignOpRequestPayload.builder().build();
    }
}