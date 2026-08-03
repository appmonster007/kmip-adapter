package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ActivationDate;

public class ActivationDateBenchmarkSubject extends KmipBenchmarkSubject<ActivationDate> {

  public ActivationDateBenchmarkSubject() throws Exception {
    ActivationDate activationDate = ActivationDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(activationDate, ActivationDate.class);
  }

  @Override
  public String name() {
    return "ActivationDate";
  }

}
