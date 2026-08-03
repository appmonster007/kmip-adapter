package org.purplebean.kmip.codec.json.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.Ephemeral;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Ephemeral JSON Serialization")
class EphemeralJsonTest extends AbstractJsonSerializationTestSuite<Ephemeral> {
  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.Standard.DATA.inst();
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.Standard.EMPTY.inst();
  }
}
