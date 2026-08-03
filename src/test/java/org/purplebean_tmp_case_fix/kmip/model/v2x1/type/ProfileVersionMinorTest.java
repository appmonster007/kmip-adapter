package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ProfileVersionMinor Domain Tests")
class ProfileVersionMinorTest extends AbstractKmipDataTypeTestSuite<ProfileVersionMinor> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ProfileVersionMinor> type() {
    return ProfileVersionMinor.class;
  }

  @Override
  protected ProfileVersionMinor createDefault() {
    return ProfileVersionMinor.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}