package org.purpleBean.kmip.codec.xml.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.EncryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EncryptOpRequestPayload Xml Serialization Tests")
class EncryptOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<EncryptOpRequestPayload> {

  @Override
  public Class<EncryptOpRequestPayload> type() {
    return EncryptOpRequestPayload.class;
  }

  @Override
  public EncryptOpRequestPayload createDefault() {
    return EncryptOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public EncryptOpRequestPayload createVariant() {
    return EncryptOpRequestPayload
        .builder()
        .build();
  }
}