package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Sensitive;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Sensitive Json Serialization Tests")
class SensitiveJsonTest extends AbstractJsonSerializationTestSuite<Sensitive> {

  @Override
  public Class<Sensitive> type() {
    return Sensitive.class;
  }

  @Override
  public Sensitive createDefault() {
    return Sensitive.of(true);
  }

  @Override
  public Sensitive createVariant() {
    return Sensitive.of(false);
  }
}