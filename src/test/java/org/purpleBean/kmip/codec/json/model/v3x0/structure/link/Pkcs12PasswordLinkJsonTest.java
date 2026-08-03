package org.purpleBean.kmip.codec.json.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs12PasswordLink Json Serialization Tests")
class Pkcs12PasswordLinkJsonTest extends AbstractJsonSerializationTestSuite<Pkcs12PasswordLink> {

  @Override
  public Class<Pkcs12PasswordLink> type() {
    return Pkcs12PasswordLink.class;
  }

  @Override
  public Pkcs12PasswordLink createDefault() {
    return Pkcs12PasswordLink.of(UniqueIdentifier.of("test-id"));
  }

  @Override
  public Pkcs12PasswordLink createVariant() {
    return Pkcs12PasswordLink.of(UniqueIdentifier.of("test-id"));
  }
}