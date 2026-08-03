package org.purplebean.kmip.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SubmissionDate Domain Tests")
class SubmissionDateTest extends AbstractKmipDataTypeTestSuite<SubmissionDate> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<SubmissionDate> type() {
    return SubmissionDate.class;
  }

  @Override
  protected SubmissionDate createDefault() {
    return SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.DATE_TIME_EXTENDED;
  }
}