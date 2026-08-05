package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;

/**
 * Benchmark subject for {@link CryptographicUsageMask}.
 */
public class CryptographicUsageMaskBenchmarkSubject
    extends KmipBenchmarkSubject<CryptographicUsageMask> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link CryptographicUsageMaskBenchmarkSubject}.
   */
  public CryptographicUsageMaskBenchmarkSubject() throws Exception {
    var fixed = 10;
    CryptographicUsageMask cryptographicUsageMask = CryptographicUsageMask
        .builder()
        .value(fixed)
        .build();
    initialize(cryptographicUsageMask, CryptographicUsageMask.class);
  }

  @Override
  public String name() {
    return "CryptographicUsageMask";
  }

}
