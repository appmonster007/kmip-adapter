package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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