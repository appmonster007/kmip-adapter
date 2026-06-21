package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateUserOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateUserOpResponsePayload Ttlv Serialization Tests")
class CreateUserOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<CreateUserOpResponsePayload> {

    @Override
    public Class<CreateUserOpResponsePayload> type() {
        return CreateUserOpResponsePayload.class;
    }

    @Override
    public CreateUserOpResponsePayload createDefault() {
        return CreateUserOpResponsePayload.builder().build();
    }

    @Override
    public CreateUserOpResponsePayload createVariant() {
        return CreateUserOpResponsePayload.builder().build();
    }
}