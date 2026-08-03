package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EncodingOption XML Serialization")
class EncodingOptionXmlTest extends AbstractXmlSerializationTestSuite<EncodingOption> {
  @Override
  public Class<EncodingOption> type() {
    return EncodingOption.class;
  }

  @Override
  public EncodingOption createDefault() {
    return EncodingOption.Standard.NO_ENCODING.inst();
  }

  @Override
  public EncodingOption createVariant() {
    return EncodingOption.Standard.TTLV_ENCODING.inst();
  }
}
