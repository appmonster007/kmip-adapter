package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ProtectStopDate;

/**
 * Benchmark subject for {@link ProtectStopDate}.
 */
public class ProtectStopDateBenchmarkSubject extends KmipBenchmarkSubject<ProtectStopDate> {

  /**
   * Constructs a new {@link ProtectStopDateBenchmarkSubject}.
   */
  public ProtectStopDateBenchmarkSubject() throws Exception {
    ProtectStopDate protectStopDate = ProtectStopDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(protectStopDate, ProtectStopDate.class);
  }

  @Override
  public String name() {
    return "ProtectStopDate";
  }

}