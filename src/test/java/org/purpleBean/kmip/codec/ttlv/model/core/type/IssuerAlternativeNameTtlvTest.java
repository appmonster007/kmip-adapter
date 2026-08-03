package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IssuerAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("IssuerAlternativeName TTLV Serialization Tests")
class IssuerAlternativeNameTtlvTest
    extends AbstractTtlvSerializationTestSuite<IssuerAlternativeName> {

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