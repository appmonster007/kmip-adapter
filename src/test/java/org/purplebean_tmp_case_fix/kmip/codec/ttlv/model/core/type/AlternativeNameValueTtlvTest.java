package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeNameValue TTLV Serialization Tests")
class AlternativeNameValueTtlvTest
    extends AbstractTtlvSerializationTestSuite<AlternativeNameValue> {

  @Override
  public Class<AlternativeNameValue> type() {
    return AlternativeNameValue.class;
  }

  @Override
  public AlternativeNameValue createDefault() {
    return AlternativeNameValue
        .builder()
        .value("some-value")
        .build();
  }

  @Override
  public AlternativeNameValue createVariant() {
    return AlternativeNameValue
        .builder()
        .value("some-other-value")
        .build();
  }
}