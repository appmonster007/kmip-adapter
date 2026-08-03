package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Username JSON Serialization Tests")
class UsernameJsonTest extends AbstractJsonSerializationTestSuite<Username> {

  @Override
  public Class<Username> type() {
    return Username.class;
  }

  @Override
  public Username createDefault() {
    return Username
        .builder()
        .value("test-user")
        .build();
  }

  @Override
  public Username createVariant() {
    return Username
        .builder()
        .value("another-user")
        .build();
  }
}