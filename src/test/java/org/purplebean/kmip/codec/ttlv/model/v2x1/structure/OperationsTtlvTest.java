package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Operations;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Operations Ttlv Serialization Tests")
class OperationsTtlvTest extends AbstractTtlvSerializationTestSuite<Operations> {

  @Override
  public Class<Operations> type() {
    return Operations.class;
  }

  @Override
  public Operations createDefault() {
    return Operations
        .builder()
        .build();
  }

  @Override
  public Operations createVariant() {
    return Operations
        .builder()
        .build();
  }
}