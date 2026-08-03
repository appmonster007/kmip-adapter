package org.purplebean.kmip.benchmark.subjects.model.v3x0.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.type.Name;

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