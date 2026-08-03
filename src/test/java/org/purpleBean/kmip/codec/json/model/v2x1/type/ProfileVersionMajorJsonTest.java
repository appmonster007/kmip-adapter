package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ProfileVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProfileVersionMajor Json Serialization Tests")
class ProfileVersionMajorJsonTest extends AbstractJsonSerializationTestSuite<ProfileVersionMajor> {

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