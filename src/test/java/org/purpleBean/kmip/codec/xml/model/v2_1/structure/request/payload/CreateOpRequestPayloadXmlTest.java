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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.CreateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateOpRequestPayload Xml Serialization Tests")
class CreateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CreateOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V2_1;
    }

    @Override
    public Class<CreateOpRequestPayload> type() {
        return CreateOpRequestPayload.class;
    }

    @Override
    public CreateOpRequestPayload createDefault() {
        return CreateOpRequestPayload.builder().objectType(ObjectType.Standard.SYMMETRIC_KEY.inst()).attributes(org.purpleBean.kmip.model.v2_1.structure.Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build()).build();
    }

    @Override
    public CreateOpRequestPayload createVariant() {
        return CreateOpRequestPayload.builder().objectType(ObjectType.Standard.SYMMETRIC_KEY.inst()).attributes(org.purpleBean.kmip.model.v2_1.structure.Attributes.builder().attribute(CryptographicAlgorithm.Standard.AES.inst()).build()).build();
    }
}