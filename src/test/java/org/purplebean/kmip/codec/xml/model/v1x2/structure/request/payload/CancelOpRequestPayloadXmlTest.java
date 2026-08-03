package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CancelOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CancelOpRequestPayload Xml Serialization Tests")
class CancelOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CancelOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CancelOpRequestPayload> type() {
    return CancelOpRequestPayload.class;
  }

  @Override
  public CancelOpRequestPayload createDefault() {
    return CancelOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public CancelOpRequestPayload createVariant() {
    return CancelOpRequestPayload
        .builder()
        .asynchronousCorrelationValue(AsynchronousCorrelationValue.of(new byte[] {4, 5, 6}))
        .build();
  }
}
