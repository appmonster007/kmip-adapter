package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateDate;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateDate Ttlv Serialization Tests")
class RotateDateTtlvTest extends AbstractTtlvSerializationTestSuite<RotateDate> {

  @Override
  public Class<RotateDate> type() {
    return RotateDate.class;
  }

  @Override
  public RotateDate createDefault() {
    return RotateDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
  }

  @Override
  public RotateDate createVariant() {
    return RotateDate.of(OffsetDateTime.of(2025, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
  }
}