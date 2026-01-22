package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ActivateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ActivateOpRequestPayload Xml Serialization Tests")
class ActivateOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<ActivateOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ActivateOpRequestPayload> type() {
        return ActivateOpRequestPayload.class;
    }

    @Override
    protected ActivateOpRequestPayload createDefault() {
        return ActivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
    }

    @Override
    protected ActivateOpRequestPayload createVariant() {
        return ActivateOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }
}
