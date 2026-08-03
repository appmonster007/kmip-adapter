package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProcessOpResponsePayload Ttlv Serialization Tests")
class ProcessOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ProcessOpResponsePayload> {

  @Override
  public Class<ProcessOpResponsePayload> type() {
    return ProcessOpResponsePayload.class;
  }

  @Override
  public ProcessOpResponsePayload createDefault() {
    return ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
  }

  @Override
  public ProcessOpResponsePayload createVariant() {
    return ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x03, 0x04}))
        .build();
  }
}