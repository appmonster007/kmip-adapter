package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.LocateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LocateOpResponsePayload Xml Serialization Tests")
class LocateOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<LocateOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<LocateOpResponsePayload> type() {
        return LocateOpResponsePayload.class;
    }

    @Override
    protected LocateOpResponsePayload createDefault() {
        return LocateOpResponsePayload.builder().build();
    }

    @Override
    protected LocateOpResponsePayload createVariant() {
        return LocateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("12345"))
                .uniqueIdentifier(UniqueIdentifier.of("67890"))
                .build();
    }
}
