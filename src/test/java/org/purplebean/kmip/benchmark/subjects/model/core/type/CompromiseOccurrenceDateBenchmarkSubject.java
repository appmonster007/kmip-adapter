package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;

/**
 * Benchmark subject for {@link CompromiseOccurrenceDate}.
 */
public class CompromiseOccurrenceDateBenchmarkSubject
    extends KmipBenchmarkSubject<CompromiseOccurrenceDate> {

  /**
   * Constructs a new {@link CompromiseOccurrenceDateBenchmarkSubject}.
   */
  public CompromiseOccurrenceDateBenchmarkSubject() throws Exception {
    CompromiseOccurrenceDate compromiseOccurrenceDate = CompromiseOccurrenceDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(compromiseOccurrenceDate, CompromiseOccurrenceDate.class);
  }

  @Override
  public String name() {
    return "CompromiseOccurrenceDate";
  }

}