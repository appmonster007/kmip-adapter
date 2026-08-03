package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.OperationPolicyName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("OperationPolicyName JSON Serialization Tests")
class OperationPolicyNameJsonTest extends AbstractJsonSerializationTestSuite<OperationPolicyName> {

  @Override
  public Class<OperationPolicyName> type() {
    return OperationPolicyName.class;
  }

  @Override
  public OperationPolicyName createDefault() {
    return OperationPolicyName
        .builder()
        .value("test")
        .build();
  }

  @Override
  public OperationPolicyName createVariant() {
    return OperationPolicyName
        .builder()
        .value("test2")
        .build();
  }
}
