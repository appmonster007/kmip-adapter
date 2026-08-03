package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ValidationVersionMinor Domain Tests")
class ValidationVersionMinorTest extends AbstractKmipDataTypeTestSuite<ValidationVersionMinor> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationVersionMinor> type() {
    return ValidationVersionMinor.class;
  }

  @Override
  protected ValidationVersionMinor createDefault() {
    return ValidationVersionMinor.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}