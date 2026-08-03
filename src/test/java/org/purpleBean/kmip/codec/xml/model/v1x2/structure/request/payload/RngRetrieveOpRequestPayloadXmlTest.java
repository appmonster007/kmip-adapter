package org.purpleBean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.RngRetrieveOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngRetrieveOpRequestPayload Xml Serialization Tests")
class RngRetrieveOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RngRetrieveOpRequestPayload> {

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
