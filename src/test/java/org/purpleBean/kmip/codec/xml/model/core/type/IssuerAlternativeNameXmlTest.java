package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("IssuerAlternativeName XML Serialization Tests")
class IssuerAlternativeNameXmlTest
    extends AbstractXmlSerializationTestSuite<IssuerAlternativeName> {

  @Override
  public Class<IssuerAlternativeName> type() {
    return IssuerAlternativeName.class;
  }

  @Override
  public IssuerAlternativeName createDefault() {
    return IssuerAlternativeName.of("test-issuer-alt-name".getBytes());
  }

  @Override
  public IssuerAlternativeName createVariant() {
    return IssuerAlternativeName.of("another-issuer-alt-name".getBytes());
  }
}