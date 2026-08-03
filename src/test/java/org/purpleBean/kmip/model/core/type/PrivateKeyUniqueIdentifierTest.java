package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("PrivateKeyUniqueIdentifier Domain Tests")
class PrivateKeyUniqueIdentifierTest
    extends AbstractKmipDataTypeTestSuite<PrivateKeyUniqueIdentifier> {

  @Override
  protected Class<PrivateKeyUniqueIdentifier> type() {
    return PrivateKeyUniqueIdentifier.class;
  }

  @Override
  protected PrivateKeyUniqueIdentifier createDefault() {
    return PrivateKeyUniqueIdentifier
        .builder()
        .value("test-private-key-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}