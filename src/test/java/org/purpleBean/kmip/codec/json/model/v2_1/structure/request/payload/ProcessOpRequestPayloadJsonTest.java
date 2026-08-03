package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ProcessOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProcessOpRequestPayload Json Serialization Tests")
class ProcessOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ProcessOpRequestPayload> {

  @Override
  public Class<ProcessOpRequestPayload> type() {
    return ProcessOpRequestPayload.class;
  }

  @Override
  public ProcessOpRequestPayload createDefault() {
    return ProcessOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
  }

  @Override
  public ProcessOpRequestPayload createVariant() {
    return ProcessOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(
            org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x03, 0x04}))
        .build();
  }
}