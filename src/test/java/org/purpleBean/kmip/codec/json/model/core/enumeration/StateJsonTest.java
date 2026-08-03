package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("State JSON Serialization")
class StateJsonTest extends AbstractJsonSerializationTestSuite<State> {
  @Override
  public Class<State> type() {
    return State.class;
  }

  @Override
  public State createDefault() {
    return State.Standard.ACTIVE.inst();
  }

  @Override
  public State createVariant() {
    return State.Standard.DEACTIVATED.inst();
  }
}
