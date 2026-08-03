package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertifyOpRequestPayload Ttlv Serialization Tests")
class CertifyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CertifyOpRequestPayload> type() {
    return CertifyOpRequestPayload.class;
  }

  @Override
  public CertifyOpRequestPayload createDefault() {
    return CertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .certificateRequestType(CertificateRequestType.Standard.PKCS_10.inst())
        .certificateRequest(CertificateRequest.of(new byte[] {0x01, 0x02, 0x03}))
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public CertifyOpRequestPayload createVariant() {
    return CertifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .certificateRequestType(CertificateRequestType.Standard.PEM.inst())
        .build();
  }
}