package org.purplebean.kmip.codec.ttlv.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.Name;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Name Ttlv Serialization Tests")
class NameTtlvTest extends AbstractTtlvSerializationTestSuite<Name> {

  @Override
  public Class<Name> type() {
    return Name.class;
  }

  @Override
  public Name createDefault() {
    return Name.of("default-string");
  }

  @Override
  public Name createVariant() {
    return Name.of("variant-string");
  }
}