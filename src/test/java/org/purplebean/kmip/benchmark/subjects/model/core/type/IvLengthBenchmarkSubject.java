package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.IvLength;

/**
 * Benchmark subject for {@link IvLength}.
 */
public class IvLengthBenchmarkSubject extends KmipBenchmarkSubject<IvLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link IvLengthBenchmarkSubject}.
   */
  public IvLengthBenchmarkSubject() throws Exception {
    IvLength ivLength = IvLength.of(128);
    initialize(ivLength, IvLength.class);
  }

  @Override
  public String name() {
    return "IvLength";
  }

}