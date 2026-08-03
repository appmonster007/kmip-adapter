package org.purpleBean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.type.RotateLatest;

public class RotateLatestBenchmarkSubject extends KmipBenchmarkSubject<RotateLatest> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public RotateLatestBenchmarkSubject() throws Exception {
    RotateLatest subject = RotateLatest.of(true);
    initialize(subject, RotateLatest.class);
  }

  @Override
  public String name() {
    return "RotateLatest";
  }
}