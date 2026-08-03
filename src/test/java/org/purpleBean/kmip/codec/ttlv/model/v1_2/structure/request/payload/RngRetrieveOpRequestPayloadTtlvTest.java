package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RngRetrieveOpRequestPayload Ttlv Serialization Tests")
class RngRetrieveOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<RngRetrieveOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RngRetrieveOpRequestPayload> type() {
    return RngRetrieveOpRequestPayload.class;
  }

  @Override
  public RngRetrieveOpRequestPayload createDefault() {
    return RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
  }

  @Override
  public RngRetrieveOpRequestPayload createVariant() {
    return RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(32))
        .build();
  }
}
