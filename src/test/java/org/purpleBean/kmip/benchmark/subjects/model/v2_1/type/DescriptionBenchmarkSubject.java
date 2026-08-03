package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.Description;

public class DescriptionBenchmarkSubject extends KmipBenchmarkSubject<Description> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public DescriptionBenchmarkSubject() throws Exception {
    Description subject = Description.of("default-string");
    initialize(subject, Description.class);
  }

  @Override
  public String name() {
    return "Description";
  }
}