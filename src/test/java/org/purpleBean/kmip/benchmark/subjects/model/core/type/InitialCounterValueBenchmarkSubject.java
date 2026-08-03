package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueBenchmarkSubject extends KmipBenchmarkSubject<InitialCounterValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public InitialCounterValueBenchmarkSubject() throws Exception {
    InitialCounterValue initialCounterValue = InitialCounterValue.of(1);
    initialize(initialCounterValue, InitialCounterValue.class);
  }

  @Override
  public String name() {
    return "InitialCounterValue";
  }

}