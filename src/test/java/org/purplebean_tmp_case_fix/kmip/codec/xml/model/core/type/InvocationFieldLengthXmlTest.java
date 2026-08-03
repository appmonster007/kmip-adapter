package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InvocationFieldLength XML Serialization Tests")
class InvocationFieldLengthXmlTest
    extends AbstractXmlSerializationTestSuite<InvocationFieldLength> {

  @Override
  public Class<InvocationFieldLength> type() {
    return InvocationFieldLength.class;
  }

  @Override
  public InvocationFieldLength createDefault() {
    return InvocationFieldLength.of(128);
  }

  @Override
  public InvocationFieldLength createVariant() {
    return InvocationFieldLength.of(256);
  }
}