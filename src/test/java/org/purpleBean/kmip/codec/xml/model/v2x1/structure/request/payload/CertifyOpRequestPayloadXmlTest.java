package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertifyOpRequestPayload Xml Serialization Tests")
class CertifyOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CertifyOpRequestPayload> {

  @Override
  public Class<CertifyOpRequestPayload> type() {
    return CertifyOpRequestPayload.class;
  }

  @Override
  public CertifyOpRequestPayload createDefault() {
    return CertifyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public CertifyOpRequestPayload createVariant() {
    return CertifyOpRequestPayload
        .builder()
        .build();
  }
}