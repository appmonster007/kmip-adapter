package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.link;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.link.Pkcs12PasswordLink;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Pkcs12PasswordLink Ttlv Serialization Tests")
class Pkcs12PasswordLinkTtlvTest extends AbstractTtlvSerializationTestSuite<Pkcs12PasswordLink> {

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