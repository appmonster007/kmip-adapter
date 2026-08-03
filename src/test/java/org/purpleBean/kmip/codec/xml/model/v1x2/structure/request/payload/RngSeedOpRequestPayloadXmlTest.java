package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RngSeedOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RngSeedOpRequestPayload Xml Serialization Tests")
class RngSeedOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<RngSeedOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RngSeedOpRequestPayload> type() {
    return RngSeedOpRequestPayload.class;
  }

  @Override
  public RngSeedOpRequestPayload createDefault() {
    return RngSeedOpRequestPayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public RngSeedOpRequestPayload createVariant() {
    return RngSeedOpRequestPayload
        .builder()
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
