package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ProfileName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProfileName JSON Serialization")
class ProfileNameJsonTest extends AbstractJsonSerializationTestSuite<ProfileName> {
  @Override
  public Class<ProfileName> type() {
    return ProfileName.class;
  }

  @Override
  public ProfileName createDefault() {
    return ProfileName.Standard.COMPLETE_SERVER_BASIC.inst();
  }

  @Override
  public ProfileName createVariant() {
    return ProfileName.Standard.COMPLETE_SERVER_TLS_V1_2.inst();
  }
}
