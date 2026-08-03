package org.purpleBean.kmip.codec.json.model.v2_1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SubmissionDate Json Serialization Tests")
class SubmissionDateJsonTest extends AbstractJsonSerializationTestSuite<SubmissionDate> {

  @Override
  public Class<SubmissionDate> type() {
    return SubmissionDate.class;
  }

  @Override
  public SubmissionDate createDefault() {
    return SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
  }

  @Override
  public SubmissionDate createVariant() {
    return SubmissionDate.of(OffsetDateTime.of(2025, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
  }
}