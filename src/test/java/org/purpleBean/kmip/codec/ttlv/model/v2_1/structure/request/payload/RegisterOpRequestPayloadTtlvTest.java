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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RegisterOpRequestPayload Ttlv Serialization Tests")
class RegisterOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<RegisterOpRequestPayload> {

    @Override
    public Class<RegisterOpRequestPayload> type() {
        return RegisterOpRequestPayload.class;
    }

    @Override
    public RegisterOpRequestPayload createDefault() {
        return RegisterOpRequestPayload.builder().build();
    }

    @Override
    public RegisterOpRequestPayload createVariant() {
        return RegisterOpRequestPayload.builder().build();
    }
}