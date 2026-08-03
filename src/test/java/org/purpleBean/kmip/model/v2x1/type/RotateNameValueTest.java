package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("RotateNameValue Domain Tests")
class RotateNameValueTest extends AbstractKmipDataTypeTestSuite<RotateNameValue> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<RotateNameValue> type() {
    return RotateNameValue.class;
  }

  @Override
  protected RotateNameValue createDefault() {
    return RotateNameValue.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}