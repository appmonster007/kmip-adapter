package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DerivationData;

/**
 * Benchmark subject for {@link DerivationData}.
 */
public class DerivationDataBenchmarkSubject extends KmipBenchmarkSubject<DerivationData> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DerivationDataBenchmarkSubject}.
   */
  public DerivationDataBenchmarkSubject() throws Exception {
    DerivationData derivationData = DerivationData.of(new byte[] {0x01, 0x02, 0x03});
    initialize(derivationData, DerivationData.class);
  }

  @Override
  public String name() {
    return "DerivationData";
  }

}