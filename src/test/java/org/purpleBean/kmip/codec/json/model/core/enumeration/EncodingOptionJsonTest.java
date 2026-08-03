package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EncodingOption JSON Serialization")
class EncodingOptionJsonTest extends AbstractJsonSerializationTestSuite<EncodingOption> {
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
