package org.purplebean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReProvisionOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReProvisionOpRequestPayload Xml Serialization Tests")
class ReProvisionOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ReProvisionOpRequestPayload> {

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