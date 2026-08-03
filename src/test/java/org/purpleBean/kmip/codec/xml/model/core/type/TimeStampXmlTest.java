package org.purpleBean.kmip.codec.xml.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("TimeStamp XML Serialization Tests")
class TimeStampXmlTest extends AbstractXmlSerializationTestSuite<TimeStamp> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<TimeStamp> type() {
    return TimeStamp.class;
  }

  @Override
  public TimeStamp createDefault() {

    return TimeStamp
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public TimeStamp createVariant() {

    return TimeStamp
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}