package org.purpleBean.kmip.codec.json.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateUserOpResponsePayload Json Serialization Tests")
class CreateUserOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<CreateUserOpResponsePayload> {

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