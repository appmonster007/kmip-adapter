package org.purpleBean.kmip.codec.json.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.structure.Name;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Name Json Serialization Tests")
class NameJsonTest extends AbstractJsonSerializationTestSuite<Name> {

  @Override
  public Class<Name> type() {
    return Name.class;
  }

  @Override
  public Name createDefault() {
    return Name.of("default-name");
  }

  @Override
  public Name createVariant() {
    return Name.of("variant-name");
  }
}