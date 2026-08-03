package org.purpleBean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
