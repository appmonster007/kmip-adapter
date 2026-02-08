package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.HashOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("HashOpRequestPayload Xml Serialization Tests")
class HashOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<HashOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<HashOpRequestPayload> type() {
        return HashOpRequestPayload.class;
    }

    @Override
    public HashOpRequestPayload createDefault() {
        return HashOpRequestPayload.builder()
                .cryptographicParameters(CryptographicParameters.builder().build())
                .data(DataByteString.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    public HashOpRequestPayload createVariant() {
        return HashOpRequestPayload.builder()
                .cryptographicParameters(CryptographicParameters.builder().build())
                .data(DataByteString.of(new byte[]{4, 5, 6}))
                .build();
    }
}
