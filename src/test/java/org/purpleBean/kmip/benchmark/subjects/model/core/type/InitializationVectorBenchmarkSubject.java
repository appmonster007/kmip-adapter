package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.InitializationVector;

public class InitializationVectorBenchmarkSubject
    extends KmipBenchmarkSubject<InitializationVector> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public InitializationVectorBenchmarkSubject() throws Exception {
    InitializationVector initializationVector =
        InitializationVector.of(new byte[] {0x01, 0x02, 0x03});
    initialize(initializationVector, InitializationVector.class);
  }

  @Override
  public String name() {
    return "InitializationVector";
  }

}