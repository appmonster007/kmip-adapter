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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateCredentialOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateCredentialOpRequestPayload Json Serialization Tests")
class CreateCredentialOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<CreateCredentialOpRequestPayload> {

    @Override
    public Class<CreateCredentialOpRequestPayload> type() {
        return CreateCredentialOpRequestPayload.class;
    }

    @Override
    public CreateCredentialOpRequestPayload createDefault() {
        return CreateCredentialOpRequestPayload.builder().build();
    }

    @Override
    public CreateCredentialOpRequestPayload createVariant() {
        return CreateCredentialOpRequestPayload.builder().build();
    }
}