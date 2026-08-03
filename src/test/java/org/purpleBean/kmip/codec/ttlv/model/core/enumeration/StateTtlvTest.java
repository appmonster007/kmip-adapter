package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("State TTLV Serialization")
class StateTtlvTest extends AbstractTtlvSerializationTestSuite<State> {
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
