package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.Name;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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