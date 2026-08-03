package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.ProfileVersion;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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