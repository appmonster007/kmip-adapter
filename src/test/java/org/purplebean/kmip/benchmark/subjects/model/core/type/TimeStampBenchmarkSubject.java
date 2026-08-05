package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.TimeStamp;

/**
 * Benchmark subject for {@link TimeStamp}.
 */
public class TimeStampBenchmarkSubject extends KmipBenchmarkSubject<TimeStamp> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link TimeStampBenchmarkSubject}.
   */
  public TimeStampBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    TimeStamp timeStamp = TimeStamp
        .builder()
        .value(fixed)
        .build();
    initialize(timeStamp, TimeStamp.class);
  }

  @Override
  public String name() {
    return "TimeStamp";
  }

}