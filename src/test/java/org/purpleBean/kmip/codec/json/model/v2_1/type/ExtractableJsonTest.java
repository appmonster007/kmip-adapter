package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Extractable;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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