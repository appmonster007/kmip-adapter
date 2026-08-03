package org.purpleBean.kmip.codec.ttlv.model.v3_0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.NistSecurityCategory;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NistSecurityCategory Ttlv Serialization Tests")
class NistSecurityCategoryTtlvTest
    extends AbstractTtlvSerializationTestSuite<NistSecurityCategory> {

  @Override
  public Class<NistSecurityCategory> type() {
    return NistSecurityCategory.class;
  }

  @Override
  public NistSecurityCategory createDefault() {
    return NistSecurityCategory.of(123);
  }

  @Override
  public NistSecurityCategory createVariant() {
    return NistSecurityCategory.of(456);
  }
}