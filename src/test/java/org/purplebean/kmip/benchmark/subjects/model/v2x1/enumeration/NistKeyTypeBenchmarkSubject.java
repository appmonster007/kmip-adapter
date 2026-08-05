package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;

/**
 * Benchmark subject for {@link NistKeyType}.
 */
public class NistKeyTypeBenchmarkSubject extends KmipBenchmarkSubject<NistKeyType> {

  /**
   * Constructs a new {@link NistKeyTypeBenchmarkSubject}.
   */
  public NistKeyTypeBenchmarkSubject() throws Exception {
    NistKeyType nistKeyType = NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
    initialize(nistKeyType, NistKeyType.class);
  }

  @Override
  public String name() {
    return "NistKeyType";
  }

}
