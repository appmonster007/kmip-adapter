package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2x1.structure.RotateName;
import org.purpleBean.kmip.model.v2x1.type.RotateNameValue;

public class RotateNameBenchmarkSubject extends KmipBenchmarkSubject<RotateName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public RotateNameBenchmarkSubject() throws Exception {
    RotateName subject = RotateName.of(
        RotateNameValue.of("default"),
        RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
    initialize(subject, RotateName.class);
  }

  @Override
  public String name() {
    return "RotateName";
  }
}