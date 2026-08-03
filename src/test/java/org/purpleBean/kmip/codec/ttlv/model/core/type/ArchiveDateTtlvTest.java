package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ArchiveDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ArchiveDate TTLV Serialization Tests")
class ArchiveDateTtlvTest extends AbstractTtlvSerializationTestSuite<ArchiveDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<ArchiveDate> type() {
    return ArchiveDate.class;
  }

  @Override
  public ArchiveDate createDefault() {

    return ArchiveDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public ArchiveDate createVariant() {

    return ArchiveDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}
