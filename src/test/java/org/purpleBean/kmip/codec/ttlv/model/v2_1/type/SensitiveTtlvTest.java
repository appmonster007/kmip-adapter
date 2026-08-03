package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.Sensitive;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Sensitive Ttlv Serialization Tests")
class SensitiveTtlvTest extends AbstractTtlvSerializationTestSuite<Sensitive> {

  @Override
  public Class<Sensitive> type() {
    return Sensitive.class;
  }

  @Override
  public Sensitive createDefault() {
    return Sensitive.of(true);
  }

  @Override
  public Sensitive createVariant() {
    return Sensitive.of(false);
  }
}