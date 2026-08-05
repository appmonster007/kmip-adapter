package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.EncodingOption;

/**
 * Benchmark subject for {@link EncodingOption}.
 */
public class EncodingOptionBenchmarkSubject extends KmipBenchmarkSubject<EncodingOption> {

  /**
   * Constructs a new {@link EncodingOptionBenchmarkSubject}.
   */
  public EncodingOptionBenchmarkSubject() throws Exception {
    EncodingOption encodingOption = EncodingOption.Standard.NO_ENCODING.inst();
    initialize(encodingOption, EncodingOption.class);
  }

  @Override
  public String name() {
    return "EncodingOption";
  }

}
