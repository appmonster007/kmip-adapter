package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("EncodingOption TTLV Serialization")
class EncodingOptionTtlvTest extends AbstractTtlvSerializationTestSuite<EncodingOption> {
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
