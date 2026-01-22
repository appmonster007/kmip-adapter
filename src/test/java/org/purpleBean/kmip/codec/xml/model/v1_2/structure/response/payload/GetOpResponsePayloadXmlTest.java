package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetOpResponsePayload Xml Serialization Tests")
class GetOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<GetOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<GetOpResponsePayload> type() {
        return GetOpResponsePayload.class;
    }

    @Override
    protected GetOpResponsePayload createDefault() {
        return GetOpResponsePayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
    }

    @Override
    protected GetOpResponsePayload createVariant() {
        return GetOpResponsePayload.builder()
                .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }
}
