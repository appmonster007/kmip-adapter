package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngRetrieveOpResponsePayload Xml Serialization Tests")
class RngRetrieveOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RngRetrieveOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RngRetrieveOpResponsePayload> type() {
    return RngRetrieveOpResponsePayload.class;
  }

  @Override
  public RngRetrieveOpResponsePayload createDefault() {
    return RngRetrieveOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public RngRetrieveOpResponsePayload createVariant() {
    return RngRetrieveOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
