package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ProcessStartDate;

public class ProcessStartDateBenchmarkSubject extends KmipBenchmarkSubject<ProcessStartDate> {

  public ProcessStartDateBenchmarkSubject() throws Exception {
    ProcessStartDate processStartDate = ProcessStartDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(processStartDate, ProcessStartDate.class);
  }

  @Override
  public String name() {
    return "ProcessStartDate";
  }

}