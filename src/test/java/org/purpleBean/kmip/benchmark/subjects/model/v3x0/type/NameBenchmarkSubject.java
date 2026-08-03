package org.purpleBean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.type.Name;

public class NameBenchmarkSubject extends KmipBenchmarkSubject<Name> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public NameBenchmarkSubject() throws Exception {
    Name subject = Name.of("default-string");
    initialize(subject, Name.class);
  }

  @Override
  public String name() {
    return "Name";
  }
}