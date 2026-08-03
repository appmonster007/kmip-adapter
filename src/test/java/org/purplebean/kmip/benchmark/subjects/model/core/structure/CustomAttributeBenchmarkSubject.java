package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.State;
import org.purplebean.kmip.model.core.structure.CustomAttribute;
import org.purplebean.kmip.model.core.type.AttributeValue;

public class CustomAttributeBenchmarkSubject extends KmipBenchmarkSubject<CustomAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CustomAttributeBenchmarkSubject() throws Exception {
    CustomAttribute customAttribute =
        CustomAttribute.of("x-custom-state", AttributeValue.ofEnumeration(State.Standard.ACTIVE));
    initialize(customAttribute, CustomAttribute.class);
  }

  @Override
  public String name() {
    return "CustomAttribute";
  }

}
