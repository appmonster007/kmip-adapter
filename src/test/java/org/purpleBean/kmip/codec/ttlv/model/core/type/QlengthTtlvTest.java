package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Qlength;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Qlength TTLV Serialization Tests")
class QlengthTtlvTest extends AbstractTtlvSerializationTestSuite<Qlength> {

  @Override
  public Class<Qlength> type() {
    return Qlength.class;
  }

  @Override
  public Qlength createDefault() {
    return Qlength
        .builder()
        .value(128)
        .build();
  }

  @Override
  public Qlength createVariant() {
    return Qlength
        .builder()
        .value(256)
        .build();
  }
}