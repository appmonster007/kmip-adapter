package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.IvLength;

public class IvLengthBenchmarkSubject extends KmipBenchmarkSubject<IvLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public IvLengthBenchmarkSubject() throws Exception {
    IvLength ivLength = IvLength.of(128);
    initialize(ivLength, IvLength.class);
  }

  @Override
  public String name() {
    return "IvLength";
  }

}