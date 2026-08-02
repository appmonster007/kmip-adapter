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
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.AddAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AddAttributeOpRequestPayload Xml Serialization Tests")
class AddAttributeOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<AddAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
    }

    @Override
    public Class<AddAttributeOpRequestPayload> type() {
        return AddAttributeOpRequestPayload.class;
    }

    @Override
    public AddAttributeOpRequestPayload createDefault() {
        return AddAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
                .newAttribute(NewAttribute.builder()
                        .attribute(CryptographicAlgorithm.Standard.AES.inst())
                        .build())
                .build();
    }

    @Override
    public AddAttributeOpRequestPayload createVariant() {
        return AddAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("test-uid-2"))
                .newAttribute(NewAttribute.builder()
                        .attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
                        .build())
                .build();
    }
}