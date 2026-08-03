package org.purpleBean.kmip.benchmark.subjects.model.v2_1.enumeration;

import java.util.Set;
import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.Pkcs11Function;

public class Pkcs11FunctionBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11Function> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public Pkcs11FunctionBenchmarkSubject() throws Exception {
    Pkcs11Function subject = Pkcs11Function
        .register(0x80000099, "X-Benchmark",
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0))
        .inst();
    initialize(subject, Pkcs11Function.class);
  }

  @Override
  public String name() {
    return "Pkcs11Function";
  }
}