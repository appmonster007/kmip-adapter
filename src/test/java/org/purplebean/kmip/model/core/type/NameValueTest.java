package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("NameValue Domain Tests")
class NameValueTest extends AbstractKmipDataTypeTestSuite<NameValue> {

  @Override
  protected Class<NameValue> type() {
    return NameValue.class;
  }

  @Override
  protected NameValue createDefault() {
    return NameValue.of("some-name");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}
