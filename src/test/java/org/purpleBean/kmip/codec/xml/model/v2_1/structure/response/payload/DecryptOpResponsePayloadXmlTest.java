package org.purpleBean.kmip.codec.xml.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.DecryptOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DecryptOpResponsePayload Xml Serialization Tests")
class DecryptOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<DecryptOpResponsePayload> {

  @Override
  public Class<DecryptOpResponsePayload> type() {
    return DecryptOpResponsePayload.class;
  }

  @Override
  public DecryptOpResponsePayload createDefault() {
    return DecryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public DecryptOpResponsePayload createVariant() {
    return DecryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}