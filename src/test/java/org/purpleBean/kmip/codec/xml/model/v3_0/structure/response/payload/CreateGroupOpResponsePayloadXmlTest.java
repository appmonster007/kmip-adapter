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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.CreateGroupOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateGroupOpResponsePayload Xml Serialization Tests")
class CreateGroupOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateGroupOpResponsePayload> {

    @Override
    public Class<CreateGroupOpResponsePayload> type() {
        return CreateGroupOpResponsePayload.class;
    }

    @Override
    public CreateGroupOpResponsePayload createDefault() {
        return CreateGroupOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-1").build())
                .build();
    }

    @Override
    public CreateGroupOpResponsePayload createVariant() {
        return CreateGroupOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("test-uid-2").build())
                .build();
    }
}