package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributeListOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetAttributeListOpResponsePayload Xml Serialization Tests")
class GetAttributeListOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<GetAttributeListOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<GetAttributeListOpResponsePayload> type() {
        return GetAttributeListOpResponsePayload.class;
    }

    @Override
    protected GetAttributeListOpResponsePayload createDefault() {
        return GetAttributeListOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attributeName(AttributeName.of("Attribute1"))
                .build();
    }

    @Override
    protected GetAttributeListOpResponsePayload createVariant() {
        return GetAttributeListOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attributeName(AttributeName.of("AttributeA"))
                .attributeName(AttributeName.of("AttributeB"))
                .build();
    }
}
