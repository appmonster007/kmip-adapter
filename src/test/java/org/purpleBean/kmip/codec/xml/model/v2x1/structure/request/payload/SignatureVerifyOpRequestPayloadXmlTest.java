package org.purpleBean.kmip.codec.xml.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SignatureVerifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SignatureVerifyOpRequestPayload Xml Serialization Tests")
class SignatureVerifyOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<SignatureVerifyOpRequestPayload> {

  @Override
  public Class<SignatureVerifyOpRequestPayload> type() {
    return SignatureVerifyOpRequestPayload.class;
  }

  @Override
  public SignatureVerifyOpRequestPayload createDefault() {
    return SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public SignatureVerifyOpRequestPayload createVariant() {
    return SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}