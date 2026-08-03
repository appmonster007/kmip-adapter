package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.OperationPolicyName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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
