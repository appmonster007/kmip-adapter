package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Extractable;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Extractable Ttlv Serialization Tests")
class ExtractableTtlvTest extends AbstractTtlvSerializationTestSuite<Extractable> {

  @Override
  public Class<Extractable> type() {
    return Extractable.class;
  }

  @Override
  public Extractable createDefault() {
    return Extractable.of(true);
  }

  @Override
  public Extractable createVariant() {
    return Extractable.of(false);
  }
}