package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ProfileVersionMajor Domain Tests")
class ProfileVersionMajorTest extends AbstractKmipDataTypeTestSuite<ProfileVersionMajor> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ProfileVersionMajor> type() {
    return ProfileVersionMajor.class;
  }

  @Override
  protected ProfileVersionMajor createDefault() {
    return ProfileVersionMajor.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}