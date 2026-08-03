package org.purplebean.kmip.codec.json.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.Name;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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