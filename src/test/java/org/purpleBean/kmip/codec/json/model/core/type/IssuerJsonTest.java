package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Issuer;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Issuer JSON Serialization Tests")
class IssuerJsonTest extends AbstractJsonSerializationTestSuite<Issuer> {

  @Override
  public Class<Issuer> type() {
    return Issuer.class;
  }

  @Override
  public Issuer createDefault() {
    return Issuer
        .builder()
        .value("test-issuer")
        .build();
  }

  @Override
  public Issuer createVariant() {
    return Issuer
        .builder()
        .value("another-issuer")
        .build();
  }
}