package org.purpleBean.kmip.codec.xml.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateCredentialOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateCredentialOpResponsePayload Xml Serialization Tests")
class CreateCredentialOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateCredentialOpResponsePayload> {

    @Override
    public Class<CreateCredentialOpResponsePayload> type() {
        return CreateCredentialOpResponsePayload.class;
    }

    @Override
    public CreateCredentialOpResponsePayload createDefault() {
        return CreateCredentialOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public CreateCredentialOpResponsePayload createVariant() {
        return CreateCredentialOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}