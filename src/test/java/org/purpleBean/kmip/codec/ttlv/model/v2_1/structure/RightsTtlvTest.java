package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Rights;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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