package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.FixedFieldLength;

/**
 * Benchmark subject for {@link FixedFieldLength}.
 */
public class FixedFieldLengthBenchmarkSubject extends KmipBenchmarkSubject<FixedFieldLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link FixedFieldLengthBenchmarkSubject}.
   */
  public FixedFieldLengthBenchmarkSubject() throws Exception {
    FixedFieldLength fixedFieldLength = FixedFieldLength.of(128);
    initialize(fixedFieldLength, FixedFieldLength.class);
  }

  @Override
  public String name() {
    return "FixedFieldLength";
  }

}