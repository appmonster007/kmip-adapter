package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SignatureVerifyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SignatureVerifyOpResponsePayload Xml Serialization Tests")
class SignatureVerifyOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<SignatureVerifyOpResponsePayload> {

  @Override
  public Class<SignatureVerifyOpResponsePayload> type() {
    return SignatureVerifyOpResponsePayload.class;
  }

  @Override
  public SignatureVerifyOpResponsePayload createDefault() {
    return SignatureVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public SignatureVerifyOpResponsePayload createVariant() {
    return SignatureVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}