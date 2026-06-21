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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateCredentialOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateCredentialOpResponsePayload Json Serialization Tests")
class CreateCredentialOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<CreateCredentialOpResponsePayload> {

    @Override
    public Class<CreateCredentialOpResponsePayload> type() {
        return CreateCredentialOpResponsePayload.class;
    }

    @Override
    public CreateCredentialOpResponsePayload createDefault() {
        return CreateCredentialOpResponsePayload.builder().build();
    }

    @Override
    public CreateCredentialOpResponsePayload createVariant() {
        return CreateCredentialOpResponsePayload.builder().build();
    }
}