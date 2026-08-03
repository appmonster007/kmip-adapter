package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.Extractable;

public class ExtractableBenchmarkSubject extends KmipBenchmarkSubject<Extractable> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ExtractableBenchmarkSubject() throws Exception {
    Extractable subject = Extractable.of(true);
    initialize(subject, Extractable.class);
  }

  @Override
  public String name() {
    return "Extractable";
  }
}