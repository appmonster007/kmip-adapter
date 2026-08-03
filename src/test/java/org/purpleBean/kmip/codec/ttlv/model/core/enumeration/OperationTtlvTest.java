package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Operation TTLV Serialization")
class OperationTtlvTest extends AbstractTtlvSerializationTestSuite<Operation> {
  @Override
  public Class<Operation> type() {
    return Operation.class;
  }

  @Override
  public Operation createDefault() {
    return Operation.Standard.CREATE.inst();
  }

  @Override
  public Operation createVariant() {
    return Operation.Standard.CREATE_KEY_PAIR.inst();
  }
}
