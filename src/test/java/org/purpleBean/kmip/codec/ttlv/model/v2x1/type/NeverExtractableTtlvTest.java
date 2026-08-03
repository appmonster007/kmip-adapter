package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.NeverExtractable;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NeverExtractable Ttlv Serialization Tests")
class NeverExtractableTtlvTest extends AbstractTtlvSerializationTestSuite<NeverExtractable> {

  @Override
  public Class<NeverExtractable> type() {
    return NeverExtractable.class;
  }

  @Override
  public NeverExtractable createDefault() {
    return NeverExtractable.of(true);
  }

  @Override
  public NeverExtractable createVariant() {
    return NeverExtractable.of(false);
  }
}