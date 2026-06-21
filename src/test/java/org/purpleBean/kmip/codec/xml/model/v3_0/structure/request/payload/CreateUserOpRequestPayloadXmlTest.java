package org.purpleBean.kmip.codec.xml.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateUserOpRequestPayload Xml Serialization Tests")
class CreateUserOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateUserOpRequestPayload> {

    @Override
    public Class<CreateUserOpRequestPayload> type() {
        return CreateUserOpRequestPayload.class;
    }

    @Override
    public CreateUserOpRequestPayload createDefault() {
        return CreateUserOpRequestPayload.builder().build();
    }

    @Override
    public CreateUserOpRequestPayload createVariant() {
        return CreateUserOpRequestPayload.builder().build();
    }
}