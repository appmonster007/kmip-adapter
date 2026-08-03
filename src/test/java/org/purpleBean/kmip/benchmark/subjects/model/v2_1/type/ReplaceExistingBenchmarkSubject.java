package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ReplaceExisting;

public class ReplaceExistingBenchmarkSubject extends KmipBenchmarkSubject<ReplaceExisting> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ReplaceExistingBenchmarkSubject() throws Exception {
    ReplaceExisting subject = ReplaceExisting.of(true);
    initialize(subject, ReplaceExisting.class);
  }

  @Override
  public String name() {
    return "ReplaceExisting";
  }
}