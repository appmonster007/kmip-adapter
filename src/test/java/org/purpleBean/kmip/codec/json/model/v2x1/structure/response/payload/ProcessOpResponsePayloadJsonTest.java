package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ProcessOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProcessOpResponsePayload Json Serialization Tests")
class ProcessOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ProcessOpResponsePayload> {

  @Override
  public Class<ProcessOpResponsePayload> type() {
    return ProcessOpResponsePayload.class;
  }

  @Override
  public ProcessOpResponsePayload createDefault() {
    return ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x01, 0x02}))
        .build();
  }

  @Override
  public ProcessOpResponsePayload createVariant() {
    return ProcessOpResponsePayload
        .builder()
        .asynchronousCorrelationValue(
            org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue.of(
                new byte[] {0x03, 0x04}))
        .build();
  }
}