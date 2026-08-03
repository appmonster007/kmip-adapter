package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.ProfileVersion;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProfileVersion Ttlv Serialization Tests")
class ProfileVersionTtlvTest extends AbstractTtlvSerializationTestSuite<ProfileVersion> {

  @Override
  public Class<ProfileVersion> type() {
    return ProfileVersion.class;
  }

  @Override
  public ProfileVersion createDefault() {
    return ProfileVersion.of(2, 1);
  }

  @Override
  public ProfileVersion createVariant() {
    return ProfileVersion.of(3, 0);
  }
}