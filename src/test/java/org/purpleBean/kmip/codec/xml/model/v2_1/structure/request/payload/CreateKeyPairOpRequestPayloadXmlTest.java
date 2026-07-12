package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateKeyPairOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Xml Serialization Tests")
class CreateKeyPairOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateKeyPairOpRequestPayload> {

    @Override
    public Class<CreateKeyPairOpRequestPayload> type() {
        return CreateKeyPairOpRequestPayload.class;
    }

    @Override
    public CreateKeyPairOpRequestPayload createDefault() {
        return CreateKeyPairOpRequestPayload.builder().build();
    }

    @Override
    public CreateKeyPairOpRequestPayload createVariant() {
        return CreateKeyPairOpRequestPayload.builder().build();
    }
}