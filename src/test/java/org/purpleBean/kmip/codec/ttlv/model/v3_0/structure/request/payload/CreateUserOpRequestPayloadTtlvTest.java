package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

@DisplayName("CreateUserOpRequestPayload Ttlv Serialization Tests")
class CreateUserOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<CreateUserOpRequestPayload> {

    @Override
    public Class<CreateUserOpRequestPayload> type() {
        return CreateUserOpRequestPayload.class;
    }

    @Override
    public CreateUserOpRequestPayload createDefault() {
        return CreateUserOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
    }

    @Override
    public CreateUserOpRequestPayload createVariant() {
        return CreateUserOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
    }
}