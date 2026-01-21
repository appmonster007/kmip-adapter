package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertifyOpRequestPayload Xml Serialization Tests")
class CertifyOpRequestPayloadXmlTest extends AbstractXmlSerializationTestSuite<CertifyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<CertifyOpRequestPayload> type() {
        return CertifyOpRequestPayload.class;
    }

    @Override
    protected CertifyOpRequestPayload createDefault() {
        return CertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
                .certificateRequest(CertificateRequest.of(new byte[]{0x01, 0x02, 0x03}))
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected CertifyOpRequestPayload createVariant() {
        return CertifyOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .certificateRequestType(CertificateRequestType.Standard.PEM.inst())
                .build();
    }
}