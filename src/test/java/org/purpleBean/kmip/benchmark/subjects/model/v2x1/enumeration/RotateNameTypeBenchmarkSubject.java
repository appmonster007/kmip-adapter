package org.purpleBean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.RotateNameType;

public class RotateNameTypeBenchmarkSubject extends KmipBenchmarkSubject<RotateNameType> {

  public RotateNameTypeBenchmarkSubject() throws Exception {
    RotateNameType rotateNameType = RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
    initialize(rotateNameType, RotateNameType.class);
  }

  @Override
  public String name() {
    return "RotateNameType";
  }

}
