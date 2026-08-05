package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.NameType;

/**
 * Benchmark subject for {@link NameType}.
 */
public class NameTypeBenchmarkSubject extends KmipBenchmarkSubject<NameType> {

  /**
   * Constructs a new {@link NameTypeBenchmarkSubject}.
   */
  public NameTypeBenchmarkSubject() throws Exception {
    NameType nameType = NameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(nameType, NameType.class);
  }

  @Override
  public String name() {
    return "NameType";
  }

}
