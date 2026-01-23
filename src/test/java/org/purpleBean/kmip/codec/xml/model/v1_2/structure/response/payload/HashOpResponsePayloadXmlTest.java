package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.HashOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashOpResponsePayload Xml Serialization Tests")
class HashOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<HashOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<HashOpResponsePayload> type() {
        return HashOpResponsePayload.class;
    }

    @Override
    protected HashOpResponsePayload createDefault() {
        return HashOpResponsePayload.builder()
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected HashOpResponsePayload createVariant() {
        return HashOpResponsePayload.builder()
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
