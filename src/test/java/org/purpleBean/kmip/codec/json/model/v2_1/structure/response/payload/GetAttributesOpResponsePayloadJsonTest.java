package org.purpleBean.kmip.codec.json.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetAttributesOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Json Serialization Tests")
class GetAttributesOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<GetAttributesOpResponsePayload> {

    @Override
    public Class<GetAttributesOpResponsePayload> type() {
        return GetAttributesOpResponsePayload.class;
    }

    @Override
    public GetAttributesOpResponsePayload createDefault() {
        return GetAttributesOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }

    @Override
    public GetAttributesOpResponsePayload createVariant() {
        return GetAttributesOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }
}