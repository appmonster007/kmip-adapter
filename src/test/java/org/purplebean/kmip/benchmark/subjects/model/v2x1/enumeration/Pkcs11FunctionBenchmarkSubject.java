package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import java.util.Set;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11Function;

/**
 * Benchmark subject for {@link Pkcs11Function}.
 */
public class Pkcs11FunctionBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11Function> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link Pkcs11FunctionBenchmarkSubject}.
   */
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