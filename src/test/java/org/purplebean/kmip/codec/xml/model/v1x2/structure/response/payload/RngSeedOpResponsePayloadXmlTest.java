package org.purplebean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngSeedOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
