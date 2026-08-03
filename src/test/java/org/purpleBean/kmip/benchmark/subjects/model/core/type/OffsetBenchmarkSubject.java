package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.Offset;

public class OffsetBenchmarkSubject extends KmipBenchmarkSubject<Offset> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public OffsetBenchmarkSubject() throws Exception {
    Offset offset = Offset
        .builder()
        .value(10)
        .build();
    initialize(offset, Offset.class);
  }

  @Override
  public String name() {
    return "Offset";
  }

}