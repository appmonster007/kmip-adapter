package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Issuer Domain Tests")
class IssuerTest extends AbstractKmipDataTypeTestSuite<Issuer> {

  @Override
  protected Class<Issuer> type() {
    return Issuer.class;
  }

  @Override
  protected Issuer createDefault() {
    return Issuer
        .builder()
        .value("test-issuer")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}