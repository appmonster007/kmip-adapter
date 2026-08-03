package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.type.NameValue;

public class NameBenchmarkSubject extends KmipBenchmarkSubject<Name> {

  public NameBenchmarkSubject() throws Exception {
    Name name = Name
        .builder()
        .nameValue(NameValue.of("some-name"))
        .nameType(NameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .build();
    initialize(name, Name.class);
  }

  @Override
  public String name() {
    return "Name";
  }

}
