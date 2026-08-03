package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CertifyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertifyOpResponsePayload Ttlv Serialization Tests")
class CertifyOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CertifyOpResponsePayload> type() {
    return CertifyOpResponsePayload.class;
  }

  @Override
  public CertifyOpResponsePayload createDefault() {
    return CertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public CertifyOpResponsePayload createVariant() {
    return CertifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .build();
  }
}