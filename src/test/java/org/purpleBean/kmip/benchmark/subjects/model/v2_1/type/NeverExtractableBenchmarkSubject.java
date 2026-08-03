package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.NeverExtractable;

public class NeverExtractableBenchmarkSubject extends KmipBenchmarkSubject<NeverExtractable> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public NeverExtractableBenchmarkSubject() throws Exception {
    NeverExtractable subject = NeverExtractable.of(true);
    initialize(subject, NeverExtractable.class);
  }

  @Override
  public String name() {
    return "NeverExtractable";
  }
}