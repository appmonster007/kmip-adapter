package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.ValidityDate;

/**
 * Benchmark subject for {@link ValidityDate}.
 */
public class ValidityDateBenchmarkSubject extends KmipBenchmarkSubject<ValidityDate> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ValidityDateBenchmarkSubject}.
   */
  public ValidityDateBenchmarkSubject() throws Exception {
    var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    ValidityDate validityDate = ValidityDate
        .builder()
        .value(fixed)
        .build();
    initialize(validityDate, ValidityDate.class);
  }

  @Override
  public String name() {
    return "ValidityDate";
  }

}