package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration;

/**
 * Benchmark subject for {@link DataEnumeration}.
 */
public class DataEnumerationBenchmarkSubject extends KmipBenchmarkSubject<DataEnumeration> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1; // TODO: Adjust if needed

  /**
   * Constructs a new {@link DataEnumerationBenchmarkSubject}.
   */
  public DataEnumerationBenchmarkSubject() throws Exception {
    DataEnumeration subject =
        DataEnumeration.Standard.values()[0].inst();  // TODO: Create a default instance
    initialize(subject, DataEnumeration.class);
  }

  @Override
  public String name() {
    return "DataEnumeration";
  }
}