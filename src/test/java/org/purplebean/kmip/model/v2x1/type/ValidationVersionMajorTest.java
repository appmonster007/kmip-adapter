package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ValidationVersionMajor Domain Tests")
class ValidationVersionMajorTest extends AbstractKmipDataTypeTestSuite<ValidationVersionMajor> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationVersionMajor> type() {
    return ValidationVersionMajor.class;
  }

  @Override
  protected ValidationVersionMajor createDefault() {
    return ValidationVersionMajor.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}