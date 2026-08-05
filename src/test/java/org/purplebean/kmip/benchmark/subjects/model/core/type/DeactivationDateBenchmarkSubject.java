package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DeactivationDate;

/**
 * Benchmark subject for {@link DeactivationDate}.
 */
public class DeactivationDateBenchmarkSubject extends KmipBenchmarkSubject<DeactivationDate> {

  /**
   * Constructs a new {@link DeactivationDateBenchmarkSubject}.
   */
  public DeactivationDateBenchmarkSubject() throws Exception {
    DeactivationDate deactivationDate = DeactivationDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(deactivationDate, DeactivationDate.class);
  }

  @Override
  public String name() {
    return "DeactivationDate";
  }

}