package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ProcessOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessOpResponsePayload Xml Serialization Tests")
class ProcessOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ProcessOpResponsePayload> {

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