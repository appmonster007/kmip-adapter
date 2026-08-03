package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReProvisionOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReProvisionOpRequestPayload Ttlv Serialization Tests")
class ReProvisionOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ReProvisionOpRequestPayload> {

  @Override
  public Class<ReProvisionOpRequestPayload> type() {
    return ReProvisionOpRequestPayload.class;
  }

  @Override
  public ReProvisionOpRequestPayload createDefault() {
    return ReProvisionOpRequestPayload
        .builder()
        .certificateRequest(CertificateRequest.of(new byte[] {0x30, 0x1A, 0x01}))
        .build();
  }

  @Override
  public ReProvisionOpRequestPayload createVariant() {
    return ReProvisionOpRequestPayload
        .builder()
        .certificateRequest(CertificateRequest.of(new byte[] {0x30, 0x1A, 0x02}))
        .build();
  }
}