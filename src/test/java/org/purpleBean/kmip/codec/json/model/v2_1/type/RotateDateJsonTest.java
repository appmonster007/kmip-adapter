package org.purpleBean.kmip.codec.json.model.v2_1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.RotateDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateDate Json Serialization Tests")
class RotateDateJsonTest extends AbstractJsonSerializationTestSuite<RotateDate> {

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