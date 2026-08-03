package org.purpleBean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.CertifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertifyOpRequestPayload Json Serialization Tests")
class CertifyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CertifyOpRequestPayload> {

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