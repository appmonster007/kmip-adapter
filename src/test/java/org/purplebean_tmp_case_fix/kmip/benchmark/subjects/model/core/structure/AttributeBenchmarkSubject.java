package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.structure.Attribute;

public class AttributeBenchmarkSubject extends KmipBenchmarkSubject<Attribute> {

  public AttributeBenchmarkSubject() throws Exception {
    Attribute attribute = Attribute.of(State.Standard.COMPROMISED.inst());
    initialize(attribute, Attribute.class);
  }

  @Override
  public String name() {
    return "Attribute";
  }

}
