package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ProfileName;
import org.purplebean.kmip.model.v2x1.structure.ProfileInformation;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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