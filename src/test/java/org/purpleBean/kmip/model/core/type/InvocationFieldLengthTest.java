package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InvocationFieldLength Domain Tests")
class InvocationFieldLengthTest extends AbstractKmipDataTypeTestSuite<InvocationFieldLength> {

  @Override
  protected Class<InvocationFieldLength> type() {
    return InvocationFieldLength.class;
  }

  @Override
  protected InvocationFieldLength createDefault() {
    return InvocationFieldLength.of(128);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}