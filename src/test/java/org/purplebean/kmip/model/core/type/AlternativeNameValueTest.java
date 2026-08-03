package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AlternativeNameValue Domain Tests")
class AlternativeNameValueTest extends AbstractKmipDataTypeTestSuite<AlternativeNameValue> {

  @Override
  protected Class<AlternativeNameValue> type() {
    return AlternativeNameValue.class;
  }

  @Override
  protected AlternativeNameValue createDefault() {
    return AlternativeNameValue
        .builder()
        .value("some-value")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}