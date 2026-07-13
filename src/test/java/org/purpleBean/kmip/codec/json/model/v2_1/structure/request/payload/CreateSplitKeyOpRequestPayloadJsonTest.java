package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateSplitKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateSplitKeyOpRequestPayload Json Serialization Tests")
class CreateSplitKeyOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<CreateSplitKeyOpRequestPayload> {

    @Override
    public Class<CreateSplitKeyOpRequestPayload> type() {
        return CreateSplitKeyOpRequestPayload.class;
    }

    @Override
    public CreateSplitKeyOpRequestPayload createDefault() {
        return CreateSplitKeyOpRequestPayload.builder().build();
    }

    @Override
    public CreateSplitKeyOpRequestPayload createVariant() {
        return CreateSplitKeyOpRequestPayload.builder().build();
    }
}