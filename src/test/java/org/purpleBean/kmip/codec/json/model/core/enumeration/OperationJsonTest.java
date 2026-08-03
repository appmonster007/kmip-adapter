package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Operation JSON Serialization")
class OperationJsonTest extends AbstractJsonSerializationTestSuite<Operation> {
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
