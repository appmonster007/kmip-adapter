package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.InitialDate;

public class InitialDateBenchmarkSubject extends KmipBenchmarkSubject<InitialDate> {

  public InitialDateBenchmarkSubject() throws Exception {
    InitialDate initialDate = InitialDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(initialDate, InitialDate.class);
  }

  @Override
  public String name() {
    return "InitialDate";
  }

}