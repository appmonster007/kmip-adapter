package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Pkcs12FriendlyName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Pkcs12FriendlyName Json Serialization Tests")
class Pkcs12FriendlyNameJsonTest extends AbstractJsonSerializationTestSuite<Pkcs12FriendlyName> {

  @Override
  public Class<Pkcs12FriendlyName> type() {
    return Pkcs12FriendlyName.class;
  }

  @Override
  public Pkcs12FriendlyName createDefault() {
    return Pkcs12FriendlyName.of("default-string");
  }

  @Override
  public Pkcs12FriendlyName createVariant() {
    return Pkcs12FriendlyName.of("variant-string");
  }
}