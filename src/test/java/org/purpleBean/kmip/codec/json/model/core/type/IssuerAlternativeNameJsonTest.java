package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IssuerAlternativeName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("IssuerAlternativeName JSON Serialization Tests")
class IssuerAlternativeNameJsonTest
    extends AbstractJsonSerializationTestSuite<IssuerAlternativeName> {

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