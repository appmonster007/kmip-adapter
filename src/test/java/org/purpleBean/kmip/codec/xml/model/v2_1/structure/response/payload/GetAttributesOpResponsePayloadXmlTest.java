package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetAttributesOpResponsePayload;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import java.util.List;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Xml Serialization Tests")
class GetAttributesOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<GetAttributesOpResponsePayload> {

    @Override
    public Class<GetAttributesOpResponsePayload> type() {
        return GetAttributesOpResponsePayload.class;
    }

    @Override
    public GetAttributesOpResponsePayload createDefault() {
        return GetAttributesOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).attributes(Attributes.of(List.of())).build();
    }

    @Override
    public GetAttributesOpResponsePayload createVariant() {
        return GetAttributesOpResponsePayload.builder().uniqueIdentifier(UniqueIdentifier.of("test-uid")).attributes(Attributes.of(List.of())).build();
    }
}
