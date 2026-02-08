package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RecertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RecertifyOpRequestPayload Ttlv Serialization Tests")
class RecertifyOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<RecertifyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<RecertifyOpRequestPayload> type() {
        return RecertifyOpRequestPayload.class;
    }

    @Override
    public RecertifyOpRequestPayload createDefault() {
        return RecertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
                .certificateRequest(CertificateRequest.of(new byte[]{0x01, 0x02, 0x03}))
                .offset(Offset.builder().value(100).build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    public RecertifyOpRequestPayload createVariant() {
        return RecertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
                .offset(Offset.builder().value(200).build())
                .build();
    }
}