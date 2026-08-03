package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.InteropOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InteropOpRequestPayload Xml Serialization Tests")
class InteropOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<InteropOpRequestPayload> {

  @Override
  public Class<InteropOpRequestPayload> type() {
    return InteropOpRequestPayload.class;
  }

  @Override
  public InteropOpRequestPayload createDefault() {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .build();
  }

  @Override
  public InteropOpRequestPayload createVariant() {
    return InteropOpRequestPayload
        .builder()
        .interopFunction(InteropFunction.Standard.BEGIN.inst())
        .build();
  }
}