package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.RotateDate;

/**
 * Benchmark subject for {@link RotateDate}.
 */
public class RotateDateBenchmarkSubject extends KmipBenchmarkSubject<RotateDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link RotateDateBenchmarkSubject}.
   */
  public RotateDateBenchmarkSubject() throws Exception {
    RotateDate subject = RotateDate.of(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC));
    initialize(subject, RotateDate.class);
  }

  @Override
  public String name() {
    return "RotateDate";
  }
}