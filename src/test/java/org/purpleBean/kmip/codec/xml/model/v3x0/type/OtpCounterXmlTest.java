package org.purpleBean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.OtpCounter;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("OtpCounter Xml Serialization Tests")
class OtpCounterXmlTest extends AbstractXmlSerializationTestSuite<OtpCounter> {

  @Override
  public Class<OtpCounter> type() {
    return OtpCounter.class;
  }

  @Override
  public OtpCounter createDefault() {
    return OtpCounter.of(123);
  }

  @Override
  public OtpCounter createVariant() {
    return OtpCounter.of(456);
  }
}