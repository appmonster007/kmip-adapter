package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateGroupOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

@DisplayName("CreateGroupOpRequestPayload Json Serialization Tests")
class CreateGroupOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<CreateGroupOpRequestPayload> {

    @Override
    public Class<CreateGroupOpRequestPayload> type() {
        return CreateGroupOpRequestPayload.class;
    }

    @Override
    public CreateGroupOpRequestPayload createDefault() {
        return CreateGroupOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
    }

    @Override
    public CreateGroupOpRequestPayload createVariant() {
        return CreateGroupOpRequestPayload.builder().attributes(Attributes.of(java.util.List.of())).build();
    }
}