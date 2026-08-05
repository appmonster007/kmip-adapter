package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.model.core.structure.KeyWrappingData;

/**
 * Benchmark subject for {@link KeyWrappingData}.
 */
public class KeyWrappingDataBenchmarkSubject extends KmipBenchmarkSubject<KeyWrappingData> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link KeyWrappingDataBenchmarkSubject}.
   */
  public KeyWrappingDataBenchmarkSubject() throws Exception {
    KeyWrappingData subject = KeyWrappingData
        .builder()
        .wrappingMethod(WrappingMethod.Standard.ENCRYPT.inst())
        .build();
    initialize(subject, KeyWrappingData.class);
  }

  @Override
  public String name() {
    return "KeyWrappingData";
  }

}