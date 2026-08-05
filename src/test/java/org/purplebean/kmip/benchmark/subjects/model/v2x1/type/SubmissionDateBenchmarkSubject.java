package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.SubmissionDate;

/**
 * Benchmark subject for {@link SubmissionDate}.
 */
public class SubmissionDateBenchmarkSubject extends KmipBenchmarkSubject<SubmissionDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link SubmissionDateBenchmarkSubject}.
   */
  public SubmissionDateBenchmarkSubject() throws Exception {
    SubmissionDate subject =
        SubmissionDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    initialize(subject, SubmissionDate.class);
  }

  @Override
  public String name() {
    return "SubmissionDate";
  }
}