package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ProfileVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileVersionMinor Xml Serialization Tests")
class ProfileVersionMinorXmlTest extends AbstractXmlSerializationTestSuite<ProfileVersionMinor> {

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