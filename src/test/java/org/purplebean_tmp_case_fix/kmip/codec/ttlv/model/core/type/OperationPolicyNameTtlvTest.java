package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.OperationPolicyName;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OperationPolicyName TTLV Serialization Tests")
class OperationPolicyNameTtlvTest extends AbstractTtlvSerializationTestSuite<OperationPolicyName> {

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
