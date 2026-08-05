package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.LastChangeDate;

/**
 * Benchmark subject for {@link LastChangeDate}.
 */
public class LastChangeDateBenchmarkSubject extends KmipBenchmarkSubject<LastChangeDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link LastChangeDateBenchmarkSubject}.
   */
  public LastChangeDateBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    LastChangeDate lastChangeDate = LastChangeDate
        .builder()
        .value(fixed)
        .build();
    initialize(lastChangeDate, LastChangeDate.class);
  }

  @Override
  public String name() {
    return "LastChangeDate";
  }

}
