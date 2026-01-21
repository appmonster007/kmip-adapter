package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CertifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertifyOpResponsePayload Json Serialization Tests")
class CertifyOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<CertifyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<CertifyOpResponsePayload> type() {
        return CertifyOpResponsePayload.class;
    }

    @Override
    protected CertifyOpResponsePayload createDefault() {
        return CertifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected CertifyOpResponsePayload createVariant() {
        return CertifyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .build();
    }
}