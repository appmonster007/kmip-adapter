package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Rights;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Rights Ttlv Serialization Tests")
class RightsTtlvTest extends AbstractTtlvSerializationTestSuite<Rights> {

  @Override
  public Class<Rights> type() {
    return Rights.class;
  }

  @Override
  public Rights createDefault() {
    return Rights
        .builder()
        .build();
  }

  @Override
  public Rights createVariant() {
    return Rights
        .builder()
        .build();
  }
}