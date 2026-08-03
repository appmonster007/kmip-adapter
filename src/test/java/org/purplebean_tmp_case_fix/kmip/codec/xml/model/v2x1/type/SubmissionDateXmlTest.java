package org.purplebean.kmip.codec.xml.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SubmissionDate Xml Serialization Tests")
class SubmissionDateXmlTest extends AbstractXmlSerializationTestSuite<SubmissionDate> {

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