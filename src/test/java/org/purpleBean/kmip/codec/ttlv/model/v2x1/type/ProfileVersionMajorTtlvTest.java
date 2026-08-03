package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ProfileVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProfileVersionMajor Ttlv Serialization Tests")
class ProfileVersionMajorTtlvTest extends AbstractTtlvSerializationTestSuite<ProfileVersionMajor> {

  @Override
  public Class<ProfileVersionMajor> type() {
    return ProfileVersionMajor.class;
  }

  @Override
  public ProfileVersionMajor createDefault() {
    return ProfileVersionMajor.of(123);
  }

  @Override
  public ProfileVersionMajor createVariant() {
    return ProfileVersionMajor.of(456);
  }
}