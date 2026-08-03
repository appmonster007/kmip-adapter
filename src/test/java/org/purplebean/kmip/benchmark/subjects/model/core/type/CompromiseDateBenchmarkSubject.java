package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CompromiseDate;

public class CompromiseDateBenchmarkSubject extends KmipBenchmarkSubject<CompromiseDate> {

  public CompromiseDateBenchmarkSubject() throws Exception {
    CompromiseDate compromiseDate = CompromiseDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(compromiseDate, CompromiseDate.class);
  }

  @Override
  public String name() {
    return "CompromiseDate";
  }

}