package org.purplebean.kmip.benchmark.subjects.model.v2x1.enumeration;

import java.util.Set;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.Pkcs11ReturnCode;

/**
 * Benchmark subject for {@link Pkcs11ReturnCode}.
 */
public class Pkcs11ReturnCodeBenchmarkSubject extends KmipBenchmarkSubject<Pkcs11ReturnCode> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link Pkcs11ReturnCodeBenchmarkSubject}.
   */
  public Pkcs11ReturnCodeBenchmarkSubject() throws Exception {
    Pkcs11ReturnCode subject = Pkcs11ReturnCode
        .register(0x80000099, "X-Benchmark",
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0))
        .inst();
    initialize(subject, Pkcs11ReturnCode.class);
  }

  @Override
  public String name() {
    return "Pkcs11ReturnCode";
  }
}