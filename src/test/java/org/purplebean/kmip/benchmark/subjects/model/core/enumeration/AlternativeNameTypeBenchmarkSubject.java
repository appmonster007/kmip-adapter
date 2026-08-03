package org.purplebean.kmip.benchmark.subjects.model.core.enumeration;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeBenchmarkSubject extends KmipBenchmarkSubject<AlternativeNameType> {

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
