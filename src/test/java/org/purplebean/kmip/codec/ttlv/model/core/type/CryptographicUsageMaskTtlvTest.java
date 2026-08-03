package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CryptographicUsageMask TTLV Serialization Tests")
class CryptographicUsageMaskTtlvTest
    extends AbstractTtlvSerializationTestSuite<CryptographicUsageMask> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<CryptographicUsageMask> type() {
    return CryptographicUsageMask.class;
  }

  @Override
  public CryptographicUsageMask createDefault() {
    return CryptographicUsageMask
        .builder()
        .value(10)
        .build();
  }

  @Override
  public CryptographicUsageMask createVariant() {
    return CryptographicUsageMask
        .builder()
        .value(100)
        .build();
  }
}
