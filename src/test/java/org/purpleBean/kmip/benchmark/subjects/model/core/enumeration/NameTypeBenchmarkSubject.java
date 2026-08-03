package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.NameType;

public class NameTypeBenchmarkSubject extends KmipBenchmarkSubject<NameType> {

  public NameTypeBenchmarkSubject() throws Exception {
    NameType nameType = NameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(nameType, NameType.class);
  }

  @Override
  public String name() {
    return "NameType";
  }

}
