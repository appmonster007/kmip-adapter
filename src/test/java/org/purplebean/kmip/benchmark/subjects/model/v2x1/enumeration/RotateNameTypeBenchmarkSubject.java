package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;

/**
 * Benchmark subject for {@link RotateNameType}.
 */
public class RotateNameTypeBenchmarkSubject extends KmipBenchmarkSubject<RotateNameType> {

  /**
   * Constructs a new {@link RotateNameTypeBenchmarkSubject}.
   */
  public RotateNameTypeBenchmarkSubject() throws Exception {
    RotateNameType rotateNameType = RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(rotateNameType, RotateNameType.class);
  }

  @Override
  public String name() {
    return "RotateNameType";
  }

}
