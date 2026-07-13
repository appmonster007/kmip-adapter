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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SignatureVerifyOpResponsePayload Json Serialization Tests")
class SignatureVerifyOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<SignatureVerifyOpResponsePayload> {

    @Override
    public Class<SignatureVerifyOpResponsePayload> type() {
        return SignatureVerifyOpResponsePayload.class;
    }

    @Override
    public SignatureVerifyOpResponsePayload createDefault() {
        return SignatureVerifyOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }

    @Override
    public SignatureVerifyOpResponsePayload createVariant() {
        return SignatureVerifyOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).build();
    }
}