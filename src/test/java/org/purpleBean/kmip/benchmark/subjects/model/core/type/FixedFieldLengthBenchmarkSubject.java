package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;

public class FixedFieldLengthBenchmarkSubject extends KmipBenchmarkSubject<FixedFieldLength> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public FixedFieldLengthBenchmarkSubject() throws Exception {
    FixedFieldLength fixedFieldLength = FixedFieldLength.of(128);
    initialize(fixedFieldLength, FixedFieldLength.class);
  }

  @Override
  public String name() {
    return "FixedFieldLength";
  }

}