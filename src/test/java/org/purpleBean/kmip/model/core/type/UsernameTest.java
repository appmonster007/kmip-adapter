package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Username Domain Tests")
class UsernameTest extends AbstractKmipDataTypeTestSuite<Username> {

  @Override
  protected Class<Username> type() {
    return Username.class;
  }

  @Override
  protected Username createDefault() {
    return Username
        .builder()
        .value("test-user")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}