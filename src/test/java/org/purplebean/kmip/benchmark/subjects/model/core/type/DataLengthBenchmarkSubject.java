package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataLength;

/**
 * Benchmark subject for {@link DataLength}.
 */
public class DataLengthBenchmarkSubject extends KmipBenchmarkSubject<DataLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DataLengthBenchmarkSubject}.
   */
  public DataLengthBenchmarkSubject() throws Exception {
    DataLength dataLength = DataLength.of(128);
    initialize(dataLength, DataLength.class);
  }

  @Override
  public String name() {
    return "DataLength";
  }

}
