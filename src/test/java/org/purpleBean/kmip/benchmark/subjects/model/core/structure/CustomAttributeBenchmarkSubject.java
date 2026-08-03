package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.type.AttributeValue;

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
