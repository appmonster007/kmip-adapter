package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.model.v2_1.structure.ProfileInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProfileInformation Xml Serialization Tests")
class ProfileInformationXmlTest extends AbstractXmlSerializationTestSuite<ProfileInformation> {

  @Override
  public Class<ProfileInformation> type() {
    return ProfileInformation.class;
  }

  @Override
  public ProfileInformation createDefault() {
    return ProfileInformation.of(ProfileName.Standard.COMPLETE_SERVER_BASIC.inst());
  }

  @Override
  public ProfileInformation createVariant() {
    return ProfileInformation.of(ProfileName.Standard.COMPLETE_SERVER_BASIC.inst());
  }
}