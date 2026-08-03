package org.purpleBean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.OtpDigits;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpDigits Xml Serialization Tests")
class OtpDigitsXmlTest extends AbstractXmlSerializationTestSuite<OtpDigits> {

  @Override
  public Class<OtpDigits> type() {
    return OtpDigits.class;
  }

  @Override
  public OtpDigits createDefault() {
    return OtpDigits.of(123);
  }

  @Override
  public OtpDigits createVariant() {
    return OtpDigits.of(456);
  }
}