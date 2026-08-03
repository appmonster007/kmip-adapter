package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMinor;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProfileVersionMinor Ttlv Serialization Tests")
class ProfileVersionMinorTtlvTest extends AbstractTtlvSerializationTestSuite<ProfileVersionMinor> {

  @Override
  public Class<ProfileVersionMinor> type() {
    return ProfileVersionMinor.class;
  }

  @Override
  public ProfileVersionMinor createDefault() {
    return ProfileVersionMinor.of(123);
  }

  @Override
  public ProfileVersionMinor createVariant() {
    return ProfileVersionMinor.of(456);
  }
}