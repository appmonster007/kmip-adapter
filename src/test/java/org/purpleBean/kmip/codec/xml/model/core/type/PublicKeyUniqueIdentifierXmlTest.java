package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PublicKeyUniqueIdentifier XML Serialization Tests")
class PublicKeyUniqueIdentifierXmlTest
    extends AbstractXmlSerializationTestSuite<PublicKeyUniqueIdentifier> {

  @Override
  public Class<PublicKeyUniqueIdentifier> type() {
    return PublicKeyUniqueIdentifier.class;
  }

  @Override
  public PublicKeyUniqueIdentifier createDefault() {
    return PublicKeyUniqueIdentifier
        .builder()
        .value("test-key-id")
        .build();
  }

  @Override
  public PublicKeyUniqueIdentifier createVariant() {
    return PublicKeyUniqueIdentifier
        .builder()
        .value("another-key-id")
        .build();
  }
}