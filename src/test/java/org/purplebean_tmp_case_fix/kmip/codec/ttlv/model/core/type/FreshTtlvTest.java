package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Fresh;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Fresh TTLV Serialization Tests")
class FreshTtlvTest extends AbstractTtlvSerializationTestSuite<Fresh> {

  @Override
  public Class<Fresh> type() {
    return Fresh.class;
  }

  @Override
  public Fresh createDefault() {
    return Fresh
        .builder()
        .value(true)
        .build();
  }

  @Override
  public Fresh createVariant() {
    return Fresh
        .builder()
        .value(false)
        .build();
  }
}