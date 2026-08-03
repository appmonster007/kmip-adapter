package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ProcessOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessOpRequestPayload Xml Serialization Tests")
class ProcessOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ProcessOpRequestPayload> {

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