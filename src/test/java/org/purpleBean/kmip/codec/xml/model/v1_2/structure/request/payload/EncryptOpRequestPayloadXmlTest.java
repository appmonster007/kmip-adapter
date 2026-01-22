package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.EncryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EncryptOpRequestPayload Xml Serialization Tests")
class EncryptOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<EncryptOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<EncryptOpRequestPayload> type() {
        return EncryptOpRequestPayload.class;
    }

    @Override
    protected EncryptOpRequestPayload createDefault() {
        return EncryptOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    protected EncryptOpRequestPayload createVariant() {
        return EncryptOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
