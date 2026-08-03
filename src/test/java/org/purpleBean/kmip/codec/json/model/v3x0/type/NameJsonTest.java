package org.purpleBean.kmip.codec.json.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.Name;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Name Json Serialization Tests")
class NameJsonTest extends AbstractJsonSerializationTestSuite<Name> {

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