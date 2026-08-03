package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SerialNumber;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SerialNumber TTLV Serialization Tests")
class SerialNumberTtlvTest extends AbstractTtlvSerializationTestSuite<SerialNumber> {

  @Override
  public Class<SerialNumber> type() {
    return SerialNumber.class;
  }

  @Override
  public SerialNumber createDefault() {
    return SerialNumber
        .builder()
        .value("12345")
        .build();
  }

  @Override
  public SerialNumber createVariant() {
    return SerialNumber
        .builder()
        .value("67890")
        .build();
  }
}