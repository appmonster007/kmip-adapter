package org.purpleBean.kmip.codec.xml.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngSeedOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
