package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.OriginalCreationDate;

/**
 * Benchmark subject for {@link OriginalCreationDate}.
 */
public class OriginalCreationDateBenchmarkSubject
    extends KmipBenchmarkSubject<OriginalCreationDate> {

  /**
   * Constructs a new {@link OriginalCreationDateBenchmarkSubject}.
   */
  public OriginalCreationDateBenchmarkSubject() throws Exception {
    OriginalCreationDate originalCreationDate = OriginalCreationDate
        .builder()
        .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
        .build();
    initialize(originalCreationDate, OriginalCreationDate.class);
  }

  @Override
  public String name() {
    return "OriginalCreationDate";
  }

}