package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.Description;

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