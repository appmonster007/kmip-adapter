package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.SubjectAlternativeName;

public class SubjectAlternativeNameBenchmarkSubject
    extends KmipBenchmarkSubject<SubjectAlternativeName> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public SubjectAlternativeNameBenchmarkSubject() throws Exception {
    SubjectAlternativeName subjectAlternativeName =
        SubjectAlternativeName.of(new byte[] {0x01, 0x02, 0x03});
    initialize(subjectAlternativeName, SubjectAlternativeName.class);
  }

  @Override
  public String name() {
    return "SubjectAlternativeName";
  }

}