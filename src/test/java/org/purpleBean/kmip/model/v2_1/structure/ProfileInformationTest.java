package org.purpleBean.kmip.model.v2_1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ProfileName;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ProfileInformation Domain Tests")
class ProfileInformationTest extends AbstractKmipStructureTestSuite<ProfileInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ProfileInformation> type() {
    return ProfileInformation.class;
  }

  @Override
  protected ProfileInformation createDefault() {
    return ProfileInformation.of(ProfileName.Standard.COMPLETE_SERVER_BASIC.inst());
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}