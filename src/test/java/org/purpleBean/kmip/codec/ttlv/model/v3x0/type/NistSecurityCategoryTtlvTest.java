package org.purplebean.kmip.codec.ttlv.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.NistSecurityCategory;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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