package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Extractable;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Extractable Json Serialization Tests")
class ExtractableJsonTest extends AbstractJsonSerializationTestSuite<Extractable> {

  @Override
  public Class<Extractable> type() {
    return Extractable.class;
  }

  @Override
  public Extractable createDefault() {
    return Extractable.of(true);
  }

  @Override
  public Extractable createVariant() {
    return Extractable.of(false);
  }
}