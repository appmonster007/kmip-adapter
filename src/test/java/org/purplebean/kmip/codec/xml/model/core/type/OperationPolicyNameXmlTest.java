package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.OperationPolicyName;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OperationPolicyName XML Serialization Tests")
class OperationPolicyNameXmlTest extends AbstractXmlSerializationTestSuite<OperationPolicyName> {

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
