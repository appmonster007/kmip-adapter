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
    protected void setupDefaultSpec() {
        defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
    }

    @Override
    public Class<CreateSplitKeyOpRequestPayload> type() {
        return CreateSplitKeyOpRequestPayload.class;
    }

    @Override
    public CreateSplitKeyOpRequestPayload createDefault() {
        return CreateSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("source-key-id").build())
                .splitKeyParts(SplitKeyParts.of(3))
                .splitKeyThreshold(SplitKeyThreshold.of(2))
                .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
                .build();
    }

    @Override
    public CreateSplitKeyOpRequestPayload createVariant() {
        return CreateSplitKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("source-key-id-2").build())
                .splitKeyParts(SplitKeyParts.of(5))
                .splitKeyThreshold(SplitKeyThreshold.of(3))
                .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_28))
                .build();
    }
}