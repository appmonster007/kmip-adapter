package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.DecryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DecryptOpRequestPayload Xml Serialization Tests")
class DecryptOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DecryptOpRequestPayload> {

  @Override
  public Class<DecryptOpRequestPayload> type() {
    return DecryptOpRequestPayload.class;
  }

  @Override
  public DecryptOpRequestPayload createDefault() {
    return DecryptOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public DecryptOpRequestPayload createVariant() {
    return DecryptOpRequestPayload
        .builder()
        .build();
  }
}