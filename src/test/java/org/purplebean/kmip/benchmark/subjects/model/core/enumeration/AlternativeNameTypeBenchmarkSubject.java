package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;

/**
 * Benchmark subject for {@link AlternativeNameType}.
 */
public class AlternativeNameTypeBenchmarkSubject extends KmipBenchmarkSubject<AlternativeNameType> {

  /**
   * Constructs a new {@link AlternativeNameTypeBenchmarkSubject}.
   */
  public AlternativeNameTypeBenchmarkSubject() throws Exception {
    AlternativeNameType alternativeNameType =
        AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(alternativeNameType, AlternativeNameType.class);
  }

  @Override
  public String name() {
    return "AlternativeNameType";
  }

}
