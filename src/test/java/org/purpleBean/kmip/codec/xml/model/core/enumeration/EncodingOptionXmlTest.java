package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
