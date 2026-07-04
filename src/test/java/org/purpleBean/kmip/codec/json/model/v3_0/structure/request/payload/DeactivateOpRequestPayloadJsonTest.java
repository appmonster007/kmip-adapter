package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivateOpRequestPayload Json Serialization Tests")
class DeactivateOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<DeactivateOpRequestPayload> {

    @Override
    public Class<DeactivateOpRequestPayload> type() {
        return DeactivateOpRequestPayload.class;
    }

    @Override
    public DeactivateOpRequestPayload createDefault() {
        return DeactivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public DeactivateOpRequestPayload createVariant() {
        return DeactivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}