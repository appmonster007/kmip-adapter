package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngSeedOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngSeedOpResponsePayload Xml Serialization Tests")
class RngSeedOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RngSeedOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RngSeedOpResponsePayload> type() {
    return RngSeedOpResponsePayload.class;
  }

  @Override
  public RngSeedOpResponsePayload createDefault() {
    return RngSeedOpResponsePayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
  }

  @Override
  public RngSeedOpResponsePayload createVariant() {
    return RngSeedOpResponsePayload
        .builder()
        .dataLength(DataLength.of(32))
        .build();
  }
}
