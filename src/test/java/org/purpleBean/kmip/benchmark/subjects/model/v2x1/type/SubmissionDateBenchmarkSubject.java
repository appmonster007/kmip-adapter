package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.SubmissionDate;

public class SubmissionDateBenchmarkSubject extends KmipBenchmarkSubject<SubmissionDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

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