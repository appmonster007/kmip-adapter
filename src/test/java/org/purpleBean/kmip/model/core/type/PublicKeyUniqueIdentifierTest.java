package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PublicKeyUniqueIdentifier Domain Tests")
class PublicKeyUniqueIdentifierTest
    extends AbstractKmipDataTypeTestSuite<PublicKeyUniqueIdentifier> {

  @Override
  protected Class<PublicKeyUniqueIdentifier> type() {
    return PublicKeyUniqueIdentifier.class;
  }

  @Override
  protected PublicKeyUniqueIdentifier createDefault() {
    return PublicKeyUniqueIdentifier
        .builder()
        .value("test-key-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}