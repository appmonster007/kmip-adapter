package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Ephemeral;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Ephemeral Json Serialization Tests")
class EphemeralJsonTest extends AbstractJsonSerializationTestSuite<Ephemeral> {

  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.of(true);
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.of(false);
  }
}