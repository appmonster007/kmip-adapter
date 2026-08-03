package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ProfileVersionMajor;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileVersionMajor Xml Serialization Tests")
class ProfileVersionMajorXmlTest extends AbstractXmlSerializationTestSuite<ProfileVersionMajor> {

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